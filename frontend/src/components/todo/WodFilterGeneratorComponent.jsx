import { Formik, Form, Field, ErrorMessage } from "formik"
import { useState } from "react"
import { generateWod } from "./api/WodApiService"
import { useAuth } from "./security/AuthContext"
import '../css/WodFilterGeneratorComponent.css'
import { saveWod } from "./api/SaveWodService"

export default function WodFilterGeneratorComponent() {

    const authContext = useAuth()
    const username = authContext.username

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
                if(!response.data) {
                    setBadRequest(true)
                }
                setListOfExercises(response.data)
            })
            .catch(error => console.log(error))
        
        setBadRequest(false)
    }


    function addWod() {

        // const listOfExercisesToSave = [];



        // const savedWod = {
        //     id: null,
        //     wodName: wodName,
        //     userName: username,
        // }
        const listExerciesId = []
        listOfExercises.forEach(element => {
            listExerciesId.push(element.id);
        });

        const creationExcerciseWodRequest = {
            wodName: wodName,
            exercisesId: listExerciesId
        }

        saveWod(username, creationExcerciseWodRequest)

        // const requestBody = { savedWod, listOfExercisesToSave }

        // saveWod(username, requestBody)
        //     .then(
        //         // response => 
        //         // console.log(response.data)
        //         )
        //     .catch(error => console.log(error.data))

        console.log(creationExcerciseWodRequest)

    }



    function handleWodName(event) {
        setWodName(event.target.value)
    }


    
    return(
        <div>
            <div className="container" >
                <div className="row justify-content-md-center" >
                    <div className="col-sm-10" >
                    <label > Cuantos ejercicios hacemos ? </label>


                            <Formik initialValues={{ exAmountFuerza: 1 , exAmountCardio: 1, exAmountOly: 1}} 
                                enableReinitialize={true} 
                                onSubmit= { onSubmit }
                                validate= { validate }
                                validateOnChange = {false}
                                validateOnBlur = {false}
                                >
                                {
                                    (props) => (
                                        <Form className="exAmountCardio">
                                            <ErrorMessage 
                                                name='exAmountCardio'
                                                component='div'
                                                className="alert alert-danger"
                                            />
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

                                            <button className="button" type="submit">Generar !</button>
                                        </Form>
                                    )
                                }
                            </Formik>
                    </div>
                </div>
            </div>
            
            <div className="container">
                <div className="row">
                    <div className="col-sm-12">
                        </div>
                            <div>
                                { badRequest && 
                                    <div className="alert alert-danger">
                                        Elegiste muchos ejercicios para alguna categoría
                                    </div>
                                }
                                { listOfExercises && 
                                
                                <div className="tabla-ejercicios">
                                <table className='table table-hover'>
                                    <thead>
                                        <tr>
                                            <th className="ExerciseTitle">Ejercicio</th>
                                            <th>Tipo</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        { listOfExercises.map(
                                                exercise => (
                                                    <tr key={listOfExercises.exerciseId}>
                                                        <td>{exercise.exerciseName}</td>
                                                        <td>{exercise.exerciseType}</td>
                                                    </tr>
                                                )
                                                )
                                            }
                                    </tbody>
                                </table>
                                <div>
                                    <input className="wodName" required="required" type="text" value={wodName} onChange={handleWodName} placeholder="Nombre de Wod"/>
                                    <button className="buton" onClick={() => addWod()} disabled={!wodName}>Guardar WOD</button>
                                </div>
                            </div>
                                }
                        </div>
                    </div>
                </div>

         </div>    
    )


    function validate(values) {
        let errors = {}

        const total = values.exAmountCardio + values.exAmountFuerza + values.exAmountOly

        if( total < 1 
            || values.exAmountCardio < 0 
            || values.exAmountFuerza < 0 
            || values.exAmountOly < 0) 
            {
                errors.exAmountCardio = 'Debes elegir al menos un ejercicio'
            }
        return errors
    }

}