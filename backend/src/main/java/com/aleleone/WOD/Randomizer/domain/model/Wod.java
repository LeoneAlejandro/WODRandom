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

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "wod")
public class Wod {
	
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;
    
    @Column(name = "wod_name", nullable = false)
    private String wodName;
    
    @ManyToOne(fetch = FetchType.LAZY) // Many Wods can belong to one AppUser
    @JoinColumn(name = "user_id") // Define the foreign key column
    @JsonIgnore
    private AppUser user; // Reference to the AppUser
    
    @ManyToMany
    @JoinTable(
		name = "saved_wods",
        joinColumns = @JoinColumn(name = "wod_id"),
        inverseJoinColumns = @JoinColumn(name = "exercise_id")
		)
    List<Exercise> exercises;
    

    public static Wod createWod(String wodName, AppUser user, List<Exercise> exercises) {
    	Wod wod = new Wod();
    	
    	wod.setExercises(exercises);
    	wod.setUser(user);
    	wod.setWodName(wodName);
    	
    	return wod;
    }
    
    public static Wod createWod(Long id, String wodName, AppUser user, List<Exercise> exercises) {
    	Wod wod = new Wod();
    	
    	wod.setId(id);
    	wod.setExercises(exercises);
    	wod.setUser(user);
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

	public AppUser getUser() {
		return user;
	}

	public void setUser(AppUser user) {
		this.user = user;
	}

	public List<Exercise> getExercises() {
		return exercises;
	}

	public void setExercises(List<Exercise> exercises) {
		this.exercises = exercises;
	}

	@Override
	public String toString() {
		return "Wod [id=" + id + ", wodName=" + wodName + ", user=" + user + ", exercises=" + exercises + "]";
	}
    
}
