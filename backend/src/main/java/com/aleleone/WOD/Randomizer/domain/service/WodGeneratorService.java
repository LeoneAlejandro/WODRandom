package com.aleleone.WOD.Randomizer.domain.service;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.stereotype.Service;

import com.aleleone.WOD.Randomizer.domain.model.Exercise;

@Service
public class WodGeneratorService {

	public List<Exercise> generateWod(List<Exercise> list, int exAmountFuerza, int exAmountCardio, int exAmountOly ) {
		
		List<Exercise> ejerciciosDeFuerza =	list.stream().filter(
																p -> p.getExerciseType().equals("Fuerza"))
															.collect(Collectors.toList());;
															
		List<Exercise> ejerciciosDeCardio =	list.stream().filter(p -> p.getExerciseType().equals("Cardio"))
															.collect(Collectors.toList());;
			
		List<Exercise> ejerciciosDeOly = list.stream().filter(p -> p.getExerciseType().equals("Oly"))
															.collect(Collectors.toList());;
		
		
		int lengthFuerza = ejerciciosDeFuerza.size();
		int lengthCardio = ejerciciosDeCardio.size();
		int lengthOly = ejerciciosDeOly.size();
		
		if(lengthFuerza < exAmountFuerza || lengthCardio < exAmountCardio || lengthOly < exAmountOly) {
//			throw new IndexOutOfBoundsException(); "Elegiste muchos ejercicios para tu lista"
			return null;
		}
		
		Collections.shuffle(ejerciciosDeCardio);
		Collections.shuffle(ejerciciosDeFuerza);
		Collections.shuffle(ejerciciosDeOly);
		
		List<Exercise> exFuerzas =  ejerciciosDeFuerza.subList(0, exAmountFuerza);
		List<Exercise> exCardios = ejerciciosDeCardio.subList(0, exAmountCardio);
		List<Exercise> exOlys = ejerciciosDeOly.subList(0, exAmountOly);
		
		List<Exercise> wodGenerado = Stream.of(exFuerzas, exCardios, exOlys).flatMap(Collection::stream).collect(Collectors.toList()); 
		
		Collections.shuffle(wodGenerado);
	
		
		return wodGenerado;
	}
}
