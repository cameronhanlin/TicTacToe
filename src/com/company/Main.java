package com.company;

import javax.swing.*;
import java.util.Random;

public class Main {

    public static void main(String[] args) {

        Board board = new Board();
        Random rand = new Random();

        int y= rand.nextInt(2);
        char player;
        char computer;

        if(y == 0){            //initiation for which character the human is
            player = 'X';
            computer = 'O';
        }
        else{
            player = 'O';
            computer = 'X';
        }

        String finalMessage;    //initiation of other variables.
        boolean noWinner = true;
        int numMoves = 1;
        int playAgain = 0;
        int humanScore = 0;
        int computerScore = 0;
        int tieGame = 0;
        int move;

        board.setHumanChar(player);

        JOptionPane.showMessageDialog(null,"Welcome to TIC TAC TOE!\nSelect the number of the space you wish to play your mark.\nYour mark is "+player,"TIC TAC TOE",2);

        while(playAgain == 0){       //main loop for game repeat

            while (numMoves < 10 && noWinner) {                   //checks if there isnt a winner and the board isnt full
                move = board.getNextMove();   // human players move
                board.setSpace(move, player);    //changes the board tile
                noWinner = board.checkNoWinner(); //checks if there is a winner
                numMoves++;

                if (numMoves == 10 || !noWinner)    //if the board is full, ends game
                    break;

                move = board.getComputerMove();      //computer's move
                board.setSpace(move, computer);
                noWinner = board.checkNoWinner();
                numMoves++;
            }

            if(noWinner){                       //tie game
                finalMessage = "Tie Game, no winner";
                tieGame++;
            }
            else if (numMoves % 2 == 0){   //even number of moves means human won
                finalMessage = "Human  Wins!";
                humanScore++;
            }
            else{                      ///odd number of moves means computer won
                finalMessage = "Computer Wins!";
                computerScore++;
            }

            numMoves = 1;         //resets game variables
            noWinner = true;
            board.resetBoard();

            playAgain = JOptionPane.showConfirmDialog(null,finalMessage+"\n"+"Human: "+humanScore+"\nComputer: "+computerScore+"\nTie: "+tieGame+"\n"+
                    "Would you like to play again?", "TIC TAC TOE", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        }

        JOptionPane.showMessageDialog(null,"Final Score:\n"+"Human: "+humanScore+"\nComputer: "+computerScore+"\nTie: "+tieGame,"TIC TAC TOE",2);

    }
}
