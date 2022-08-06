package com.example.phase2;

public class Game extends Media{
    private double weight;

    public Game() {
    }

    public Game(String code, String title, int copiesAvailable, double weight) {
        super(code, title, copiesAvailable);
        setWeight(weight);
    }

    public void setWeight(double weight){
        if (weight>0)
            this.weight = weight;
        else
        throw new IllegalArgumentException("weight can't be negative");
    }
    public double getWeight() {
        return weight;
    }

    @Override
    public String toString() {
        return "Game{" +
                "weight=" + weight +
                ", code='" + code + '\'' +
                ", title='" + title + '\'' +
                ", copiesAvailable=" + copiesAvailable +
                '}';
    }
}
