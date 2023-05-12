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
	
	private List<Exercise> mockListExercises = new ArrayList<Exercise>();
	private Exercise mockExercise = new Exercise(1L, "MockUsername", "MockExerciseName", ExerciseType.CARDIO);
	
		
    @Test
    void givenUsernameWhenFindThenReturnExercises() {
    	mockListExercises.add(mockExercise);
    	when(exerciseRepositoryMock.findByUserName("MockUsername")).thenReturn(mockListExercises);
        List<Exercise> listFound = exerciseServiceMock.find("MockUsername");
        assertEquals(listFound, mockListExercises);
    }
	
	@Test
	void givenUsernameAndIdWhenFindThenReturnExercise() {
		List<Exercise> mockList = new ArrayList<>();
		Exercise mockExercise = new Exercise(3L, "MockUsername", "MockExerciseName", ExerciseType.CARDIO);
		mockList.add(mockExercise);
		
		when(exerciseRepositoryMock.findByUserName("MockUsername")).thenReturn(mockList);
		Exercise mockExerciseFound = exerciseServiceMock.find("MockUsername", 3L);
		assertEquals(mockExercise, mockExerciseFound);
	}
	
	@Test
	void givenIdNullWhenFindExerciseThenThrowsException() {
		assertThrows(EntityNotFoundException.class, () -> exerciseServiceMock.find("Ale", null));
	}
	
	@Test
	void givenUsernameNullWhenFindExerciseThenThrowsException() {
		assertThrows(EntityNotFoundException.class, () -> exerciseServiceMock.find(null, 1L));
	}
	
}
