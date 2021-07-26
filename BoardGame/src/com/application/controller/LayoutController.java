package com.application.controller;


import com.application.model.Game;
import com.application.model.Grid;
import com.application.model.Player;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.*;

import java.util.Arrays;


public class LayoutController {

    @FXML
    Pane pane;

    @FXML
    Button btnPlayAgain, start;

    @FXML
    TextField message, player1Name, player2Name;

    @FXML
    Label player1Label, player2Label ,playerChance;

    private int moves = 1;
    private Player player1, player2;
    private int currX = -1;
    private int currY = -1;
    private Grid cell[][];
    private Color player1Color = Color.RED,player2Color = Color.BLUE;

    public void btnPlayAgain(ActionEvent actionEvent) {
        reset();
    }

    private void reset()
    {
        moves = 1;
        currX = -1;
        currY = -1;
        message.setText("");

        player1Label.setText("Player 1");
        player2Label.setText("Player 2");

        playerChance.setText("Player 1");

        pane.getChildren().clear();
        start.setDisable(false);
        playerChance.setText("");
    }

    public void startGame(ActionEvent actionEvent) {

        String name1 = player1Name.getText();
        String name2 = player2Name.getText();

        if (name1.length() > 0 && name2.length() > 0)
        {
            player1 = new Player(name1, true, Color.RED, player1Label);
            player2 = new Player(name2, false, Color.BLUE, player2Label);

            playerChance.setText("Player 1");
            playerChance.setTextFill(player1Color);

            // pass grid size here....
            makeGird(5);
            start.setDisable(true);
        }
    }

    public void makeGird(int grid)
    {

        if (grid < 3 || grid > 12)
            grid = 8;

        int w = (int) pane.getWidth();
        int h = (int) pane.getHeight();

        int sclW = w / grid;
        int sclH = h / grid;

        cell = new Grid[grid][grid];
        for (int i = 0; i < grid; i++)
        {
            for (int j = 0; j < grid; j++)
            {
                cell[i][j] = new Grid(i, j, sclW, sclH);

                Rectangle rectangle = cell[i][j].getRectangle();

                int size = grid;

                cell[i][j].getRectangle().setOnMouseClicked(new EventHandler<MouseEvent>()
                {
                    @Override
                    public void handle(MouseEvent mouseEvent)
                    {
                        int x = (int) (rectangle.getX() / rectangle.getWidth());
                        int y = (int) (rectangle.getY() / rectangle.getHeight());

                        if (player1.isChance())
                        {
                            if(currX == -1 && currY == -1)
                            {
                                firstMove(rectangle,x,y);
                            }
                            else if (Game.move(x, y, currX, currY, size) && checkColor(rectangle))
                            {
                                playerOne(rectangle);
                                setPlayerChance(player1,player2);
                            }
                        }
                        else if (player2.isChance())
                        {
                            if (Game.move(x, y, currX, currY, size) && checkColor(rectangle))
                            {
                                playerTwo(rectangle);
                                setPlayerChance(player1,player2);
                            }
                        }

                        if(Game.isWinner(cell, x, y))
                        {
                            if(player2.isChance())
                                setWinnerMessage(player1, moves - 1, player1Label);
                            else
                                setWinnerMessage(player2, moves - 1, player2Label);
                        }
                    }
                });
                pane.getChildren().add(cell[i][j].getRectangle());
            }
        }

    }

    private boolean checkColor(Rectangle rectangle)
    {
        return rectangle.getFill() == Color.LIGHTYELLOW;
    }


    private void numInRectangle(Rectangle rectangle)
    {
        int centerX = (int) (rectangle.getX() + rectangle.getWidth() / 2);
        int centerY = (int) (rectangle.getY() + rectangle.getHeight() / 2);

        Text text = new Text(centerX, centerY, moves + "");
        text.setFont(Font.font("Verdana", FontWeight.BOLD, FontPosture.REGULAR, 20));
        text.setFill(Color.WHITE);

        pane.getChildren().add(text);

        moves += 1;
    }

    private void playerOne(Rectangle rectangle)
    {
        rectangle.setFill(Color.RED);
        numInRectangle(rectangle);
        findCurrentXY(rectangle);
    }

    private void playerTwo(Rectangle rectangle)
    {
        rectangle.setFill(Color.BLUE);
        numInRectangle(rectangle);
        findCurrentXY(rectangle);
    }

    private void setWinnerMessage(Player player, int moves, Label label)
    {
        message.setText(player.getName() + " has won in " + moves + " moves!");
        label.setText("WINNER");
        playerChance.setText("");
    }

    private void setPlayerChance(Player player1,Player player2)
    {
//        System.out.println(player1.isChance()+" "+player2.isChance());
        if(player1.isChance())
        {
            player1.setChance(false);
            player2.setChance(true);
            playerChance.setText("Player 2");
            playerChance.setTextFill(player2Color);
        }
        else if(player2.isChance())
        {
            player1.setChance(true);
            player2.setChance(false);
            playerChance.setText("Player 1");
            playerChance.setTextFill(player1Color);
        }
    }

    private void firstMove(Rectangle rectangle,int x,int y)
    {
        currX = x;
        currY = y;
        playerOne(rectangle);
        setPlayerChance(player1,player2);

    }

    private void findCurrentXY(Rectangle rectangle)
    {
        currX = (int) (rectangle.getX() / rectangle.getWidth());
        currY = (int) (rectangle.getY() / rectangle.getHeight());
    }
}
