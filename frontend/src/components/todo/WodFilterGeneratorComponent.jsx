import { Formik, Form, Field } from "formik"
import { useState } from "react"
import { generateWod } from "./api/WodApiService"
import { useAuth } from "./security/AuthContext"
import '../css/WodFilterGeneratorComponent.css'
import { saveWod } from "./api/SaveWodService"
import { useNavigate } from "react-router-dom"
import { retriveRandomExerciseByType } from "./api/ExerciseApiService"
import refresh from '../../assets/images/refresh.png'

export default function WodFilterGeneratorComponent() {

    const authContext = useAuth()
    const username = authContext.username
    const navigate = useNavigate()

    const [listOfExercises, setListOfExercises] = useState('')
    const [badRequest, setBadRequest] = useState(()=>false)
    const [wodName, setWodName] = useState('')
    

    function onSubmit(values) {

        const wod = { 
            exAmountFuerza: values.exAmountFuerza,
            exAmountCardio: values.exAmountCardio, 
            exAmountOly: values.exAmountOly
            }

        generateWod(username, wod)
            .then(response => {
                setListOfExercises(response.data)
            })
            .catch(error => {
                console.log(error)
                setBadRequest(true)
            })
        
        setBadRequest(false)
    }


    async function addWod() {

        const listExerciesId = []
        listOfExercises.forEach(element => {
            listExerciesId.push(element.id);
        });

        const creationExcerciseWodRequest = {
            wodName: wodName,
            exercisesId: listExerciesId
        }

        saveWod(username, creationExcerciseWodRequest)
            .then((response) => {
                navigate(`/wods`)
            })
            .catch(error => console.log(error))        
    }

    function handleWodName(event) {
        setWodName(event.target.value)
    }

    function onReRoll(index) {
        const exercise = listOfExercises[index]
        console.log(exercise)

        retriveRandomExerciseByType(username, exercise)
            .then(res => {
                console.log(res.data)
                const updatedExercises = [...listOfExercises];
                updatedExercises[index] = res.data;
                setListOfExercises(updatedExercises);
            })
            .catch(error => console.log(error))
    }
    
    return(
        <div>
            <label > Cuantos ejercicios hacemos ? </label>
            <div className="wodAmountSelector" >
                <Formik initialValues={{ exAmountFuerza: 1 , exAmountCardio: 1, exAmountOly: 1}} 
                    enableReinitialize={true} 
                    onSubmit= { onSubmit }
                    validateOnChange = {false}
                    validateOnBlur = {false}
                    >
                    {
                        (props) => (
                            <Form className="exAmounts">
                                <label > Fuerza: </label>
                                <fieldset className="form-group">
                                        <Field type="number" className="form-control" name="exAmountFuerza"/>
                                </fieldset>
                                <label > Cardio:  </label>
                                <fieldset className="form-group">
                                        <Field type="number" className="form-control" name="exAmountCardio"/>
                                </fieldset>
                                <label > Oly:  </label>
                                <fieldset className="form-group">
                                        <Field type="number" className="form-control" name="exAmountOly"/>
                                </fieldset>

                                <button className="buttonGenerate" type="submit">Generar !</button>
                            </Form>
                        )
                    }
                </Formik>
            </div>
            
            <div className="container">
                    { badRequest && 
                        <div className="alertaMuchosEjercicios">
                            Elegiste muchos ejercicios para alguna categoría
                        </div>
                    }

                    { listOfExercises && !badRequest &&
                    
                    <div className="containerTableExercises">
                        <table className='tableExercises'>
                            <thead >
                                <tr className="exerciseTitle">
                                    <th className="thExerciseWFGC">Ejercicio</th>
                                    <th >Tipo</th>
                                    <th >Cambiar</th>
                                </tr>
                            </thead>
                            <tbody>
                                { listOfExercises.map((exercise, index) => (
                                            <tr className="rowTableExercise" key={index}>
                                                <td >{exercise.exerciseName}</td>
                                                <td >{exercise.exerciseType}</td>
                                                <td><button className="reRollButton" onClick={() => onReRoll(index)}>
                                                        <img src={refresh} alt="Icon" className="icon" />
                                                    </button></td>
                                            </tr>
                                        )
                                        )
                                    }
                            </tbody>
                        </table>
                        <div>
                        </div>
                        <input className="wodName" required="required" type="text" value={wodName} onChange={handleWodName} placeholder="Nombre de Wod"/>
                        <button className="buttonGenerate" onClick={() => addWod()} disabled={!wodName}>Guardar WOD</button>
                    </div>
                    }
            </div>
         </div>    
    )
}