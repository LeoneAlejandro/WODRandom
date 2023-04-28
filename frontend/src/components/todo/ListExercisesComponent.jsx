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
            {/* <h3>All your Exercises</h3> */}
            {message && <div className="alert alert-warning">{message}</div>}
            <div className="lista-ejercicios">
                <table className='table table-hover'>
                    <thead>
                        <tr>
                            {/* <td>Id</td> */}
                            <th className="ExercisesTitle">Ejercicio</th>
                            <th className="TypeTitle">Tipo</th>
                            <th className="UpdateTitle"></th>
                            <th className="DeleteTitle"></th>
                        </tr>
                    </thead>
                    <tbody>
                        { exercise.map(
                                exercise => (
                                    <tr key={exercise.exerciseId}>
                                        {/* <td>{exercise.exerciseId}</td> */}
                                        <td>{exercise.exerciseName}</td>
                                        <td>{exercise.exerciseType}</td>
                                        <td><button className="btn btn-success" onClick={() => updateExercise(exercise.exerciseId) }>Modificar</button></td>
                                        <td><button className="btn btn-danger" onClick={() => deleteExercise(exercise.exerciseId) }>X</button></td>
                                    </tr>
                                )
                            )
                        }
                    </tbody>
                </table>
            </div>
            <div className="btn btn-success" onClick={addExercise}>Agregar nuevo ejercicio</div>
        </div>
    )
}