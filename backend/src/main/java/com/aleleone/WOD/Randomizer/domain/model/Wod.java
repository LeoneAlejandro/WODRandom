package com.aleleone.WOD.Randomizer.domain.model;

//									┌──────────────────┐
//									│      wod         │
//									├──────────────────┤
//									│  id              │
//									│  wod_name        │
//									│  user_name       │
//									└────────┬─────────┘
//									         │n
//									         │
//									         │1
//	┌────────────────────┐         ┌─────────┴─────────┐
//	│     exercise       │         │  exercise_wod     │
//	├────────────────────┤         ├───────────────────┤
//	│  id                ├─────────┤ id                │
//	│  user_name         │n       1│ wod_id            │
//	│  exercise_name     │         │ exercise_id       │
//	└────────────────────┘         └───────────────────┘

import static jakarta.persistence.GenerationType.IDENTITY;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "wod")
public class Wod {
	
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;
    
    @Column(name = "wod_name", nullable = false)
    private String wodName;
    @Column(name = "user_name", nullable = false)
    private String userName;
    
    @ManyToMany
    @JoinTable(
		name = "saved_wods",
//		joinColumns = @JoinColumn(name = "exercise_id"),
//		inverseJoinColumns = @JoinColumn(name = "wod_id")
        joinColumns = @JoinColumn(name = "wod_id"),
        inverseJoinColumns = @JoinColumn(name = "exercise_id")
		)
    List<Exercise> exercises;
    

    public static Wod createWod(String wodName, String userName, List<Exercise> exercises) {
    	Wod wod = new Wod();
    	
    	wod.setExercises(exercises);
    	wod.setUserName(userName);
    	wod.setWodName(wodName);
    	
    	return wod;
    }
    
    public static Wod createWod(Long id, String wodName, String userName, List<Exercise> exercises) {
    	Wod wod = new Wod();
    	
    	wod.setId(id);
    	wod.setExercises(exercises);
    	wod.setUserName(userName);
    	wod.setWodName(wodName);
    	
    	return wod;
    }
    

	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getWodName() {
		return wodName;
	}


	public void setWodName(String wodName) {
		this.wodName = wodName;
	}


	public String getUserName() {
		return userName;
	}


	public void setUserName(String userName) {
		this.userName = userName;
	}

	
	
	public List<Exercise> getExercises() {
		return exercises;
	}

	public void setExercises(List<Exercise> exercises) {
		this.exercises = exercises;
	}

	@Override
	public String toString() {
		return "SavedWod [id=" + id + ", wodName=" + wodName + ", userName=" + userName
				+ "]";
	}
    
}
