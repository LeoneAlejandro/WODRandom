import { useState, useEffect } from 'react';
import {retriveAllExercisesForUsernameApi} from './api/ExerciseApiService';
import '../css/ExerciseSelectorPopup.css'


function ExerciseSelectorPopup( { username, onSelectExercise }) {
  const [exercises, setExercises] = useState([]);
  const [showPopup, setShowPopup] = useState(false);
  const [searchQuery, setSearchQuery] = useState('');


  useEffect(() => {
    async function fetchExercises() {
      const response = await retriveAllExercisesForUsernameApi(username);
      setExercises(response.data);
      console.log(response.data)
    }
    fetchExercises();
  }, [username]);


  function handleSelectExercise(exerciseId, exerciseName) {
    if(!window.event.ctrlKey) {
      setShowPopup(false);
    }
    onSelectExercise(exerciseId, exerciseName);
  }

  function handleOpenPopup() {
    setShowPopup(true)
    setSearchQuery('')
  }

  

  return (
    <>
      <button className='select-exercise-esp' onClick={handleOpenPopup}>Elegir ejercicios</button>
      {showPopup && (
        <div className='background'>
          <div className="popup">
            <div className="popup-content">
              <h2 className='title-esp'>Lista de ejercicios</h2>
              <h10>(mantené presionado ctrl para elección multiple)</h10>
              <input className='searchBar' type="text" placeholder="Search exercises..." value={searchQuery} onChange={(e) => setSearchQuery(e.target.value)} />
              <ul className='list-exercises'>
                {exercises.filter(exercise => exercise.exerciseName.toLowerCase().includes(searchQuery.toLowerCase())).map((exercise) => (
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