import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;

public class Connect4 {
    int[][] board;
    String turn;
    Connect4(){
        this.board = new int[][]{
                {0, 0, 0, 0, 0, 0},
			    {0, 0, 0, 0, 0, 0},
			    {0, 0, 0, 0, 0, 0},
			    {0, 0, 0, 0, 0, 0},
			    {0, 0, 0, 0, 0, 0},
			    {0, 0, 0, 0, 0, 0},
			    {0, 0, 0, 0, 0, 0},
		};
        this.turn = "red";
    }
    boolean place(String color,int coloum){
        int counter = 0;
        for (int y = 0; y < 6; y++) {
            if (this.board[coloum][y] != 0) {
                counter += 1;
            }
        }
        if (counter == 6) return false;
        int piece = -1;
        String oppColor = "red";
        if (color.equals("red")) {
            piece = 1;
            oppColor = "black";
        }
        for (int y = 0; y < 6; y++)
            if (this.board[coloum][y] != 0) {
                this.board[coloum][y - 1] = piece;
                this.turn = oppColor;
                break;
            } else if (y >= 5) {
                this.board[coloum][y] = piece;
                this.turn = oppColor;
                break;
            }
        return true;
    }
    int CheckForConnect() {
        int red = 0;
        int black = 0;
        for (int x = 0; x < 7; x++) {
            for (int y = 0; y < 6; y++) {
                if (this.board[x][y] != 0) {
                    int side = this.board[x][y];
                    int counter = 0;
                    //UP
                    for (int yOff = 0; yOff < 4; yOff++) {
                        if (y - yOff > -1) {
                            if (this.board[x][y - yOff] == side) {
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
                        return side;
                    }

                    //RIGHT
                    counter = 0;
                    for (int xOff = 0; xOff < 4; xOff++) {
                        if (x + xOff < 7) {
                            if (this.board[x + xOff][y] == side) {
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
                        return side;
                    }

                    //TOPLeft
                    counter = 0;
                    for (int xOff = 0; xOff < 4; xOff++) {
                        if (x - xOff > -1 && y - xOff > -1) {
                            if (this.board[x - xOff][y - xOff] == side) {
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
                        return side;
                    }
                    //TORIGHT
                    counter = 0;
                    for (int xOff = 0; xOff < 4; xOff++) {
                        if (x + xOff < 7 && y - xOff > -1) {
                            if (this.board[x + xOff][y - xOff] == side) {
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
                        return side;
                    }
                }
            }
        }
        return 0;
    }
    boolean checkForDraw() {
        boolean  fullBoard = true;
        for (int x = 0; x < 7; x++) {
            for (int y = 0; y < 6; y++) {
                if (this.board[x][y] == 0) {
                    fullBoard = false;
                }
            }
        }
        return fullBoard;
    }
    Connect4[] possible_moves(int side) {
        String color = "black";
        if (side == 0) color = "red";
        Connect4[] possibleMoves = {null,null,null,null,null,null,null};
        for (int x = 0; x < 7; x++) {
            Connect4 newBoard = new Connect4();
            newBoard.board = assistant.CopyArray(this.board);
            if (newBoard.place(color, x)) {
                possibleMoves[x] = newBoard;
            }
        }
        //console.log(possibleMoves);
        return possibleMoves;
    }
    void drawConsole(){
        int[][] newArray = new int[this.board[0].length][this.board.length];

        //invert values 90 degrees clockwise by starting from button of
        //array to top and from left to right
        for(int i=0; i<this.board[0].length; i++){
            for(int j=this.board.length-1; j>=0; j--){
                newArray[i][j] = this.board[j][i];
            }
        }
        for (int[] x: newArray){
            System.out.println(Arrays.toString(x));
        }
    }
    void draw(Graphics g,int width,int height,int xDivideOffset,int yDivideOffset,int xOffset,int yOffset,int PieceSize){
        for (int x = 0; x < 7; x++) {
            for (int y = 0; y < 6; y++) {
                if (this.board[x][y] == 1) {
                    g.setColor(Color.RED);
                    g.fillOval(x * (width / xDivideOffset) + xOffset,
                            y * (height / yDivideOffset) + yOffset,
                            PieceSize,PieceSize);

                }
                if (this.board[x][y] == -1) {
                    g.setColor(Color.BLACK);
                    g.fillOval(x * (width / xDivideOffset) + xOffset,
                            y * (height / yDivideOffset) + yOffset,
                            PieceSize,PieceSize);
                }
            }
        }
    }
}
