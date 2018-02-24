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
    private List<City> cities;
    private Player owner;

    //call this constructor when initializing a game. Without player occupying route
    public Route(int length, int pointValue, String color, List<City> cities)
    {
        this.length = length;
        this.pointValue = pointValue;
        this.color = color;
        this.cities = cities;
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

    private List<City> getCities() {
        return cities;
    }

    private void setCities(List<City> cities) {
        this.cities = cities;
    }

    private Player getOwner() {
        return owner;
    }

    private void setOwner(Player owner) {
        this.owner = owner;
    }

    @Override
    public boolean equals(Object o) {
        if(this.class != o.class)
            return false;
        if(this == o)
            return true;

        Route r = (Route)o;

        if(this.length == r.getLength())
        {
            if(this.color == r.getColor())
            {
                if(this.cities.at(0).equals(r.getCities().at(0)))
                {
                    if(this.cities.at(1).equals(r.getCities().at(1)))
                        return true;
                }
            }
        }
        return false;
    }


}