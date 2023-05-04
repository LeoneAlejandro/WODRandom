package com.aleleone.WOD.Randomizer.domain.model;

import java.util.List;

public class RequestBodyDetails {

	private SavedWod savedWod;
	private List<SavedExercise> listOfExercisesToSave;
	

	public RequestBodyDetails() {
		super();
	}
	public RequestBodyDetails(SavedWod savedWod, List<SavedExercise> listOfExercisesToSave) {
		super();
		this.savedWod = savedWod;
		this.listOfExercisesToSave = listOfExercisesToSave;
	}
	public SavedWod getSavedWod() {
		return savedWod;
	}
	public void setSavedWod(SavedWod savedWod) {
		this.savedWod = savedWod;
	}
	public List<SavedExercise> getListOfExercisesToSave() {
		return listOfExercisesToSave;
	}
	public void setListOfExercisesToSave(List<SavedExercise> listOfExercisesToSave) {
		this.listOfExercisesToSave = listOfExercisesToSave;
	}
	@Override
	public String toString() {
		return "RequestBodyDetails [savedWod=" + savedWod + ", listOfExercisesToSave=" + listOfExercisesToSave + "]";
	}
	

	
}
