package com.aleleone.WOD.Randomizer.presentation;


public class CreationWodRequest {

    private int exAmountFuerza;
    private int exAmountCardio;
    private int exAmountOly;

    public int getExAmountFuerza() {
        return exAmountFuerza;
    }

    public void setExAmountFuerza(int exAmountFuerza) {
        this.exAmountFuerza = exAmountFuerza;
    }

    public int getExAmountCardio() {
        return exAmountCardio;
    }

    public void setExAmountCardio(int exAmountCardio) {
        this.exAmountCardio = exAmountCardio;
    }

    public int getExAmountOly() {
        return exAmountOly;
    }

    public void setExAmountOly(int exAmountOly) {
        this.exAmountOly = exAmountOly;
    }

    @Override
    public String toString() {
        return "WodRequestDetails [exAmountFuerza=" + exAmountFuerza + ", exAmountCardio=" + exAmountCardio
                + ", exAmountOly=" + exAmountOly + "]";
    }

}
