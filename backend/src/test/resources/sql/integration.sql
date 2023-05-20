-- Insert data into the exercise table
INSERT INTO exercise (exercise_name, exercise_type, user_name) VALUES ('MockExerciseName1', 0, 'MockUsername');
INSERT INTO exercise (exercise_name, exercise_type, user_name) VALUES ('MockExerciseName2', 1, 'MockUsername');
INSERT INTO exercise (exercise_name, exercise_type, user_name) VALUES ('MockExerciseName3', 2, 'MockUsername');
INSERT INTO exercise (exercise_name, exercise_type, user_name) VALUES ('MockExerciseName4', 0, 'MockUsername');
INSERT INTO exercise (exercise_name, exercise_type, user_name) VALUES ('MockExerciseName5', 1, 'MockUsername'); --Ejercicio sin FK en saved_wods para delete

-- Insert data into the wod table
INSERT INTO wod (user_name, wod_name) VALUES ('MockUsername', 'MockWodname1');
INSERT INTO wod (user_name, wod_name) VALUES ('MockUsername', 'MockWodname2');

-- Insert data into the saved_wods table
INSERT INTO saved_wods (exercise_id, wod_id) VALUES ((SELECT id FROM exercise WHERE exercise_name = 'MockExerciseName1'), (SELECT id FROM wod WHERE wod_name = 'MockWodname1'));
INSERT INTO saved_wods (exercise_id, wod_id) VALUES ((SELECT id FROM exercise WHERE exercise_name = 'MockExerciseName2'), (SELECT id FROM wod WHERE wod_name = 'MockWodname1'));

INSERT INTO saved_wods (exercise_id, wod_id) VALUES ((SELECT id FROM exercise WHERE exercise_name = 'MockExerciseName3'), (SELECT id FROM wod WHERE wod_name = 'MockWodname2'));
INSERT INTO saved_wods (exercise_id, wod_id) VALUES ((SELECT id FROM exercise WHERE exercise_name = 'MockExerciseName4'), (SELECT id FROM wod WHERE wod_name = 'MockWodname2'));

