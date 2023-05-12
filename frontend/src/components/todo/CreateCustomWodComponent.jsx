import { useState } from 'react';
import ExerciseSelectorPopup from './ExerciseSelectorPopup';
import { useAuth } from './security/AuthContext';
import { saveWod } from "./api/SaveWodService";
import { useNavigate } from "react-router-dom";
import '../css/CreateCustomWodComponent.css'

function CreateCustomWodComponent() {
  const [selectedExerciseIds, setSelectedExerciseIds] = useState([]);
  const [wodName, setWodName] = useState('')

  const navigate = useNavigate()

  const authContext = useAuth()
  const username = authContext.username

  function handleSelectExercise(exerciseId, exerciseName) {
    const exercise = {
      id: exerciseId,
      name: exerciseName
    }
    setSelectedExerciseIds((prevSelectedExerciseIds) => [...prevSelectedExerciseIds, exercise ]);
  }


  function handleSaveWod() {
    const listExerciesId = []

    selectedExerciseIds.forEach(element => {
        listExerciesId.push(element.id);
    });

    const creationExcerciseWodRequest = {
        wodName: wodName,
        exercisesId: listExerciesId
    }

    saveWod(username, creationExcerciseWodRequest)
    navigate(`/wods`)
  }

  function removeSelectedExercise(e) {
    setSelectedExerciseIds((selectedExerciseIds) => {
      const newSelectedExerciseIds = [...selectedExerciseIds];
      newSelectedExerciseIds.splice(e, 1);
      return newSelectedExerciseIds;
    });
  }
  
  return (
    <div>
      <h2>Crea tu custom WOD</h2>
      <ExerciseSelectorPopup username={username} onSelectExercise={handleSelectExercise} />
      <div className='create-custom-wod-table'>
        { selectedExerciseIds.length > 0 &&
          <table className="tableCustomWod">
            <thead className='titles-CCWC'>
              <tr >
                <th className="exerciseHeader">Ejercicio</th>
                <th >Borrar</th>
              </tr>
            </thead>
              <tbody>
                {selectedExerciseIds.map((exercise, index) => (
                  <tr className='tableCustomRows' key={index}>
                      <td value={index}>{exercise.name}</td>
                      <td><button className='buttonDeleteEx' onClick={()=>removeSelectedExercise(index)}>X</button></td>
                  </tr>
                ))}
              </tbody>
          </table>
        }

        <input className="wodName" required="required" type="text" value={wodName} onChange={(e) => setWodName(e.target.value)} placeholder="Nombre de Wod"/>
        <button className="buton" onClick={handleSaveWod} disabled={!wodName}>Guardar WOD</button>
    </div>
  </div>
  );
}

export default CreateCustomWodComponent;
