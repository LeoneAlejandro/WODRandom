package com.aleleone.WOD.Randomizer.domain.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace.AUTO_CONFIGURED;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;
import static org.springframework.test.annotation.DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;

import com.aleleone.WOD.Randomizer.WodRandomizerApplication;
import com.aleleone.WOD.Randomizer.datasource.repository.ExerciseRepository;
import com.aleleone.WOD.Randomizer.domain.model.Exercise;
import com.aleleone.WOD.Randomizer.domain.model.Exercise.ExerciseType;
import com.aleleone.WOD.Randomizer.domain.service.impl.ExerciseServiceImpl;

@SpringBootTest(classes = WodRandomizerApplication.class, webEnvironment = RANDOM_PORT)
@DirtiesContext(classMode = AFTER_EACH_TEST_METHOD)
@TestPropertySource(properties = {"SCOPE_SUFFIX = local"})
@AutoConfigureTestDatabase(replace = AUTO_CONFIGURED)
//@ExtendWith(MockitoExtension.class)
class ExerciseServiceTest {

	
	@Mock
	private ExerciseRepository exerciseRepositoryMock;
	
	@InjectMocks
	private ExerciseServiceImpl exerciseServiceImplMock;
	
	@Test
	@Sql({"/sql/integration.sql"})
	void returnTypeOfExercise() {
		when(exerciseServiceImplMock.find("Ale", 1L)).thenReturn(new Exercise(1L, "Ale", "HSPU", ExerciseType.CARDIO));
		Exercise mock_user_name = exerciseServiceImplMock.find("Ale", 1L);
		assertEquals(ExerciseType.CARDIO, mock_user_name.getExerciseType());
	}

	
//    @Autowired
//    ExerciseService exerciseService;
//    @Autowired
//    ExerciseRepository exerciseRepository;
//
//    @Test
//    @Sql({"/sql/integration.sql"})
//    void test() {
//        List<Exercise> mock_user_name = exerciseService.find("Mock User Name");
//        System.out.print("algo");
//    }
	
	
//	(List<Exercise>) new Exercise( 1L, "Ale", "HSPU", ExerciseType.CARDIO )
}
