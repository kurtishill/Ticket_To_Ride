package com.example.server.Model;

/**
 Created by tnels on Feb 24 2018
 **/
public class TrainCard
{
    private String color;

    public TrainCard(String color) {
        this.color = color;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public boolean equals(Object o)
    {
        if(this.getClass() != o.getClass())
            return false;
        if(this == o)
            return true;

        TrainCard t = (TrainCard)o;
        return this.color.equals(t.getColor());
    }

}