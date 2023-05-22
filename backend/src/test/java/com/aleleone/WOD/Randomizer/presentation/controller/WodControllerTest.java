package com.aleleone.WOD.Randomizer.presentation.controller;

import static org.hamcrest.Matchers.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;
import static org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace.AUTO_CONFIGURED;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;
import static org.springframework.test.annotation.DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


import org.hibernate.internal.build.AllowSysOut;
import org.hibernate.type.descriptor.java.ObjectArrayJavaType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultMatcher;

import com.aleleone.WOD.Randomizer.WodRandomizerApplication;
import com.aleleone.WOD.Randomizer.domain.model.Exercise;
import com.aleleone.WOD.Randomizer.domain.model.Wod;
import com.aleleone.WOD.Randomizer.domain.model.Exercise.ExerciseType;
import com.aleleone.WOD.Randomizer.domain.service.impl.WodServiceImpl;
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
	
    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MockMvc mvc;


    private Exercise exercise1 = new Exercise(1L, "MockUsername", "MockExerciseName1", ExerciseType.FUERZA);
    private Exercise exercise2 = new Exercise(2L, "MockUsername", "MockExerciseName2", ExerciseType.FUERZA);
//    private Exercise exercise2 = new Exercise(3L, "MockUsername", "MockExerciseName3", ExerciseType.OLY);
//    private Exercise exercise3 = new Exercise(4L, "MockUsername", "MockExerciseName4", ExerciseType.FUERZA);
//    private List<Exercise> mockExercises = Arrays.asList(exercise1, exercise2, exercise3);
//    private Wod mockWod = Wod.createWod("MockWodName", "MockUsername", mockExercises);
    private List<Long> listOfIds = Arrays.asList(1L, 2L, 3L);
    private CreationExcerciseWodRequest wodRequest = new CreationExcerciseWodRequest("MockWodname", listOfIds);
    
    @Test
    @Sql({"/sql/integration.sql"})
    @WithMockUser("MockUsername")
    public void givenWodWhenAddWodThenReturnSavedWod() throws Exception {
    	System.out.println(wodRequest);
    	String exercise1String = objectMapper.writeValueAsString(exercise1);
//    	System.out.println(exercise1String);
    	
    	MvcResult result = mvc.perform(post("/users/{username}/wods", "MockUsername").contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(wodRequest)))
    		.andExpect(status().isOk())
    		.andExpect(jsonPath("$").isNotEmpty())
    		.andExpect(jsonPath("$.exercises", hasSize(3)))
    		.andExpect(jsonPath("$.userName").value("MockUsername"))
    		.andExpect(jsonPath("$.wodName").value("MockWodname"))
    		.andExpect(jsonPath("$.exercises[0].exerciseName").value("MockExerciseName1"))
    		.andExpect(jsonPath("$.exercises[0].exerciseType").value("FUERZA"))
//			.andExpect(jsonPath("$.exercises[0]").equals(exercise1))
    		.andReturn();
 
		
//		String content = result.getResponse().getContentAsString();
//		System.out.println(content.toString());
//
//		System.out.println("post");   
    	
    }
    
    @Test
    @Sql({"/sql/integration.sql"})
    @WithMockUser("MockUsername")
    public void givenWodIdWhenGetWodThenReturnWod() throws Exception {
    	String exercise1String = objectMapper.writeValueAsString(exercise1);
    	String response = "[\r\n"
    			+ "  {\r\n"
    			+ "    \"id\": 1,\r\n"
    			+ "    \"wodName\": \"MockWodname1\",\r\n"
    			+ "    \"userName\": \"MockUsername\",\r\n"
    			+ "    \"exercises\": [\r\n"
    			+ "      {\r\n"
    			+ "        \"id\": 1,\r\n"
    			+ "        \"userName\": \"MockUsername\",\r\n"
    			+ "        \"exerciseName\": \"MockExerciseName1\",\r\n"
    			+ "        \"exerciseType\": \"FUERZA\"\r\n"
    			+ "      },\r\n"
    			+ "      {\r\n"
    			+ "        \"id\": 2,\r\n"
    			+ "        \"userName\": \"MockUsername\",\r\n"
    			+ "        \"exerciseName\": \"MockExerciseName2\",\r\n"
    			+ "        \"exerciseType\": \"CARDIO\"\r\n"
    			+ "      }\r\n"
    			+ "    ]\r\n"
    			+ "  },\r\n"
    			+ "  {\r\n"
    			+ "    \"id\": 2,\r\n"
    			+ "    \"wodName\": \"MockWodname2\",\r\n"
    			+ "    \"userName\": \"MockUsername\",\r\n"
    			+ "    \"exercises\": [\r\n"
    			+ "      {\r\n"
    			+ "        \"id\": 3,\r\n"
    			+ "        \"userName\": \"MockUsername\",\r\n"
    			+ "        \"exerciseName\": \"MockExerciseName3\",\r\n"
    			+ "        \"exerciseType\": \"OLY\"\r\n"
    			+ "      },\r\n"
    			+ "      {\r\n"
    			+ "        \"id\": 4,\r\n"
    			+ "        \"userName\": \"MockUsername\",\r\n"
    			+ "        \"exerciseName\": \"MockExerciseName4\",\r\n"
    			+ "        \"exerciseType\": \"FUERZA\"\r\n"
    			+ "      }\r\n"
    			+ "    ]\r\n"
    			+ "  }\r\n"
    			+ "]";
    	
    	MvcResult result = mvc.perform(get("/users/{username}/wods", "MockUsername"))
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
    		.andExpect(jsonPath("$[0].exercises[1].exerciseType").value("CARDIO"))
    		.andExpect(content().json(response))
    		.andReturn();
    	
//    		.andExpect(jsonPath("$.exercises[0]").value(exercise1))
//    		.andExpect(jsonPath("$.userName").value("MockUsername"))
    		
//    		String content = result.getResponse().getContentAsString();
//    		System.out.println(content.toString());
//
//    		System.out.println("get");
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

