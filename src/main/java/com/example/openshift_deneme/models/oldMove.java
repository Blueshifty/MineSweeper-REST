/*package com.example.openshift_deneme.models;

import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;


public class Move{
    
    @ApiModelProperty(notes = "Coordinate of X To Play")
    @NotNull
    @Min(value = 0, message = "X Coordinate Should Not Be Less Then 0")
    @Max(value = 9, message = "X Coordinate Should Not Be Greateard Then 9")
    private int x;
    
    @ApiModelProperty(notes = "Coordinate of Y To Play")
    @NotNull
    @Min(value = 0, message = "Y Coordinate Should Not Be Less Then 0")
    @Max(value = 9, message = "Y Coordinate Should Not Be Greater Then 9")
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

}*/