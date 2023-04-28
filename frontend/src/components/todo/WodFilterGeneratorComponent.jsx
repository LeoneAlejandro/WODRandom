import { Formik, Form, Field, ErrorMessage } from "formik"
import { useState } from "react"
import { generateWod } from "./api/WodApiService"
import { useAuth } from "./security/AuthContext"
import '../css/WodFilterGeneratorComponent.css'

export default function WodFilterGeneratorComponent() {

    const authContext = useAuth()
    const username = authContext.username

    const [listOfExercises, setListOfExercises] = useState('')

    function onSubmit(values) {

        const wod = { 
            exAmountFuerza: values.exAmountFuerza,
            exAmountCardio: values.exAmountCardio, 
            exAmountOly: values.exAmountOly
            }

        console.log(wod)


        generateWod(username, wod)
            .then(response => {
                console.log(response.data)
                setListOfExercises(response.data)
            })
            .catch(error => console.log(error))

    }

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
                                                        {/* <td>{exercise.exerciseId}</td> */}
                                                        <td>{exercise.exerciseName}</td>
                                                        <td>{exercise.exerciseType}</td>
                                                    </tr>
                                                )
                                            )
                                        }
                                    </tbody>
                                </table>
                            </div>
                                }
                        </div>
                    </div>
                </div>

         </div>    
    )

}