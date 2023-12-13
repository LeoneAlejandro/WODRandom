import { Formik, Form, Field, ErrorMessage } from "formik"
import { useEffect, useState } from "react"
import { useNavigate, useParams } from "react-router-dom"
import { createExerciseApi, retriveExerciseApi, updateExerciseApi } from "./api/ExerciseApiService"
import { useAuth } from "./security/AuthContext"
import '../css/ExerciseComponent.css'


export default function ExerciseComponent() {

    const { id } = useParams()
    const authContext = useAuth()
    const userId = authContext.userId
    const navigate = useNavigate()

    const [exerciseName, setExerciseName] = useState('')
    const [exerciseType, setExerciseType] = useState('')

    function retriveExercises() {
        if(id !== "-1") {
            retriveExerciseApi(userId, id)
                .then(response => {
                    setExerciseName(response.data.exerciseName)
                    setExerciseType(response.data.exerciseType)
                })
                .catch(error => console.log(error))
        }
    }

    useEffect(
        () => retriveExercises, [id]
    )

    function onSubmit(values) {
       
        const exercise = { 
            id: id, 
            // userId: userId,
            exerciseName: values.exerciseName, 
            exerciseType: values.exerciseType, 
            }

        if(id === "-1") {
            createExerciseApi(userId, exercise)
                .then(response => {
                  navigate('/exercises')
                })
                .catch(error => console.log(error))
        } else {
            updateExerciseApi(userId, id, exercise)
                .then(response => {
                    navigate('/exercises')
                })
                .catch(error => console.log(error))
        }
    }
    
    function validate(values) {
        let errors = {}
        // console.log(authContext)
        if(values.exerciseName.length < 2) {
            errors.exerciseName = 'El ejercicio debe tener más de 2 caracteres'}
        if(values.exerciseType.length < 2) {
            errors.exerciseName = 'Selecciona un tipo de ejercicio'    }
        if(values.exerciseType.length < 2 && values.exerciseName.length < 2) {
            errors.exerciseName = 'El ejercicio debe tener más de 2 caracteres y debe tener un tipo de ejercicio'}

        return errors
    }


    return(
        <div className="agregar-ejercicio">
            <h1 className="addExTitle">Agrega un ejercicio</h1>
            <div>
                <Formik initialValues={{ exerciseName: exerciseName, exerciseType: exerciseType }} 
                    enableReinitialize={true} 
                    onSubmit= {onSubmit}
                    validate= { validate }
                    validateOnChange = {false}
                    validateOnBlur = {false}
                    >
                    {(props) => (
                            <Form className="editExercise" autoComplete="off">
                                <ErrorMessage 
                                    name='exerciseName'
                                    component='div'
                                    className="alert alert-warning"
                                />
                                <fieldset className="form-group editExercise">
                                    <label>Ejercicio</label>
                                    <Field type="text" className="form-control exercise" name="exerciseName"/>
                                    <label>Tipo</label>
                                    <Field as="select" type="select" className="selectType" name="exerciseType">
                                            <option value="" disabled>Tipo</option>
                                            <option value="FUERZA">Fuerza</option>
                                            <option value="CARDIO">Cardio</option>
                                            <option value="OLY">Oly</option>
                                    </Field>
                                </fieldset>
                                <button className="button-success" type="submit">Agregar</button>
                            </Form>
                        )
                    }
                </Formik>
            </div>
        </div>
    )
}