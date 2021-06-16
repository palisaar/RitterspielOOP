/**
 *
 * Main.java
 * Ritterspiel aka Yucky Chocolate
 * last changed: 16.06.21
 * @author Lennart Palisaar
 *
 * */

import java.util.Scanner;

public class Main {

    private static char breakPoint; //saves the breakpoint

    //runs a constant game loop for the two player mode
    public static void gameLoopMult() {

        Chocolate choccy = new Chocolate();

        // while true loop for the endless game loop? game end thru y/n question when someone has won?

        int[] xy = choccy.initialUserInput();
        choccy.setX(xy[0]);
        choccy.setY(xy[1]);

        System.out.println("This is your board!");
        choccy.updateBoard();


        while(choccy.getY() != 1 || choccy.getX() !=1){
            breakPoint = choccy.userInputInt(); // the point where the choccy should be broken
            //gets the breakpoint and derives how the table should be changed from that
            if(choccy.getYBreak().contains(Character.getNumericValue(breakPoint))){
                choccy.setY(Character.getNumericValue(breakPoint) + 1);
            } else if (choccy.getXBreak().contains((int)breakPoint)) {
                int temp = (int)breakPoint - 64;
                choccy.setX(temp);
            } else {
                System.out.println("Nah-ah, not an option.");
            }

            choccy.updateBoard();//prints the table from the changed values
        }

        if(choccy.isPlayerOne()) {
            System.out.println("PlayerOne has to eat the Yucky choccy");
        } else {
            System.out.println("PlayerTwo has to eat the Yucky choccy");
        }

        //restarts the game loop if the player wants to play again.
        System.out.println("Would you like to play again? y/n");
        Scanner sc = new Scanner(System.in);
        char yn = sc.next().charAt(0);
        if(yn == 'y'){
            gameLoopMult();
        }

    }

    //runs a constant game loop for the one player mode
    public static void gameLoopSing() {

        Chocolate choccy = new Chocolate();

        //gets the initial dimensions of the chocolate/board
        int[] xy = choccy.initialUserInput();
        choccy.setX(xy[0]);
        choccy.setY(xy[1]);

        System.out.println("This is your board!");
        choccy.updateBoard();

        if(choccy.getX() == choccy.getY() ) { //the option where the player goes first
            while (choccy.getY() != 1 || choccy.getX() != 1) {

                breakPoint = choccy.userInputInt(); // the point where the choccy should be broken
                //gets the breakpoint and derives how the table should be changed from that
                if (choccy.getYBreak().contains(Character.getNumericValue(breakPoint))) {
                    choccy.setY(Character.getNumericValue(breakPoint) + 1);
                } else if (choccy.getXBreak().contains((int) breakPoint)) {
                    int temp = (int) breakPoint - 64;
                    choccy.setX(temp);
                } else {
                    System.out.println("Nah-ah, not an option.");
                }
                choccy.updateBoard(/*choccy.getX(), choccy.getY()*/);//prints the table from the changed values

                System.out.println("It's the Computers turn.");
                xy = ambrosiusIV(choccy.getX(), choccy.getY());


                choccy.setX(xy[0]);
                choccy.setY(xy[1]);

                choccy.updateBoard(/*choccy.getX(), choccy.getY()*/);

            }
            System.out.println("You have to eat the Yucky choccy");
        } else { //the option where the computer goes first
            while (choccy.getY() != 1 || choccy.getX() != 1) {

                System.out.println("It's the Computers turn.");
                xy = ambrosiusIV(choccy.getX(), choccy.getY()); //gets the way to win :)


                choccy.setX(xy[0]);
                choccy.setY(xy[1]);


                choccy.updateBoard(/*choccy.getX(), choccy.getY()*/); //upadets the board

                breakPoint = choccy.userInputInt(); // the point where the choccy should be broken
                //gets the breakpoint and derives how the table should be changed from that
                if (choccy.getYBreak().contains(Character.getNumericValue(breakPoint))) {
                    choccy.setY(Character.getNumericValue(breakPoint) + 1);
                } else if (choccy.getXBreak().contains((int) breakPoint)) {
                    int temp = (int) breakPoint - 64;
                    choccy.setX(temp);
                } else {
                    System.out.println("Nah-ah, not an option.");
                }
                choccy.updateBoard(/*choccy.getX(), choccy.getY()*/);//prints the table from the changed values

                xy = ambrosiusIV(choccy.getX(), choccy.getY());

                choccy.setX(xy[0]);
                choccy.setX(xy[1]);

            }
            System.out.println("You have to eat the Yucky choccy");
        }

        //restarts the game loop if the player wants to play again.
        System.out.println("Would you like to play again? y/n");
        Scanner sc = new Scanner(System.in);
        char yn = sc.next().charAt(0);
        if(yn == 'y'){
            gameLoopSing();
        }

    }

    //prints a fancy title with game instructions
    public static void printTitle() {
        // made with http://www.kammerl.de/ascii/AsciiSignature.php
        System.out.println("-----------------------------------------------------------");
        System.out.println(".  .         .           ,--. .               .      .     ");
        System.out.println("|  | . . ,-. | , . .    | `-' |-. ,-. ,-. ,-. |  ,-. |- ,-.");
        System.out.println("|  | | | |   |<  | |    |   . | | | | |   | | |  ,-| |  |-'");
        System.out.println("`--| `-^ `-' ' ` `-|    `--'  ' ' `-' `-' `-' `' `-^ `' `-'");
        System.out.println(".- |              /|                                       ");
        System.out.println("`--'             `-'                                       ");
        System.out.println();
        System.out.println();
        System.out.println("by Lennart Palisaar");
        System.out.println("-----------------------------------------------------------");
        System.out.println("           Win by being the one who reduces the");
        System.out.println("            board to just the top left corner.");
        System.out.println("         Select where you want to break off the the");
        System.out.println("        choccy by inputting that number or letter.");
        System.out.println("                        gl,hf!");
        System.out.println("-----------------------------------------------------------");
    }

    //gets the dimensions of the choccy and decides how to change it.
    public static int[] ambrosiusIV(int x, int y){

        int[] ret = new int[2];

        //always reduces the shape to a square.

        if (x > y){
            x = y;
        } else {
            y = x;
        }


        ret[0] = x;
        ret[1] = y;

        return ret;
    }

    public static void main(String[] args) {
        printTitle();

        //choose to play against the computer or another human. The Computer can not be beat.
        System.out.print("Would you like to play against the Computer? y/n");
        Scanner sc = new Scanner(System.in);
        char c = sc.next().charAt(0);
        if(c == 'y') {
            System.out.println("You chose to play against the computer.");
            gameLoopSing();
        } else {
            System.out.println("You chose to play against another human.");
            gameLoopMult();
        }
    }

}