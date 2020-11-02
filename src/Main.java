import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Arrays;
import java.util.MissingResourceException;

import java.util.Scanner;
import java.util.TimerTask;

public class Main extends JFrame{

    Main(){
        Connect4 game = new Connect4();
        Scanner input = new Scanner(System.in);
        Canvas c = new Canvas();
        int width = 800;
        int height = 500;
        int PieceSize = 60;
        int xDivideOffset = 10;
        int yDivideOffset = 6;
        int WALL = (width * 250) / 350;
        int xOffset =  (width * 20) / 350;
        int yOffset = (height * 17) / 350;
        c.setBackground(Color.gray);
        add(c);
        String os = System.getProperty("os.name");
        if (os.startsWith("Mac")){
            setSize(width + 1, height + 23);//mac
        }else if (os.startsWith("Windows")){
            setSize(width+17, height+40);//windows
        }else{
            throw new MissingResourceException("ERROR CANNOT DETECT SYSTEM","os","OS");
        }
        show();
        final int[] choice = {-1};
        final int[][] mouse = {{0, 0}};
        c.addMouseMotionListener(new MouseMotionListener() {
         @Override
         public void mouseDragged(MouseEvent e) {
         }
         @Override
         public void mouseMoved(MouseEvent e) {
             mouse[0] = findpiecefromPos(e.getPoint(), width, height, xDivideOffset, yDivideOffset,xOffset,yOffset);
         }
     }
        );
        c.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (game.turn.equals("red")) {
                    choice[0] = findpiecefromPos(e.getPoint(), width, height, xDivideOffset, yDivideOffset,xOffset,yOffset)[0];
                }

            }
            @Override
            public void mousePressed(MouseEvent e) {

            }
            @Override
            public void mouseReleased(MouseEvent e) {

            }
            @Override
            public void mouseEntered(MouseEvent e) {

            }
            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
        Graphics g = c.getGraphics();
        boolean playing = true;
        //timer.start();
        while (true){
            g.setColor(Color.BLACK);
            g.fillRect(WALL+xOffset,0,5,height);
            game.draw(g,width,height,xDivideOffset,yDivideOffset,xOffset,yOffset,PieceSize);

            if (playing) {
                if (game.turn.equals("red")) {

                    //System.out.println("thinking");
                    //double[] moves = MiniMax.minimax(game, 6, Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY, true, true);
                    //System.out.println(Arrays.toString(moves));
                    //choice = assistant.argmax(moves);
                } else {
                    System.out.println("thinking");
                    double[] moves = MiniMax.minimax(game, 15, Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY, false, true);
                    System.out.println(Arrays.toString(moves));
                    choice[0] = assistant.argmin(moves);
                    //System.out.println("MOVE:");
                    //choice = input.nextInt();
                }
                if (choice[0] != -1) {
                    game.place(game.turn, choice[0]);
                    int outcome = game.CheckForConnect();
                    if (outcome == 1) {
                        System.out.println("RED WON");
                        playing = false;
                        //game = new Connect4();
                    } else if (outcome == -1) {
                        System.out.println("BLACK WON");
                        playing = false;
                    }
                    if (game.checkForDraw()) {
                        System.out.println("DRAW");
                        playing = false;
                    }
                    choice[0] = -1;
                }else{
                    if (game.turn.equals("red")) {
                        //g.setColor(Color.RED);
                        //try {
                        //    g.fillOval(mouse[0][0] * (width / xDivideOffset) + xOffset,
                        //            mouse[0][1] * (height / yDivideOffset) + yOffset,
                        //            PieceSize, PieceSize);
                        //} catch (NullPointerException e) {
                        //}
                    }
                }
            }

        }







    }
    int[] findpiecefromPos(Point pos,int width,int height, int xDivideOffset,int yDivideOffset, int xOffset, int yOffset) {
        int coloum = (pos.x - xOffset)/ (width / xDivideOffset);
        int row = (pos.y  - yOffset)/ (height / yDivideOffset);
        return new int[]{coloum,row};
    }

    public static void main(String[] args){
        Main start = new Main();
    }

}
