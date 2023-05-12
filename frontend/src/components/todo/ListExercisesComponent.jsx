import { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import { deleteExerciseApi, retriveAllExercisesForUsernameApi } from "./api/ExerciseApiService";
import { useAuth } from "./security/AuthContext";
import '../css/ListExercisesComponent.css'

export default function ListExercisesComponent() {

    const authContext = useAuth()
    const username = authContext.username

    const navigate = useNavigate()

    const [exercise, setExercise] = useState([])
    const [message, setMessage] = useState(null)
    
    useEffect(
        () => refreshExercises(), []
    )

    function refreshExercises() {
        retriveAllExercisesForUsernameApi(username)
            .then(response => {
                setExercise(response.data)
            })
            .catch(error => console.log(error))
    }

    function deleteExercise(id){
        deleteExerciseApi(username, id)
            .then(
                () => {
                    setMessage(`Exercise with id: ${id} was deleted`)
                    refreshExercises()
                }
            )
            .catch(error => console.log(error))
    }

    function updateExercise(id) {
        navigate(`/exercises/${id}`)
    }

    function addExercise() {
        navigate(`/exercises/-1`)
    }


    return(
        <div className="container">
            {/* {message && <div className="alert alert-warning">{message}</div>} */}
            <div className="lista-ejercicios">
                <table className='table table-hover'>
                    <thead>
                        <tr>
                            <th className="ExercisesTitle">Ejercicio</th>
                            <th className="TypeTitle">Tipo</th>
                            <th className="UpdateTitle"></th>
                            <th className="DeleteTitle"></th>
                        </tr>
                    </thead>
                    <tbody>
                        { exercise.map(
                                exercise => (
                                    <tr key={exercise.id}>
                                        <td>{exercise.exerciseName}</td>
                                        <td>{exercise.exerciseType}</td>
                                        <td><button className="button-success" onClick={() => updateExercise(exercise.id) }>Modificar</button></td>
                                        <td><button className="button-delete" onClick={() => deleteExercise(exercise.id) }>X</button></td>
                                    </tr>
                                )
                            )
                        }
                    </tbody>
                </table>
            </div>
            <button className="button-success" onClick={addExercise}>Agregar nuevo ejercicio</button>
        </div>
    )
}