package com.example.ai1;

public class Population {




    private int fitness;
    private String gines;
    public Population(int fitness, String gines) {
        this.fitness = fitness;
        this.gines = gines;
    }

    public Population() {
        this.fitness = 0;
        this.gines = "";
    }
    public int getFitness() {
        return fitness;
    }

    public void setFitness(int fitness) {
        this.fitness = fitness;
    }

    public String getGenes() {
        return gines;
    }

    public void setGenes(String gines) {
        this.gines = gines;
    }
}