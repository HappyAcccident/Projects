/*
Nathaniel D'annunzio
Date: 4/19/2024
Class: AP Computer Science A
Program Name: Tic-Tac-Toe Game
Program Description: Allows the user to play Tic-Tac-Toe against another human or a computer
Difficulties: Having the user input a box to place their symbol.
What I Learned: How to combine all the aspects taught in AP Computer Science A, such as loops, arrays, method, classes, etc.
*/

import java.util.Scanner;

public class NathanielDannunzioMenu {
    private static Scanner kbReader = new Scanner(System.in);
    private static int bestOf = 0;
    private static int compDiff = 0;
    
    // Method to clear the console screen
    static void refresh() {
        for (int n = 0; n < 100; n++) {
            System.out.println();
        }
    }
    
    // Method to display the start menu and prompt user choice
    static void startMenu() {
        int input = -1;
        boolean a = true; // Flag to control the loop
        refresh();
        System.out.println("WELCOME TO TIC-TAC-TOE!!!\nWOULD YOU LIKE TO PLAY AGAINST A HUMAN OR A COMPUTER?\n\n1. HUMAN\n2. COMPUTER\n\nTYPE \"1\" FOR HUMAN OR \"2\" FOR COMPUTER.");
        while (a) {
            try {
                input = kbReader.nextInt(); // Try to get user input
            } catch (Exception e) {
                // Exception caught if user input is not an integer
            }
            kbReader.nextLine(); // Clear input buffer
            if (input == 1) {
                a = false; // Exit loop
                setsMenu(0); // Proceed to settings menu for human vs. human game
            }
            else if (input == 2) {
                a = false; // Exit loop
                compDiff = 1; // Set computer difficulty flag
                setsMenu(1); // Proceed to settings menu for human vs. computer game
            }
            else {
                System.out.println("INVALID INPUT. PLEASE TYPE EITHER \"1\" TO PLAY AGAINST ANOTHER HUMAN OR \"2\" TO PLAY AGAINST A COMPUTER. IF YOU'RE HAVING TROUBLE, PLEASE SEE THE MANUAL.");
            }
        }
    }
    
    // Method to display the settings menu and prompt user choice
    static void setsMenu(int compDiff) {
        boolean a = true; // Flag to control the loop
        int input = -1;
        refresh();
        System.out.println("WHAT SET WILL YOU PLAY? (BEST OF [X])\nTYPE ANY ODD NUMBER TO REPLACE [X].\n\nFOR EXAMPLE, IF YOU WANT TO PLAY A BEST OF 5, THEN TYPE \"5\", OR IF YOU WANT TO PLAY A BEST OF 7, THEN TYPE \"7\".\n\nTYPE \"0\" TO RETURN TO THE LAST MENU.");
        while (a) {
            try {
                input = kbReader.nextInt(); // Try to get user input
            } catch (Exception e) {
                // Exception caught if user input is not an integer
            }
            kbReader.nextLine(); // Clear input buffer
            if ((compDiff == 0) && (input == 0)) {
                a = false; // Exit loop
                startMenu(); // Return to start menu
            }
            else if (input == 0) {
                a = false; // Exit loop
                startMenu(); // Return to start menu
            }
            else if (input == -1) {
                System.out.println("INVALID INPUT. PLEASE TYPE ANY ODD NUMBER TO REPLACE [X]. IF YOU'RE HAVING TROUBLE, PLEASE SEE THE MANUAL.");
            }
            else if ((input % 2) == 0) {
                System.out.println("INVALID INPUT. YOU INPUTTED AN EVEN NUMBER, YOU MUST INPUT AN ODD NUMBER TO REPLACE [X]. IF YOU'RE HAVING TROUBLE, PLEASE SEE THE MANUAL.");
            }
            else {
                a = false; // Exit loop
                bestOf = input; // Set number of rounds for the game
            }
        }
    }
    
    public NathanielDannunzioMenu() //simple constructor
    {
        
    }
    
    // Getter method for bestOf variable
    public static int getBestOf() {
        return bestOf;
    }
    
    // Getter method for compDiff variable
    public static int getCompDiff() {
        return compDiff;
    }
    
    //Setter method for bestOf variable
    public static void setBestOf(int methodBestOf)
    {
        bestOf = methodBestOf;
    }
    
    //Setter method for compDiff variable
    public static void setCompDiff(int methodCompDiff)
    {
        compDiff = methodCompDiff;
    }
}

/*
Sample Output:
due to clearing of the screen taking up around 100 lines, only the first output will be shown

WELCOME TO TIC-TAC-TOE!!!
WOULD YOU LIKE TO PLAY AGAINST A HUMAN OR A COMPUTER?

1. HUMAN
2. COMPUTER

TYPE "1" FOR HUMAN OR "2" FOR COMPUTER.
 */