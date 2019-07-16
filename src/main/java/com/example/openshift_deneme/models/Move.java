package com.example.openshift_deneme.models;

import io.swagger.annotations.ApiModelProperty;

public class Move{
    
    @ApiModelProperty(notes = "Coordinate of X To Play")
    private int x;
    
    @ApiModelProperty(notes = "Coordinate of Y To Play")
    private int y;

    public Move(){
    }

    public Move(int x, int y){
        this.x = x;
        this.y = y;
    }

    public int getX(){
        return this.x;
    }

    public void setX(int x){
        this.x = x;
    }

    public int getY(){
        return this.y;
    }

    public void setY(int y){
        this.y = y;
    }

}