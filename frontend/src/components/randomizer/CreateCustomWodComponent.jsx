import { useEffect, useState } from 'react';
import ExerciseSelectorPopup from './ExerciseSelectorPopup';
import { useAuth } from './security/AuthContext';
import { retriveSavedWod, saveWod, updateWodById } from "./api/SaveWodService";
import { useNavigate, useParams } from "react-router-dom";
import '../css/CreateCustomWodComponent.css'

function CreateCustomWodComponent() {

  const { id } = useParams()
  const [selectedExerciseIds, setSelectedExerciseIds] = useState([])
  const [wodName, setWodName] = useState('')

  const navigate = useNavigate()

  const authContext = useAuth()
  const username = authContext.username

  useEffect(() => {
     retriveWod()
  }, [id])

  async function retriveWod() {
    if (id !== "-1") {
      try {
        const response = await retriveSavedWod(username, id);
        setSelectedExerciseIds(response.data.exercises);
        setWodName(response.data.wodName)
      } catch (error) {
        console.log(error);
      }
    } else {
      setSelectedExerciseIds('')
      setWodName('')
    }
  }

  function handleSelectExercise(exerciseId, exerciseName) {
    const exercise = {
      id: exerciseId,
      exerciseName: exerciseName
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

    if(id !== "-1") {
      updateWodById(username, id, creationExcerciseWodRequest)
        .then(() => navigate(`/wods`))
    } else {
      saveWod(username, creationExcerciseWodRequest)
        .then(() => {
          navigate(`/wods`)
        })
        .catch(error => console.log(error))
    }
  }

  function removeSelectedExercise(e) {
    setSelectedExerciseIds((selectedExerciseIds) => {
      const newSelectedExerciseIds = [...selectedExerciseIds];
      newSelectedExerciseIds.splice(e, 1);
      return newSelectedExerciseIds;
    });
  }
 
  return (
    <div className='customWod'>
      <h2 className='CustomWodh2'>Crea tu custom WOD</h2>
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
                      <td value={index}>{exercise.exerciseName}</td>
                      <td><button className='buttonDeleteEx' onClick={()=>removeSelectedExercise(index)}>X</button></td>
                  </tr>
                ))}
              </tbody>
          </table>
        }
        <div className='nameAndSave'>
        <input className="wodName" required="required" type="text" value={wodName} onChange={(e) => setWodName(e.target.value)} placeholder="Nombre de Wod"/>
        <button className="saveCustomWodButton" onClick={handleSaveWod} disabled={!wodName}>Guardar WOD</button>
        </div>
    </div>
  </div>
  );
}

export default CreateCustomWodComponent;
