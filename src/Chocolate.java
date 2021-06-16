/**
 *
 * Chocolate.java
 * Ritterspiel aka Yucky Chocolate
 * last changed: 16.06.21
 * @author Lennart Palisaar
 *
 * */

import java.util.ArrayList;
import java.util.Scanner;

public class Chocolate {

    private static int x; //width of board
    private static int y; //height of board
    private static ArrayList<Integer> XBreak = new ArrayList<>(); //to check whether a move is valid
    private static ArrayList<Integer> YBreak = new ArrayList<>(); //to check whether a move is valid
    private static boolean playerOne = false; //when not player One -> player Two :)
    //for optional color
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static boolean colorAllowed = false;


    //takes the initial user input for board measurements
    public static int[] initialUserInput(){
        int[] xy;
        xy = new int[2];

        Scanner sc = new Scanner(System.in);

        System.out.print("How wide should the board be?");
        xy[0] = sc.nextInt();

        System.out.print("How tall should the board be?");
        xy[1] = sc.nextInt();

        System.out.print("Would you like to play in pretty-mode? y/n\n(Causes Problems in non-Unix Consoles)");
        char yn = sc.next().charAt(0);

        if(yn == 'y'){
            colorAllowed = true;
            System.out.println("Pretty-mode is turned on.");
        } else {
            colorAllowed = false;
            System.out.println("Pretty-mode is turned off.");
        }

        //sc.close(); //somehow this crashes my program??

        return xy;
    }

    //returns an int the user inputted
    public static char userInputInt(){
        char i;
        Scanner sc = new Scanner(System.in);
        String player;

        if(playerOne){
            player = "Player One";
        } else {
            player = "Player Two";
        }



        System.out.print("Where do you want to break, " + player + "?");
        i = sc.next().charAt(0);
        //sc.close();

        return i;
    }

    //prints the board. takes the width and height of the board as int - if colorAllowed == true: the board is in color
    //color not supported by cmd or powershell :( (is supported by Unix shell prompts. Works in IntelliJ Run)
    public static void updateBoard(){
        XBreak.clear();
        YBreak.clear();

        if(colorAllowed) {
            for (int i = 0; i < y; i++) {
                System.out.print("   ");
                for (int j = 0; j < x; j++) {
                    if (i == 0 && j == 0) {
                        System.out.print(ANSI_BLUE + "█   " + ANSI_RESET);
                    } else {
                        System.out.print(ANSI_YELLOW + "█   " + ANSI_RESET);
                    }
                }
                System.out.print("\n");
                if(i < y-1) {
                    System.out.println(i);
                    YBreak.add(i);
                }
            }
            System.out.print("  ");
            for (int i = 0; i < x - 1; i++) {
                int t = 65 + i;
                System.out.printf("   %c", t);
                XBreak.add(t);
            }
        } else {
            for (int i = 0; i < y; i++) {
                System.out.print("   ");
                for (int j = 0; j < x; j++) {
                    if (i == 0 && j == 0) {
                        System.out.print("X   ");
                    } else {
                        System.out.print("█   ");
                    }
                }
                System.out.print("\n");
                if(i < y-1) {
                    System.out.println(i);
                    YBreak.add(i);
                }
            }
            System.out.print("  ");
            for (int i = 0; i < x - 1; i++) {
                int t = 65 + i;
                System.out.printf("   %c", t);
                XBreak.add(t);
            }
        }
        System.out.println();
        playerOne = !playerOne;//changes the player to determine who won
    }

    //Constructor
    public Chocolate() {
    }

    //some Getter and Setter
    public static int getX() {
        return x;
    }

    public static int getY() {
        return y;
    }

    public static ArrayList<Integer> getXBreak() {
        return XBreak;
    }

    public static ArrayList<Integer> getYBreak() {
        return YBreak;
    }

    public static boolean isPlayerOne() {
        return playerOne;
    }

    public static void setX(int x) {
        Chocolate.x = x;
    }

    public static void setY(int y) {
        Chocolate.y = y;
    }

    public static void setXBreak(ArrayList<Integer> XBreak) {
        Chocolate.XBreak = XBreak;
    }

    public static void setYBreak(ArrayList<Integer> YBreak) {
        Chocolate.YBreak = YBreak;
    }

    public static void setPlayerOne(boolean playerOne) {
        Chocolate.playerOne = playerOne;
    }

}