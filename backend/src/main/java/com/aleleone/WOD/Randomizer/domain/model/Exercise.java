package com.aleleone.WOD.Randomizer.domain.model;


import static jakarta.persistence.GenerationType.IDENTITY;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "exercise")
public class Exercise {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @Column(name = "user_name", nullable = false)
    private String userName;
    @Column(name = "exercise_name", nullable = false)
    private String exerciseName;
    @Column(name = "exercise_type", nullable = false)
    private ExerciseType exerciseType;

    @ManyToMany(mappedBy = "exercises")
    List<Wod> savedWods;

    public Exercise() {
    }


    public Exercise(Long id, String userName, String exerciseName, ExerciseType exerciseType) {
        super();
        this.id = id;
        this.userName = userName;
        this.exerciseName = exerciseName;
        this.exerciseType = exerciseType;
    }

    public static Exercise createExercise(String userName, String exerciseName, ExerciseType exerciseType) {
    	Exercise exercise = new Exercise();
    	
    	exercise.setUserName(userName);
    	exercise.setExerciseName(exerciseName);
    	exercise.setExerciseType(exerciseType);
    	
    	return exercise;
    }
    

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;

    }

    public String getExerciseName() {
        return exerciseName;
    }

    public void setExerciseName(String exerciseName) {
        this.exerciseName = exerciseName;
    }

    public ExerciseType getExerciseType() {
        return exerciseType;
    }

    public void setExerciseType(ExerciseType exerciseType) {
        this.exerciseType = exerciseType;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;

    }

    @Override
    public String toString() {
        return "Exercise [exerciseId=" + id + ", exerciseName=" + exerciseName + ", exerciseType="
                + exerciseType + "]";
    }
    
    public enum ExerciseType {
        FUERZA,
        CARDIO,
        OLY
    }


}
