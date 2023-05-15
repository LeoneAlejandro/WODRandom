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
    const [searchQuery, setSearchQuery] = useState('');
    
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
        <div className="containerExerciseList">
            {/* <div className="searchBarContainer"> */}
                <input className='searchExercisesBar' type="text" placeholder="Buscar ejercicios..." value={searchQuery} onChange={(e) => setSearchQuery(e.target.value)} />
            {/* </div> */}
            {/* {message && <div className="alert alert-warning">{message}</div>} */}
            <div className="listExercises">
                <table className='tablaEjercicios'>
                    {/* <div> */}
                        <thead>
                            <tr className="tableTitles">
                                <th className="ExercisesTitle">Ejercicio</th>
                                <th className="TypeTitle">Tipo</th>
                                <th className="UpdateTitle"></th>
                                <th className="DeleteTitle"></th> 
                            </tr>
                        </thead>

                        <tbody>
                            { exercise.filter(exercise => exercise.exerciseName.toLowerCase().includes(searchQuery.toLowerCase())).map(
                                    exercise => (
                                        <tr className="tableRows" key={exercise.id}>
                                            <td>{exercise.exerciseName}</td>
                                            <td>{exercise.exerciseType}</td>
                                            <td><button className="buttonModify" onClick={() => updateExercise(exercise.id) }>Modificar</button></td>
                                            <td><button className="buttonDelete" onClick={() => deleteExercise(exercise.id) }>X</button></td>
                                        </tr>
                                    )
                                )
                            }
                        </tbody>
                    {/* </div> */}
                </table>
            <button className="buttonModify" onClick={addExercise}>Agregar nuevo ejercicio</button>
            </div>
        </div>
    )
}