package com.application.model;


import javafx.scene.paint.Color;

public class Game {

    public static boolean isWinner(Grid cell[][],int i,int j)
    {
        if(i-1>=0 && j< cell.length && cell[i-1][j].equals(Color.LIGHTYELLOW))
            return false;
        else if(j-1>=0 && i< cell.length && cell[i][j-1].equals(Color.LIGHTYELLOW))
            return false;
        else if(i+1< cell.length && j< cell.length && cell[i+1][j].equals(Color.LIGHTYELLOW))
            return false;
        else if(j+1<cell.length && i< cell.length && cell[i][j+1].equals(Color.LIGHTYELLOW))
            return false;
        else
            return true;
    }

    public static boolean move(int i,int j,int x,int y,int size)
    {
        if(isLegal(i,j,x,y,size)){
            return true;
        }else{
            return false;
        }
    }

    public static boolean isLegal(int i,int j,int x,int y,int size)
    {
        if(x-1 >=0 && i==x-1 && y==j)
            return true;
        else if(x+1<= size && x+1==i && y==j)
            return true;
        else if( y-1>=0 && x==i && j == y-1)
            return true;
        else if(y+1<= size && x==i && j == y+1)
            return true;
        else
            return false;
    }

    public boolean player2canWin(int i, int j)
    {
        //Graph Theory game of chance
        if((i+j)%2==0)
            return false;
        return true;
    }

    public static void main(String[] args) {
        int n=3;
        Game game = new Game();
        while (n<6)
        {
            System.out.println("Board Size = "+n);
            for(int i=0;i<n;i++)
            {
                for(int j=0;j<n;j++)
                {
                    if(!game.player2canWin(i,j))
                        System.out.println("Player 1 initial move at ("+i+","+j+"): No winning strategy for Player 2");
                }
            }
            n+=1;
        }
    }
}
