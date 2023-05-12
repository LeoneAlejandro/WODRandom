import { useState, useEffect } from 'react';
import {retriveAllExercisesForUsernameApi} from './api/ExerciseApiService';
import '../css/ExerciseSelectorPopup.css'


function ExerciseSelectorPopup( { username, onSelectExercise }) {
  const [exercises, setExercises] = useState([]);
  const [showPopup, setShowPopup] = useState(false);


  useEffect(() => {
    async function fetchExercises() {
      const response = await retriveAllExercisesForUsernameApi(username);
      setExercises(response.data);
      console.log(response.data)
    }
    fetchExercises();
  }, [username]);


  function handleSelectExercise(exerciseId, exerciseName) {
    setShowPopup(false);
    onSelectExercise(exerciseId, exerciseName);
  }

  return (
    <>
      <button className='select-exercise-esp' onClick={() => setShowPopup(true)}>Select Exercise</button>
      {showPopup && (
        <div className='background' onClick={() => setShowPopup(false)}>
          <div className="popup">
            <div className="popup-content">
              <h2 className='title-esp'>Lista de ejercicios</h2>
              <ul className='list-exercises'>
                {exercises.map((exercise) => (
                  <li className='exercises' key={exercise.id} onClick={() => handleSelectExercise(exercise.id, exercise.exerciseName)}>
                    {exercise.exerciseName}
                  </li>
                ))}
              </ul>
            </div>
              <button className='closePopup' onClick={() => setShowPopup(false)}>Close</button>
          </div>
        </div>
      )}
    </>
  );
}

export default ExerciseSelectorPopup;