package com.aleleone.WOD.Randomizer.domain.model;

import jakarta.persistence.*;

import static jakarta.persistence.GenerationType.IDENTITY;

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
    private String exerciseType;


    public Exercise() {
    }


    public Exercise(Long id, String userName, String exerciseName, String exerciseType) {
        super();
        this.id = id;
        this.userName = userName;
        this.exerciseName = exerciseName;
        this.exerciseType = exerciseType;
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

    public String getExerciseType() {
        return exerciseType;
    }

    public void setExerciseType(String exerciseType) {
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
}
