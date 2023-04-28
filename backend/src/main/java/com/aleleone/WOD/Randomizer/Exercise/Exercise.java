package com.aleleone.WOD.Randomizer.Exercise;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Exercise {
	
	@Id
	@GeneratedValue
	private Integer exerciseId;
	private String username;
	private String exerciseName;
	private String exerciseType;
	
	
	public Exercise() {	}
	
	
	public Exercise(Integer exerciseId, String username, String exerciseName, String exerciseType) {
		super();
		this.exerciseId = exerciseId;
		this.username = username;
		this.exerciseName = exerciseName;
		this.exerciseType = exerciseType;
	}
	
		
	public Integer getExerciseId() {
		return exerciseId;
	}
	public void setExerciseId(Integer exerciseId) {
		this.exerciseId = exerciseId;
	}
	public String getExerciseName() {
		return exerciseName;
	}
	public void setExerciseName(String exerciseName) {
		this.exerciseName = exerciseName;
	}
	public String getExerciseType() {
		return exerciseType;
	}
	public void setExerciseType(String exerciseType) {
		this.exerciseType = exerciseType;
	}
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	
	@Override
	public String toString() {
		return "Exercise [exerciseId=" + exerciseId + ", exerciseName=" + exerciseName + ", exerciseType="
				+ exerciseType + "]";
	}
}
