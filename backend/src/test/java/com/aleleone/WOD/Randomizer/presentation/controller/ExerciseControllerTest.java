package com.aleleone.WOD.Randomizer.presentation.controller;

import static com.aleleone.WOD.Randomizer.domain.model.Exercise.createExercise;
import static org.apache.commons.lang3.StringUtils.EMPTY;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace.AUTO_CONFIGURED;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;
import static org.springframework.test.annotation.DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;

import com.aleleone.WOD.Randomizer.WodRandomizerApplication;
import com.aleleone.WOD.Randomizer.domain.model.Exercise;
import com.aleleone.WOD.Randomizer.domain.model.Exercise.ExerciseType;
import com.fasterxml.jackson.databind.ObjectMapper;


//@SpringBootTest(
//        classes = WodRandomizerApplication.class,
//        webEnvironment = RANDOM_PORT)
//@DirtiesContext(classMode = AFTER_EACH_TEST_METHOD)
//@TestPropertySource(properties = {"SCOPE_SUFFIX = local"})
//@AutoConfigureTestDatabase(replace = AUTO_CONFIGURED)
//@AutoConfigureMockMvc
//class ExerciseControllerTest {
//
//    private static final String USERNAME_EXERCISES_URL = "/users/{username}/exercises";
//    private static final String USERNAME_EXERCISES_ID_URL = "/users/{username}/exercises/{id}";
//
//    @Autowired
//    private ObjectMapper objectMapper;
//
//    @Autowired
//    private MockMvc mvc;
//    
//
//    @Test
//    @Sql({"/sql/integration.sql"})
//    @WithMockUser(username = "MockUsername")
//    void givenUsernameAndIdWhenGetExerciseThenReturnExercise() throws Exception {
//        Exercise exercise = new Exercise(2L, "MockUsername", "MockExerciseName2", ExerciseType.CARDIO);
//
//        mvc.perform(get(USERNAME_EXERCISES_ID_URL, "MockUsername", "2").contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andExpect(content().json(objectMapper.writeValueAsString(exercise)));
//    }
//
//    
//    @Test
//    @Sql({"/sql/integration.sql"})
//    @WithMockUser(username = "MockUsername")
//    void givenUsernameWhenGetExercisesThenReturnExercises() throws Exception {
//
//    	mvc.perform(get(USERNAME_EXERCISES_URL, "MockUsername").contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$", hasSize(5)))
//                .andExpect(jsonPath("$[0].id").value("1"));
//
//    }
//
//    @Test
//    @Sql({"/sql/integration.sql"})
//    @WithMockUser(username = "MockUsername")
//    void givenExerciseWhenAddExerciseThenReturnExercise() throws Exception {
//        Exercise exercise = createExercise("MockUsername", "MockExerciseName", ExerciseType.CARDIO);
//
//        mvc.perform(post(USERNAME_EXERCISES_URL, "MockUsername").contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(exercise)))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.userName").value("MockUsername"))
//                .andExpect(jsonPath("$.exerciseName").value("MockExerciseName"))
//                .andExpect(jsonPath("$.exerciseType").value("CARDIO"));
//    }
//    
//    
//    @Test
//    @Sql({"/sql/integration.sql"})
//    @WithMockUser(username = "MockUsername")
//    void givenExerciseWhenUpdateThenReturnExercise() throws Exception {
//        Exercise exercise = createExercise("MockUsername", "NewMockExerciseName", ExerciseType.FUERZA);
//
//        mvc.perform(put(USERNAME_EXERCISES_ID_URL, "MockUsername", "2").contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(exercise)))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.id").value("2"))
//                .andExpect(jsonPath("$.userName").value("MockUsername"))
//                .andExpect(jsonPath("$.exerciseName").value("NewMockExerciseName"))
//                .andExpect(jsonPath("$.exerciseType").value("FUERZA"));
//    }
//    
//    @Test
//    @Sql({"/sql/integration.sql"})
//    @WithMockUser(username = "MockUsername")
//    void givenUsernameAndIdWhenDeleteExerciseThenReturnStatusSuccessAndEmpty() throws Exception {
//        mvc.perform(delete(USERNAME_EXERCISES_ID_URL, "MockUsername", "5").contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().is2xxSuccessful())
//                .andExpect(content().string(EMPTY));
//    }
//    
//    @Test
//    @Sql({"/sql/integration.sql"})
//    void givenUsernameNotAuthorizedWhenGetExerciseThenReturnUnauthorized() throws Exception {
//        mvc.perform(get(USERNAME_EXERCISES_ID_URL, "MockUsername", "2").contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isUnauthorized());
//    }
//
//}
