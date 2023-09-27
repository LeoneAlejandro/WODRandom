import { useState } from 'react'
import { useNavigate } from 'react-router-dom'
import { useAuth } from './security/AuthContext'
import wodsData from '../../assets/legacyWods.json'
import '../css/LoginComponent.css'

export default function LoginComponent() {

    const [username, setUsername] = useState('')
    const [password, setPassword] = useState('')
    const [showErrorMessage, setShowErrorMessage] = useState(false)

    const navigate = useNavigate();

    const authContext = useAuth();

    function handleUsernameChange(event) {
        setUsername(event.target.value)
    }

    function handlePasswordChange(event) {
        setPassword(event.target.value)
    }

    async function handleSubmit(){
        if(await authContext.login(username, password)) {
            navigate(`/welcome`)
        } else {
            setShowErrorMessage(true)
        }
    }

    //Legacy Wods

    const [randomWOD, setRandomWOD] = useState(null);

    const getRandomWOD = () => {
      const randomIndex = Math.floor(Math.random() * wodsData.WODs.length);
      const selectedWOD = wodsData.WODs[randomIndex];
      setRandomWOD(selectedWOD);
    }


    return(
        
        <div className='loginMenu'>

            <div className="login">
                <div className='loginCard'>
                    <h1>Iniciá sesión</h1>
                    {showErrorMessage && <div className="errorMessage">Usuario o contraseña incorrectos</div>}
                    
                    <div className="loginForm">
                        <div className="inputbox">
                            <input required="required" type="text" value={username} onChange={handleUsernameChange}/>
                            <span>Usuario</span>
                            <i></i>
                        </div>
                        <div className="inputbox">
                            <input required="required" type="password" value={password} onChange={handlePasswordChange}/>
                            <span>Contraseña</span>
                            <i></i>
                        </div>
                        <br />
                        <div>
                            <button type="button" name="login " onClick={handleSubmit}> 
                                Ingresar </button>
                        </div>
                    </div>

                </div>
            </div>

            <div className="legacyWods">
                    <h1>WODs Tradicionales</h1>
                    <button className='legacyWodsButton' onClick={getRandomWOD}>Random WOD</button>
                    {randomWOD && (
                        <div className='legacyWodsList'>
                            {/* <p>ID: {randomWOD.id}</p> */}
                            <p className='legacyWodsListName'>{randomWOD.name}</p>
                            {/* <p>Exercises:</p> */}
                            <tr >
                                {randomWOD.exercises.map((exercise, index) => (
                                <tr className='legacyWodSingleRow' key={index}>{exercise}</tr>
                                ))}
                            </tr>
                            {randomWOD.description && <p className='lWLDescription'>{randomWOD.description} </p>
                            }
                        </div>
                    )}
            </div>
        </div>
    )
}