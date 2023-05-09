package com.aleleone.WOD.Randomizer.domain.service;

import com.aleleone.WOD.Randomizer.WodRandomizerApplication;
import com.aleleone.WOD.Randomizer.domain.model.Exercise;
import com.aleleone.WOD.Randomizer.domain.model.Exercise.ExerciseType;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;

import static com.aleleone.WOD.Randomizer.domain.model.Exercise.createExercise;
import static org.apache.commons.lang3.StringUtils.EMPTY;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace.AUTO_CONFIGURED;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;
import static org.springframework.test.annotation.DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(
        classes = WodRandomizerApplication.class,
        webEnvironment = RANDOM_PORT)
@DirtiesContext(classMode = AFTER_EACH_TEST_METHOD)
@TestPropertySource(properties = {"SCOPE_SUFFIX = local"})
@AutoConfigureTestDatabase(replace = AUTO_CONFIGURED)
@AutoConfigureMockMvc
class ExerciseControllerTest {

    private static final String USERNAME_EXERCISES_URL = "/users/{username}/exercises";
    private static final String USERNAME_EXERCISES_ID_URL = "/users/{username}/exercises/{id}";

    @Autowired
    private ExerciseService exerciseService;

    @Autowired
    private ObjectMapper objectMapper;


    @Autowired
    private MockMvc mvc;

    @Test //Este test va en el service, podes test unitarios e integrales para el service.
    @Sql({"/sql/integration.sql"})
    void givenUserNameAndIdWhenFindThenReturnExercise() {
        Exercise mock_user_name = exerciseService.find("MockUsername", 2L);
        assertEquals(mock_user_name.getUserName(), "MockUsername");
    }

    @Test
    @Sql({"/sql/integration.sql"})
    void givenUsernameAndIdWhenGetExerciseThenReturnExercise() throws Exception {
        Exercise exercise = new Exercise(2L, "MockUsername", "Mock Exercise Name", ExerciseType.CARDIO);
        String exerciseJson = objectMapper.writeValueAsString(exercise);

        mvc.perform(get(USERNAME_EXERCISES_ID_URL, "MockUsername", "2").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(exerciseJson));
    }

    @Test
    @Sql({"/sql/integration.sql"})
    void givenUsernameAndIdWhenDeleteExerciseThenReturnStatusSuccessAndEmpty() throws Exception {
        mvc.perform(delete(USERNAME_EXERCISES_ID_URL, "MockUsername", "2").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().string(EMPTY));
    }

    @Test
    @Sql({"/sql/integration.sql"})
    void givenExerciseWhenAddExerciseThenReturnExercise() throws Exception {
        Exercise exercise = createExercise("MockUsername", "MockExerciseName", ExerciseType.CARDIO);

        mvc.perform(post(USERNAME_EXERCISES_URL, "MockUsername").contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(exercise)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.userName").value("MockUsername"))
                .andExpect(jsonPath("$.exerciseName").value("MockExerciseName"))
                .andExpect(jsonPath("$.exerciseType").value("CARDIO"))
        ;
    }

    @Test
    @Sql({"/sql/integration.sql"})
    void givenExerciseWhenUpdateThenReturnExercise() throws Exception {
        Exercise exercise = createExercise("MockUsername", "NewMockExerciseName", ExerciseType.FUERZA);

        mvc.perform(put(USERNAME_EXERCISES_ID_URL, "MockUsername", "2").contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(exercise)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value("2"))
                .andExpect(jsonPath("$.userName").value("MockUsername"))
                .andExpect(jsonPath("$.exerciseName").value("NewMockExerciseName"))
                .andExpect(jsonPath("$.exerciseType").value("FUERZA"));
    }

}
