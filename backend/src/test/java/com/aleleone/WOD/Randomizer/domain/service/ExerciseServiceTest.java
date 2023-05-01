package com.aleleone.WOD.Randomizer.domain.service;

import com.aleleone.WOD.Randomizer.WodRandomizerApplication;
import com.aleleone.WOD.Randomizer.datasource.repository.ExerciseRepository;
import com.aleleone.WOD.Randomizer.domain.model.Exercise;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

import static org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace.AUTO_CONFIGURED;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;
import static org.springframework.test.annotation.DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD;

@SpringBootTest(classes = WodRandomizerApplication.class, webEnvironment = RANDOM_PORT)
@DirtiesContext(classMode = AFTER_EACH_TEST_METHOD)
@TestPropertySource(properties = {"SCOPE_SUFFIX = local"})
@AutoConfigureTestDatabase(replace = AUTO_CONFIGURED)
class ExerciseServiceTest {

    @Autowired
    ExerciseService exerciseService;
    @Autowired
    ExerciseRepository exerciseRepository;

    @Test
    @Sql({"/sql/integration.sql"})
    void test() {
        List<Exercise> mock_user_name = exerciseService.getExercisesForUsername("Mock User Name");
        System.out.print("algo");
    }
}
