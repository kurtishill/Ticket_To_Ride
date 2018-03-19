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

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getPointValue() {
        return pointValue;
    }

    public void setPointValue(int pointValue) {
        this.pointValue = pointValue;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public boolean isOccupied() {
        return occupied;
    }

    public void setOccupied(boolean occupied) {
        this.occupied = occupied;
    }

    public City getCity1() {
        return city1;
    }

    public City getCity2() {
        return city2;
    }

    public Player getOwner() {
        return owner;
    }

    public void setOwner(Player owner) {
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
            if(this.color.equals(r.getColor()) || this.color.equals("wild"))
            {
                if(this.city1.equals(r.getCity1()) && this.city2.equals(r.getCity2()))
                    return true;
            }
        }
        return false;
    }

    @Override
    public int hashCode() {
        return 31 * owner.hashCode() + color.hashCode();
    }

    @Override
    public String toString() {
        return "Points: " + pointValue + " - " + city1.getName() + " to " + city2.getName();
    }

}