package com.aleleone.WOD.Randomizer.domain.model;

import static jakarta.persistence.GenerationType.IDENTITY;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "exercise_wod")
public class ExerciseWod {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;
    
    @ManyToOne
    @JoinColumn(name="wod_id")
    private Wod wodId;
	
    @ManyToOne
    @JoinColumn(name="exercise_id")
    private Exercise exerciseId;
    
    
	public ExerciseWod() {
		super();
	}

	public ExerciseWod(Long id, Wod wodId, Exercise exerciseId) {
		super();
		this.id = id;
		this.wodId = wodId;
		this.exerciseId = exerciseId;
	}
	

	public void setSavedWodId(Wod wodId) {
		this.wodId = wodId;
	}


	@Override
	public String toString() {
		return "SavedExercise [id=" + id + ", savedWodId=" + wodId + ", exerciseId=" + exerciseId + "]";
	}
    
}
