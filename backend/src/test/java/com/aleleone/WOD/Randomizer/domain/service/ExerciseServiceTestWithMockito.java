package com.aleleone.WOD.Randomizer.domain.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
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

@ExtendWith(MockitoExtension.class)
class ExerciseServiceTestWithMockito {

	
	@Mock
	private ExerciseRepository exerciseRepositoryMock;
	
	@InjectMocks
	private ExerciseServiceImpl exerciseServiceMock;
	
	
	@Test
	void findByUsername() {
		List<Exercise> mockList = new ArrayList<>();
		mockList.add(new Exercise(3L, "Ale", "HSPU", ExerciseType.CARDIO));
		
		when(exerciseRepositoryMock.findByUserName("Ale")).thenReturn(mockList);
		Exercise mock_exercise = exerciseServiceMock.find("Ale", 3L);
		assertEquals(ExerciseType.CARDIO, mock_exercise.getExerciseType());
	}
	
}
