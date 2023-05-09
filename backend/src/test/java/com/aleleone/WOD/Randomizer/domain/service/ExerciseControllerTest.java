package com.aleleone.WOD.Randomizer.domain.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
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
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;

import com.aleleone.WOD.Randomizer.WodRandomizerApplication;
import com.aleleone.WOD.Randomizer.domain.model.Exercise;
import com.aleleone.WOD.Randomizer.domain.model.Exercise.ExerciseType;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest(
		classes = WodRandomizerApplication.class, 
		webEnvironment = RANDOM_PORT)
@DirtiesContext(classMode = AFTER_EACH_TEST_METHOD)
@TestPropertySource(properties = {"SCOPE_SUFFIX = local"})
@AutoConfigureTestDatabase(replace = AUTO_CONFIGURED)
@AutoConfigureMockMvc
class ExerciseControllerTest {

    @Autowired
    private ExerciseService exerciseService;

    @Autowired
    private ObjectMapper objectMapper;
    
    
    @Autowired
    private MockMvc mvc;
   
    @Test
    @Sql({"/sql/integration.sql"})
    void findExercise() {
        Exercise mock_user_name = exerciseService.find("MockUsername", 2L);
        assertEquals(mock_user_name.getUserName(), "MockUsername");
    }

    @Test
    @Sql({"/sql/integration.sql"})
    void addExercise() throws Exception {
    	mvc.perform(get("/users/MockUsername/exercises/2").contentType(MediaType.APPLICATION_JSON))
    		.andExpect(status().isOk())
    		.andExpect(content().json("{\r\n"
    				+ "        \"id\": 2,\r\n"
    				+ "        \"userName\": \"MockUsername\",\r\n"
    				+ "        \"exerciseName\": \"Mock Exercise Name\",\r\n"
    				+ "        \"exerciseType\": \"CARDIO\"\r\n"
    				+ "    }"));
    }

    @Test
    @Sql({"/sql/integration.sql"})
    void deleteExercise() throws Exception {
    	mvc.perform(delete("/users/MockUsername/exercises/2").contentType(MediaType.APPLICATION_JSON))
    		.andExpect(status().is2xxSuccessful())
    		.andExpect(content().string(""));
    }

    @Test
    @Sql({"/sql/integration.sql"})
    void createExercise() throws Exception {
    	Exercise exercise = Exercise.createExercise("MockUsername", "MockExerciseName", ExerciseType.CARDIO);
    	
    	mvc.perform(post("/users/MockUsername/exercises").contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(exercise)))
    		.andExpect(status().isOk())
    		.andExpect(jsonPath("$.userName").value("MockUsername"))
    		.andExpect(jsonPath("$.exerciseName").value("MockExerciseName"))
    		.andExpect(jsonPath("$.exerciseType").value("CARDIO"))
    		;
    }

    @Test
    @Sql({"/sql/integration.sql"})
    void modifyExercise() throws Exception {
    	Exercise exercise = Exercise.createExercise("MockUsername", "NewMockExerciseName", ExerciseType.FUERZA);
    	
    	mvc.perform(put("/users/MockUsername/exercises/2").contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(exercise)))
    		.andExpect(status().isOk())
    		.andExpect(jsonPath("$.id").value("2"))
    		.andExpect(jsonPath("$.userName").value("MockUsername"))
    		.andExpect(jsonPath("$.exerciseName").value("NewMockExerciseName"))
    		.andExpect(jsonPath("$.exerciseType").value("FUERZA"));
    }    

}
