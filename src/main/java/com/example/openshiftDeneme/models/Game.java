package com.example.openshiftDeneme.models;

import org.springframework.data.annotation.Id;

import io.swagger.annotations.ApiModelProperty;

public class Game{

    @Id
    @ApiModelProperty(notes = "Database generated Game ID")
    private String id;
    @ApiModelProperty(notes = "Matrix That Mines are Visible")
    private char[][] board;
    @ApiModelProperty(notes = "Matrix That Mines are Invisible")
    private char[][] invisBoard;

    public Game(){
        this.board = new char[10][10];
        this.invisBoard= new char[10][10];
    }

    public String getId(){
        return this.id;
    }
 
    public void setId(String id){
        this.id = id;
    }

    public char[][] getInvisBoard(){
        return this.invisBoard;
    }

    public void setInvisBoard(char[][] invisBoard){
        this.invisBoard = invisBoard;
    }

    public char[][] getBoard(){
        return this.board;
    }

    public void setBoard(char[][] board){
        this.board = board;
    }
    
}