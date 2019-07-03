package com.example.strongcore;

import java.io.Serializable;

public class Coordinates implements Serializable {

    private int index;
    private int x;
    private int y;

    Coordinates(int index, int x, int y){
        setIndex(index);
        setX(x);
        setY(y);
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
}
