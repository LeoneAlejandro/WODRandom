package com.aleleone.WOD.Randomizer.domain.model;

import static jakarta.persistence.GenerationType.IDENTITY;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "saved_exercise")
public class SavedExercise {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;
    
    @ManyToOne
    @JoinColumn(name="wod_id")
    private SavedWod savedWodId;
	
    @ManyToOne
    @JoinColumn(name="exercise_id")
    private Exercise exerciseId;
    
    
	public SavedExercise() {
		super();
	}

	public SavedExercise(Long id, SavedWod savedWodId, Exercise exerciseId) {
		super();
		this.id = id;
		this.savedWodId = savedWodId;
		this.exerciseId = exerciseId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public SavedWod getSavedWodId() {
		return savedWodId;
	}

	public void setSavedWodId(SavedWod savedWodId) {
		this.savedWodId = savedWodId;
	}

	public Exercise getExerciseId() {
		return exerciseId;
	}

	public void setExerciseId(Exercise exerciseId) {
		this.exerciseId = exerciseId;
	}

	@Override
	public String toString() {
		return "SavedExercise [id=" + id + ", savedWodId=" + savedWodId + ", exerciseId=" + exerciseId + "]";
	}


    
}
