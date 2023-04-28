// import { useParams, useNavigate } from 'react-router-dom'
import  WodFilterGeneratorComponent  from './WodFilterGeneratorComponent'

export default function WelcomeComponent() {

    // const { username } = useParams()

    // const navigate = useNavigate()

    // function toExercises() {
    //     navigate('/exercises')
    // }

    return(
        <div>
            <div className="WelcomeComponent">
                {/* <h1>{username} ! Bienvenido al generador de WODs</h1> */}
                <h1>Vamos a generar un WOD</h1>

                <WodFilterGeneratorComponent />

                <br />

            </div>

            {/* <div className='footer'>
                    Para ver la lista de ejercicios: <button className="btn btn-danger m-2" onClick={toExercises} >Exercises</button>
            </div> */}

        </div>
    )
}