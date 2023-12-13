import { useState, useEffect, useRef } from 'react';
import {retriveAllExercisesForUsernameApi} from './api/ExerciseApiService';
import '../css/ExerciseSelectorPopup.css'


function ExerciseSelectorPopup( { userId, onSelectExercise }) {

  const modalRef = useRef();
  const [exercises, setExercises] = useState([]);
  const [showPopup, setShowPopup] = useState(false);
  const [searchQuery, setSearchQuery] = useState('');


  useEffect(() => {
    async function fetchExercises() {
      const response = await retriveAllExercisesForUsernameApi(userId);
      setExercises(response.data);
    }
    fetchExercises();
  }, [userId]);


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

  function closeModal(e) {
    if(modalRef.current === e.target) {
      setShowPopup(false)
    }
  }


  return (
    <>
      <button className='select-exercise-esp' onClick={handleOpenPopup}>Elegir ejercicios</button>
      {showPopup && (
        <div className='background' ref={modalRef} onClick={closeModal}>
          <div className="popup">
            <div className="popup-content">
              <h2 className='title-esp'>Lista de ejercicios</h2>
              <p className='selectMultipleExText'>(mantené presionado ctrl para elección multiple)</p>
              <input className='searchBar' type="text" placeholder="Buscar ejercicios..." value={searchQuery} onChange={(e) => setSearchQuery(e.target.value)} />
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