package com.example.server.Model;

/**
 Created by tnels on Feb 24 2018
 **/
public class Route
{
    private int length;
    private int pointValue;
    private String color;
    private boolean occupied;
    private City city1;
    private City city2;
    private Player owner;

    //call this constructor when initializing a game. Without player occupying route
    public Route(int length, int pointValue, String color, City city1, City city2)
    {
        this.length = length;
        this.pointValue = pointValue;
        this.color = color;
        this.city1 = city1;
        this.city2 = city2;
        this.occupied = false;
        this.owner = null;
    }

    private int getLength() {
        return length;
    }

    private void setLength(int length) {
        this.length = length;
    }

    private int getPointValue() {
        return pointValue;
    }

    private void setPointValue(int pointValue) {
        this.pointValue = pointValue;
    }

    private String getColor() {
        return color;
    }

    private void setColor(String color) {
        this.color = color;
    }

    private boolean isOccupied() {
        return occupied;
    }

    private void setOccupied(boolean occupied) {
        this.occupied = occupied;
    }

    private City getCity1() {
        return city1;
    }

    private City getCity2() {
        return city2;
    }

    private Player getOwner() {
        return owner;
    }

    private void setOwner(Player owner) {
        this.owner = owner;
    }

    @Override
    public boolean equals(Object o) {
        if(this.getClass() != o.getClass())
            return false;
        if(this == o)
            return true;

        Route r = (Route)o;

        if(this.length == r.getLength())
        {
            if(this.color == r.getColor())
            {
                if(this.city1 == r.getCity1() && this.city2 == r.getCity2())
                    return true;
            }
        }
        return false;
    }

}