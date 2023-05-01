package com.aleleone.WOD.Randomizer.domain.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

import static jakarta.persistence.EnumType.STRING;

@Entity
public class Exercise {

    @Id
    @GeneratedValue
    private Integer exerciseId;
    private String username;
    private String exerciseName;
    @Enumerated(STRING)
    private ExerciseType exerciseType;


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

    public ExerciseType getExerciseType() {
        return exerciseType;
    }

    public void setExerciseType(ExerciseType exerciseType) {
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

    public enum ExerciseType {
        FUERZA,
        CARDIO,
        OLY
    }
}
