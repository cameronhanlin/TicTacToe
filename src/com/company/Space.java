package com.company;

public class Space {

    private char space;
    private boolean isFree;

    public Space(char space){   //sets the space Char
        reset(space);
    }

    public void reset(char space){   //used for game resetting.
        this.space = space;
        isFree = true;
    }

    public void setSpace(char space) {
        this.space = space;
    }

    public char getSpace() {
        return space;
    }

    public boolean isFree() {
        return isFree;
    }

    public boolean switchIsFree(){
        return isFree = !isFree;
    }
}
