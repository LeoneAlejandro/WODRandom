package com.aleleone.WOD.Randomizer.domain.service.impl;

import com.aleleone.WOD.Randomizer.datasource.repository.ExerciseRepository;
import com.aleleone.WOD.Randomizer.domain.model.Exercise;
import com.aleleone.WOD.Randomizer.domain.model.Wod;
import com.aleleone.WOD.Randomizer.domain.service.WodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

import static com.aleleone.WOD.Randomizer.domain.model.Exercise.ExerciseType.*;
import static java.util.Collections.shuffle;
import static java.util.stream.Collectors.toList;
import static java.util.stream.Stream.of;

@Service
public class WodServiceImpl implements WodService {

    @Autowired
    ExerciseRepository exerciseRepository;

    public List<Exercise> generateWod(String username, Wod wod) {
        int exAmountFuerza = wod.getExAmountFuerza();
        int exAmountCardio = wod.getExAmountCardio();
        int exAmountOly = wod.getExAmountOly();

        List<Exercise> exercisesByUsername = exerciseRepository.findByUsername(username);

        List<Exercise> ejerciciosDeFuerza = exercisesByUsername.stream().filter(p -> FUERZA.equals(p.getExerciseType()))
                .collect(toList());

        List<Exercise> ejerciciosDeCardio = exercisesByUsername.stream().filter(p -> CARDIO.equals(p.getExerciseType()))
                .collect(toList());

        List<Exercise> ejerciciosDeOly = exercisesByUsername.stream().filter(p -> OLY.equals(p.getExerciseType()))
                .collect(toList());


        int lengthFuerza = ejerciciosDeFuerza.size();
        int lengthCardio = ejerciciosDeCardio.size();
        int lengthOly = ejerciciosDeOly.size();

        if (lengthFuerza < exAmountFuerza || lengthCardio < exAmountCardio || lengthOly < exAmountOly) {
//			throw new IndexOutOfBoundsException(); "Elegiste muchos ejercicios para tu lista"
            return null;
        }

        shuffle(ejerciciosDeCardio);
        shuffle(ejerciciosDeFuerza);
        shuffle(ejerciciosDeOly);

        List<Exercise> exFuerzas = ejerciciosDeFuerza.subList(0, exAmountFuerza);
        List<Exercise> exCardios = ejerciciosDeCardio.subList(0, exAmountCardio);
        List<Exercise> exOlys = ejerciciosDeOly.subList(0, exAmountOly);

        List<Exercise> wodGenerado = of(exFuerzas, exCardios, exOlys)
                .flatMap(Collection::stream)
                .collect(toList());

        shuffle(wodGenerado);

        return wodGenerado;
    }
}
