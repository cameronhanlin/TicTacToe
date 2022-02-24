package com.company;

import javax.swing.JOptionPane;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Board {

    private List<Space> board = new ArrayList<>(9);
    private char[] spaces = {'1','2','3','4','5','6','7','8','9'};
    private Random rand = new Random();
    private int lastMove = 0;
    private char humanChar;

    public Board(){        //constructor
        for(int i=0; i<9; i++){
            board.add(new Space(spaces[i]));
        }
    }

    public void resetBoard(){   // clears board for repeat play
        for(int i=0; i<9; i++) {
            board.get(i).reset(spaces[i]);
        }
    }

    public void setHumanChar(char humanChar){    //so the board knows which character is the humans
        this.humanChar = humanChar;
    }

    public void setSpace(int place, char XO){
        board.get(place).setSpace(XO);
    }

    public void outputBoardSystem(){      //used during testing, not used anymore.
        System.out.println(board.get(0).getSpace()+"|"+board.get(1).getSpace()+"|"+board.get(2).getSpace());
        System.out.println(board.get(3).getSpace()+"|"+board.get(4).getSpace()+"|"+board.get(5).getSpace());
        System.out.println(board.get(6).getSpace()+"|"+board.get(7).getSpace()+"|"+board.get(8).getSpace());
        System.out.println("\n");
    }

    public int getNextMove(){        //human moves only
        Object[] options = {"1","2","3","4","5","6","7","8","9"};  //"Add 3","Add 4"};
        int x;

        do{
            x = JOptionPane.showOptionDialog(null,
                    board.get(0).getSpace()+"|"+board.get(1).getSpace()+"|"+board.get(2).getSpace()+"\n"+
                            board.get(3).getSpace()+"|"+board.get(4).getSpace()+"|"+board.get(5).getSpace()+"\n"+
                            board.get(6).getSpace()+"|"+board.get(7).getSpace()+"|"+board.get(8).getSpace()+"\n"+"                                                                                                                                                                ",
                    "Where would you like to place your "+humanChar+"?", JOptionPane.DEFAULT_OPTION,
                    JOptionPane.QUESTION_MESSAGE,null, options, options[lastMove]);
        } while(x ==  -1 || !board.get(x).isFree());


        board.get(x).switchIsFree();
        return x;
    }

    public int getComputerMove(){   //computer moves only
        int x;
        do{
            x = rand.nextInt(9);

        } while (!board.get(x).isFree());

        lastMove = x;
        board.get(x).switchIsFree();
        return x;
    }

    public boolean checkNoWinner(){               //checks all combinations for three in a row
        if(checkThreeSpace(0,1,2))
            return false;  //across top
        else if(checkThreeSpace(3,4,5))
            return false;//across middle
        else if(checkThreeSpace(6,7,8))
            return false;//across bottom
        else if(checkThreeSpace(0,3,6))
            return false;//vertical left
        else if(checkThreeSpace(1,4,7))
            return false;//vertical middle
        else if(checkThreeSpace(2,5,8))
            return false;//vertical right
        else if(checkThreeSpace(2,4,6))
            return false;//back slash
        else if(checkThreeSpace(0,4,8))
            return false;//forward slash
        else
            return true;
    }

    private boolean checkThreeSpace(int a, int b, int c){   //private method used only by checkNoWinner
        if(board.get(a).getSpace() == board.get(b).getSpace() && board.get(a).getSpace() == board.get(c).getSpace()){
            return true;
        }
        else{
            return false;
        }

    }






}
