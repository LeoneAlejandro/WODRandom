package com.aleleone.WOD.Randomizer.domain.model;

import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "users")
public class AppUser implements UserDetails, Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String firstName;
	private String lastName;
	private String email;
	private String password;
	@Enumerated(EnumType.STRING)
	@Column(name = "user_role", nullable = false)
	private AppUserRole appUserRole;
	private Boolean locked = false;
	private Boolean enabled = false;
	
    @OneToMany(mappedBy = "user") // One user can have many exercises
    private List<Exercise> exercises;
    
    @OneToMany(mappedBy = "user") // One user can have many Wods
    private List<Wod> wods;


    public AppUser() {
        // Default, no-argument constructor
    }

	public AppUser(String firstName, String lastName, String email, String password,
			AppUserRole appUserRole) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.appUserRole = appUserRole;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		SimpleGrantedAuthority authority = new SimpleGrantedAuthority(appUserRole.name());
		return Collections.singleton(authority);
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return !locked;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return enabled;
	}

	
	//Getters & Setters
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public AppUserRole getAppUserRole() {
		return appUserRole;
	}

	public void setAppUserRole(AppUserRole appUserRole) {
		this.appUserRole = appUserRole;
	}

	public Boolean getLocked() {
		return locked;
	}

	public void setLocked(Boolean locked) {
		this.locked = locked;
	}

	public Boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public enum AppUserRole {
		USER,
		ADMIN
	}

	@Override
	public String getUsername() {
		return email;
	}
	
	public String getEmail() {
		return email;
	}
	
}
