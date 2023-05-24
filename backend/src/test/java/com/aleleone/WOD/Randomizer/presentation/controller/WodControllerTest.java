package com.aleleone.WOD.Randomizer.presentation.controller;

import static org.apache.commons.lang3.StringUtils.EMPTY;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace.AUTO_CONFIGURED;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;
import static org.springframework.test.annotation.DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.Arrays;
import java.util.List;

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
import com.aleleone.WOD.Randomizer.presentation.CreationExcerciseWodRequest;
import com.fasterxml.jackson.databind.ObjectMapper;

//@RunWith(SpringRunner.class)
@SpringBootTest(
        classes = WodRandomizerApplication.class,
        webEnvironment = RANDOM_PORT)
@DirtiesContext(classMode = AFTER_EACH_TEST_METHOD)
@TestPropertySource(properties = {"SCOPE_SUFFIX = local"})
@AutoConfigureTestDatabase(replace = AUTO_CONFIGURED)
@AutoConfigureMockMvc
public class WodControllerTest {
	
    private static final String USERNAME_WODS_URL = "/users/{username}/wods";
    private static final String USERNAME_WODS_ID_URL = "/users/{username}/wods/{id}";
	
    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MockMvc mvc;


//    private Exercise exercise1 = new Exercise(1L, "MockUsername", "MockExerciseName1", ExerciseType.FUERZA);
//    private Exercise exercise2 = new Exercise(2L, "MockUsername", "MockExerciseName2", ExerciseType.FUERZA);
//    private Exercise exercise2 = new Exercise(3L, "MockUsername", "MockExerciseName3", ExerciseType.OLY);
//    private Exercise exercise3 = new Exercise(4L, "MockUsername", "MockExerciseName4", ExerciseType.FUERZA);
//    private List<Exercise> mockExercises = Arrays.asList(exercise1, exercise2, exercise3);
    private List<Long> listOfIds = Arrays.asList(1L, 2L, 3L);
    private CreationExcerciseWodRequest wodRequest = new CreationExcerciseWodRequest("MockWodname", listOfIds);
    
    @Test
    @Sql({"/sql/integration.sql"})
    @WithMockUser(username = "MockUsername")
    public void givenWodWhenAddWodThenReturnSavedWod() throws Exception {
    	
    	mvc.perform(post(USERNAME_WODS_URL, "MockUsername").contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(wodRequest)))
    		.andExpect(status().isOk())
    		.andExpect(jsonPath("$").isNotEmpty())
    		.andExpect(jsonPath("$.exercises", hasSize(3)))
    		.andExpect(jsonPath("$.userName").value("MockUsername"))
    		.andExpect(jsonPath("$.wodName").value("MockWodname"))
    		.andExpect(jsonPath("$.exercises[0].exerciseName").value("MockExerciseName1"))
//			.andExpect(jsonPath("$.exercises[0]").equals(exercise1))
    		.andExpect(jsonPath("$.exercises[0].exerciseType").value("FUERZA"));    	
    }
    
    @Test
    @Sql({"/sql/integration.sql"})
    @WithMockUser(username = "MockUsername")
    public void givenWodIdWhenGetWodThenReturnWod() throws Exception {
    	
    	mvc.perform(get(USERNAME_WODS_URL, "MockUsername"))
    		.andExpect(status().is2xxSuccessful())
    		.andExpect(jsonPath("$[0].wodName").value("MockWodname1"))
    		.andExpect(jsonPath("$[1].wodName").value("MockWodname2"))
    		.andExpect(jsonPath("$[0].userName").value("MockUsername"))
            .andExpect(jsonPath("$", hasSize(2)))
            .andExpect(jsonPath("$[0].exercises", hasSize(2)))
            .andExpect(jsonPath("$[1].exercises", hasSize(2)))
    		.andExpect(jsonPath("$[0].exercises[0].id").value(1))
    		.andExpect(jsonPath("$[0].exercises[0].exerciseName").value("MockExerciseName1"))
    		.andExpect(jsonPath("$[0].exercises[0].exerciseType").value("FUERZA"))
    		.andExpect(jsonPath("$[0].exercises[1].id").value(2))
    		.andExpect(jsonPath("$[0].exercises[1].exerciseName").value("MockExerciseName2"))
//    		.andExpect(jsonPath("$.exercises[0]").value(exercise1))
    		.andExpect(jsonPath("$[0].exercises[1].exerciseType").value("CARDIO"));    		
    }
    
    @Test
    @Sql({"/sql/integration.sql"})
    @WithMockUser(username = "MockUsername")
    public void givenWodIdWhenDeleteWodThenReturnSuccessAndEmpty() throws Exception {
    	
    	mvc.perform(delete(USERNAME_WODS_ID_URL, "MockUsername", "1"))
	        .andExpect(status().is2xxSuccessful())
	        .andExpect(content().string(EMPTY));
    }
    
    
    
//    @Test
//    @WithMockUser("MockUsername")
//    public void testGetWods() throws Exception {
//        // Mock the response from the wodService
//        List<Wod> mockWods = Arrays.asList(new Wod(), new Wod());
//        when(wodService.find(anyString())).thenReturn(mockWods);
//
//        // Perform the GET request
//        mvc.perform(get("/users/{username}/wods", "MockUsername"))
//                .andExpect(status().isOk());
////                .andExpect(jsonPath("$", hasSize(2)))
////                .andExpect(jsonPath("$[0].id", notNullValue()))
////                .andExpect(jsonPath("$[1].id", notNullValue()));
//
//        // Verify that the wodService.find method was called with the correct argument
//        verify(wodService).find("MockUsername");
//    }
}

