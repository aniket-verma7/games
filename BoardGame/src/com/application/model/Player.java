package com.application.model;

import javafx.scene.control.Label;
import javafx.scene.paint.Color;

public class Player
{
    private String name;
    private boolean chance;
    private Color color;
    private Label label;

    public Player() {
    }

    public Player(String name, boolean chance, Color color) {
        this.name = name;
        this.chance = chance;
        this.color = color;
    }

    public Player(String name, boolean chance, Color color, Label label) {
        this.name = name;
        this.chance = chance;
        this.color = color;
        this.label = label;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isChance() {
        return chance;
    }

    public void setChance(boolean chance) {
        this.chance = chance;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Label getLabel() {
        return label;
    }

    public void setLabel(Label label) {
        this.label = label;
    }
}
