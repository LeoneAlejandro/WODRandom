package com.aleleone.WOD.Randomizer.domain.model;

import static jakarta.persistence.GenerationType.IDENTITY;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
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

    public static Wod createWod(String wodName, String userName) {
    	Wod wod = new Wod();
    	
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


	@Override
	public String toString() {
		return "SavedWod [id=" + id + ", wodName=" + wodName + ", userName=" + userName
				+ "]";
	}
    
}
