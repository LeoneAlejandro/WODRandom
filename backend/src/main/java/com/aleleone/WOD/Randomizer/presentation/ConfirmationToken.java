package com.aleleone.WOD.Randomizer.presentation;

import static jakarta.persistence.GenerationType.IDENTITY;

import java.time.LocalDateTime;

import com.aleleone.WOD.Randomizer.domain.model.AppUser;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class ConfirmationToken {

	@Id
    @GeneratedValue(strategy = IDENTITY)
	private Long id;
	@Column(nullable = false)
	private String token;
	@Column(nullable = false)
	private LocalDateTime createdAt;
	private LocalDateTime exipresAt;
	private LocalDateTime confirmedAt;
	@ManyToOne
	@JoinColumn(nullable = false,
				name = "app_user_id")
	private AppUser appUser;
	
	public ConfirmationToken() {
		
	}
	
	public ConfirmationToken(String token, LocalDateTime createdAt, LocalDateTime exipresAt,
			AppUser appUser) {
		super();
		this.token = token;
		this.createdAt = createdAt;
		this.exipresAt = exipresAt;
		this.appUser = appUser;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public LocalDateTime getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}
	public LocalDateTime getExipresAt() {
		return exipresAt;
	}
	public void setExipresAt(LocalDateTime exipresAt) {
		this.exipresAt = exipresAt;
	}
	public LocalDateTime getConfirmedAt() {
		return confirmedAt;
	}
	public void setConfirmedAt(LocalDateTime confirmedAt) {
		this.confirmedAt = confirmedAt;
	}

	public AppUser getAppUser() {
		return appUser;
	}

	public void setAppUser(AppUser appUser) {
		this.appUser = appUser;
	}
	
}
