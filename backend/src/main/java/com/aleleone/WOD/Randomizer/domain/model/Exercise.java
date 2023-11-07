package com.aleleone.WOD.Randomizer.domain.model;


import static jakarta.persistence.GenerationType.IDENTITY;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "exercise")
public class Exercise {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

//    @Column(name = "user_name", nullable = false)
//    private String userName;
    @Column(name = "exercise_name", nullable = false)
    private String exerciseName;
    @Column(name = "exercise_type", nullable = false)
    private ExerciseType exerciseType;
    
    @ManyToOne // Many exercises can belong to one user
    @JoinColumn(name = "user_id") // Define the foreign key column
    private AppUser user; // Reference to the user

    @ManyToMany(mappedBy = "exercises")
    List<Wod> savedWods;

    public Exercise() {
    }


    public Exercise(Long id, AppUser user, String exerciseName, ExerciseType exerciseType) {
        super();
        this.id = id;
        this.user = user;
        this.exerciseName = exerciseName;
        this.exerciseType = exerciseType;
    }

    public static Exercise createExercise(AppUser user, String exerciseName, ExerciseType exerciseType) {
    	Exercise exercise = new Exercise();
    	
    	exercise.setUser(user);
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

    public AppUser getUser() {
        return user;
    }

    public void setUser(AppUser user) {
        this.user= user;

    }

    @Override
    public String toString() {
        return "Exercise{" +
                "id=" + id +
                ", userName='" + user+ '\'' +
                ", exerciseName='" + exerciseName + '\'' +
                ", exerciseType=" + exerciseType +
                '}';
    }

    
    public enum ExerciseType {
        FUERZA,
        CARDIO,
        OLY
    }


}
