package com.aleleone.WOD.Randomizer.presentation;

import java.util.List;

public class CreationExcerciseWodRequest {

	private String wodName;
	private List<Long> exercisesId;
	
	public CreationExcerciseWodRequest(String wodName, List<Long> exercisesId) {
		super();
		this.wodName = wodName;
		this.exercisesId = exercisesId;
	}

	public String getWodName() {
		return wodName;
	}

	public void setWodName(String wodName) {
		this.wodName = wodName;
	}

	public List<Long> getExercisesId() {
		return exercisesId;
	}

	public void setExercisesId(List<Long> exercisesId) {
		this.exercisesId = exercisesId;
	}

	@Override
	public String toString() {
		return "CreationExcerciseWodRequest [wodName=" + wodName + ", exercisesId=" + exercisesId + "]";
	}
	
}
