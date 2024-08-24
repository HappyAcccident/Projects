/*
Nathaniel D'annunzio
Date: 4/19/2024
Class: AP Computer Science A
Program Name: Tic-Tac-Toe Game
Program Description: Allows the user to play Tic-Tac-Toe against another human or a computer
Difficulties: Having the user input a box to place their symbol.
What I Learned: How to combine all the aspects taught in AP Computer Science A, such as loops, arrays, method, classes, etc.
*/

public class NathanielDannunzioTicTacToe //the main class
{
    public static void main(String[] args)
    {
        NathanielDannunzioMenu.startMenu(); //sends the user through the menu
        NathanielDannunzioGame.game(NathanielDannunzioMenu.getCompDiff()); //starts the game
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