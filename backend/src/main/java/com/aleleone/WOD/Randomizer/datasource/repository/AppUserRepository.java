package com.aleleone.WOD.Randomizer.datasource.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.aleleone.WOD.Randomizer.domain.model.AppUser;
import com.aleleone.WOD.Randomizer.domain.model.AppUserDTO;

@Repository
@Transactional(readOnly = true)
public interface AppUserRepository extends JpaRepository<AppUser, Long> {
	
	Optional<AppUser> findByEmail(String email);
	
	@Query(value = """
	 			SELECT	u.id as userid,
	 		 			u.first_name as firstname,
	 		 			u.last_name as lastname,
	 		 			u.email as email 
	 		 			FROM users AS u WHERE u.id = :id
	            """,
	    nativeQuery = true)
	AppUserDTO finAppUserDTOsByUserId(@Param("id") Long id);

    @Transactional
    @Modifying
    @Query("UPDATE AppUser a " +
            "SET a.enabled = TRUE WHERE a.email = ?1")
    int enableAppUser(String email);
}
