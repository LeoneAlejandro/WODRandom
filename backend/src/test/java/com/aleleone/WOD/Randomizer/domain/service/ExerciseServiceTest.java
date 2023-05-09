package com.aleleone.WOD.Randomizer.domain.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.aleleone.WOD.Randomizer.datasource.repository.ExerciseRepository;
import com.aleleone.WOD.Randomizer.domain.model.Exercise;
import com.aleleone.WOD.Randomizer.domain.model.Exercise.ExerciseType;
import com.aleleone.WOD.Randomizer.domain.service.impl.ExerciseServiceImpl;

import jakarta.persistence.EntityNotFoundException;

@ExtendWith(MockitoExtension.class)
class ExerciseServiceTest {

	
	@Mock
	private ExerciseRepository exerciseRepositoryMock;
	
	@InjectMocks
	private ExerciseServiceImpl exerciseServiceMock;
	
	
	@Test
	void findExerciseByUsername() {
		List<Exercise> mockList = new ArrayList<>();
		mockList.add(new Exercise(3L, "Ale", "HSPU", ExerciseType.CARDIO));
		
		when(exerciseRepositoryMock.findByUserName("Ale")).thenReturn(mockList);
		Exercise mockExerciseFound = exerciseServiceMock.find("Ale", 3L);
		assertEquals(ExerciseType.CARDIO, mockExerciseFound.getExerciseType());
	}
	
	@Test
	void when_id_is_null_ExerciseNotFoundException() {
		assertThrows(EntityNotFoundException.class, () -> exerciseServiceMock.find("Ale", null));
	}
	
	@Test
	void when_id_username_null_ExerciseNotFoundException() {
		assertThrows(EntityNotFoundException.class, () -> exerciseServiceMock.find(null, 1L));
	}
	
}
