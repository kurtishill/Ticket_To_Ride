package com.example.server.Model;

/**
 Created by tnels on Feb 24 2018
 **/
public class City
{

    private String name;
    private float x;
    private float y;

    public City(String name, float x, float y)
    {
        this.name = name;
        this.x = x;
        this.y = y;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    @Override
    public boolean equals(Object o)
    {
        if(this.class != o.class)
            return false;
        if(this == o)
            return true;

        City c = (City)o;

        if(this.name == c.getName())
        {
            if(this.x == c.getX())
            {
                if(this.y == c.getY())
                {
                    return true;
                }
            }
        }
        return false;
    }


}