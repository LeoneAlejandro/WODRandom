import '../css/HeaderComponent.css'
import { Link } from 'react-router-dom'
import { useAuth } from './security/AuthContext'

export default function HeaderComponent() {

    const authContext = useAuth()
    const isAuthenticated = authContext.isAuthenticated

    function logout() {
        authContext.logout() 
    }

    return(
        <header className="headerRandomizer">
        <div className="container">
            {/* <div className="row"> */}
                <nav className="navbar navbar-expand">
                    <a className="navbar-brand" href="http://localhost:3000/">Randomiz</a>
                    <div className="collapse navbar-collapse">
                        <ul className="navbar-nav">
                            <li className="nav-item">
                                {isAuthenticated && <Link className="nav-link-header" to="/welcome/Ale">Inicio</Link>} 
                            </li>
                            <li className="nav-item">
                                {isAuthenticated && <Link className="nav-link-header" to="/exercises">Ejercicios</Link>}
                            </li>
                            <li className="nav-item">
                                {isAuthenticated && <Link className="nav-link-header" to="/wods">Wods</Link>}
                            </li>
                            <li className="nav-item">
                                {isAuthenticated && <Link className="nav-link-header" to="/createWod">Crear Wods</Link>}
                            </li>
                        </ul>
                    </div>
                    <ul className="navbar-nav">
                        <li className="nav-items">{!isAuthenticated && <Link className="nav-link-log" to="/login">Login</Link>}</li>
                        <li className="nav-items">{isAuthenticated && <Link className="nav-link-log" to="/logout" onClick={logout}>Logout</Link>}</li>              
                    </ul>
                </nav>
            {/* </div> */}
        </div>
        </header>


    )
}