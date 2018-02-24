package com.example.server.Model;

/**
 Created by tnels on Feb 24 2018
 **/
public class DestinationCard
{
    private List<Route> routePath;
    private int pointValue;

    public DestinationCard(List<Route> routePath, int pointValue) {
        this.routePath = routePath;
        this.pointValue = pointValue;
    }

    public List<Route> getRoutePath() {
        return  routePath;
    }

    public void setRoutePath(List<Route> routePath) {
        this.routePath = routePath;
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
            if(this.routePath.equals(d.getRoutePath()))
                return true;
        }
        return false;
    }
}