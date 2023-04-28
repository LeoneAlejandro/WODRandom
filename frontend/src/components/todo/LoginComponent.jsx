import { useState } from 'react'
import { useNavigate } from 'react-router-dom'
import { useAuth } from './security/AuthContext'

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
            navigate(`/welcome/${username}`)
        } else {
            // console.log(authContext.login(username, password))
            // console.log(username + " " + password)
            setShowErrorMessage(true)
        }
    }


    return(
        <div className="Login">
            <br />
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
    )
}