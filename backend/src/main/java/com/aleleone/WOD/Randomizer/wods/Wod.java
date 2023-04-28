package com.aleleone.WOD.Randomizer.wods;

public class Wod {
			
	public Wod() {	}

	public Wod(int exerciseAmount, String exerciseType) {
		super();
		this.exerciseAmount = exerciseAmount;
		this.exerciseType = exerciseType;
	}
	
	private int exerciseAmount;
	private String exerciseType;

	public int getExerciseAmount() {
		return exerciseAmount;
	}
	public void setExerciseAmount(int exerciseAmount) {
		this.exerciseAmount = exerciseAmount;
	}
	public String getExerciseType() {
		return exerciseType;
	}
	public void setExerciseType(String exerciseType) {
		this.exerciseType = exerciseType;
	}
	
	@Override
	public String toString() {
		return "Wod [exerciseAmount=" + exerciseAmount + ", exerciseType=" + exerciseType + "]";
	}

	
	
}
