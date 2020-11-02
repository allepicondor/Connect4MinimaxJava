import static java.lang.Math.max;
import static java.lang.Math.min;

public class MiniMax {
    static double evaluate(int[][] board, boolean maximizingPlayer) {
        int red = 0;
        int black = 0;
        int REDwin = 1000;
        int BLACKwin = -1000;
        if (maximizingPlayer) {
            REDwin = -1000;
            BLACKwin = 1000;
        }
        for (int x = 0; x < 7; x++) {
            for (int y = 0; y < 6; y++) {
                if (board[x][y] != 0) {
                    int side = board[x][y];
                    int counter = 0;
                    //UP
                    for (int yOff = 0; yOff < 4; yOff++) {
                        if (y - yOff > -1) {
                            if (board[x][y - yOff] == side) {
                                counter += 1;
                            } else {
                                break;
                            }
                        } else {
                            break;
                        }
                    }
                    if (side == 1) {
                        red += counter;
                    } else {
                        black += counter;
                    }
                    if (counter == 4) {
                        if (side == 1) {
                            return REDwin;
                        } else {
                            return BLACKwin;
                        }
                    }

                    //RIGHT
                    counter = 0;
                    for (int xOff = 0; xOff < 4; xOff++) {
                        if (x + xOff < 7) {
                            if (board[x + xOff][y] == side) {
                                counter += 1;
                            } else {
                                break;
                            }
                        } else {
                            break;
                        }
                    }
                    if (side == 1) {
                        red += counter;
                    } else {
                        black += counter;
                    }
                    if (counter == 4) {
                        if (side == 1) {
                            return REDwin;
                        } else {
                            return BLACKwin;
                        }
                    }

                    //TOPLeft
                    counter = 0;
                    for (int xOff = 0; xOff < 4; xOff++) {
                        if (x - xOff > -1 && y - xOff > -1) {
                            if (board[x - xOff][y - xOff] == side) {
                                counter += 1;
                            } else {
                                break;
                            }
                        } else {
                            break;
                        }
                    }
                    if (side == 1) {
                        red += counter;
                    } else {
                        black += counter;
                    }
                    if (counter == 4) {
                        if (side == 1) {
                            return REDwin;
                        } else {
                            return BLACKwin;
                        }
                    }
                    //TORIGHT
                    counter = 0;
                    for (int xOff = 0; xOff < 4; xOff++) {
                        if (x + xOff < 7 && y - xOff > -1) {
                            if (board[x + xOff][y - xOff] == side) {
                                counter += 1;
                            } else {
                                break;
                            }
                        } else {
                            break;
                        }
                    }
                    if (side == 1) {
                        red += counter;
                    } else {
                        black += counter;
                    }
                    if (counter == 4) {
                        if (side == 1) {
                            return REDwin;
                        } else {
                            return BLACKwin;
                        }
                    }
                }
            }
        }
        return red - black;
    }
    public static double[] minimax(Connect4 game,int depth, double alpha, double beta, boolean maximizingPlayer, boolean first){
        int anyoneWin = game.CheckForConnect();
        if (depth == 0 || anyoneWin == 1 || anyoneWin == -1 ){
            if (anyoneWin == 1|| anyoneWin == -1) {
                if (maximizingPlayer) return new double[]{-1000 * depth};
                else return new double[]{1000 * depth};
            }
            return new double[]{evaluate(game.board, maximizingPlayer)};
        }
        double[] moves = new double[7];
        if (maximizingPlayer) {
            double maxEval = Double.NEGATIVE_INFINITY;
            int index = 0;
            for (Connect4 child : game.possible_moves(0)) {
                if (child != null) {
                    double ev = minimax(child, depth - 1, alpha, beta, false,false)[0];
                    moves[index] = ev;
                    maxEval = max(ev, maxEval);
                    alpha = max(alpha, ev);
                    if (beta <= alpha) break;
                } else moves[index] = Double.NEGATIVE_INFINITY;
                index++;
            }
            if (first) return moves;
            return new double[]{maxEval};
        } else {
            double minEval = Double.POSITIVE_INFINITY;
            int index = 0;
            for (Connect4 child : game.possible_moves(1)) {
                if (child != null) {
                    double ev = minimax(child, depth - 1, alpha, beta, true, false)[0];
                    moves[index] = ev;
                    minEval = min(ev, minEval);
                    beta = min(beta, ev);
                    if (beta <= alpha) break;
                } else moves[index] = Double.POSITIVE_INFINITY;
                index++;
            }
            if (first) return moves;
            return new double[]{minEval};
        }
    }
}
