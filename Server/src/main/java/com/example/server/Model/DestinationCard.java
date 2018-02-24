package com.example.server.Model;

/**
 Created by tnels on Feb 24 2018
 **/
public class DestinationCard
{
    private City city1;
    private City city2;
    private int pointValue;

    public DestinationCard(City city1, City city2, int pointValue) {
        this.city1 = city1;
        this.city2 = city2;
        this.pointValue = pointValue;
    }

    public City getCity1() {
        return city1;
    }

    public City getCity2() {
        return city2;
    }

    public int getPointValue() {
        return pointValue;
    }

    public void setPointValue(int pointValue) {
        this.pointValue = pointValue;
    }

    @Override
    public boolean equals(Object o)
    {
        if(this.class != o.class)
            return false;
        if(this == o)
            return true;

        DestinationCard d = (DestinationCard)o;

        if(this.pointValue = d.getPointValue())
        {
            if(this.city1 == d.getCity1() && this.city2 == d.getCity2())
                return true;
        }
        return false;
    }
}