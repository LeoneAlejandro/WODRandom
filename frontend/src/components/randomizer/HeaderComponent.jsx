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
                <nav className="navbar navbar-expand">
                    <Link className="navbarBrand" to="/welcome">Home</Link>
                    <div className="collapse navbar-collapse">
                        <ul className="navbar-nav">
                            <li className="nav-item">
                                {isAuthenticated && <Link className="nav-link-header" to="/randomize">Randomizar</Link>} 
                            </li>
                            <li className="nav-item">
                                {isAuthenticated && <Link className="nav-link-header" to="/exercises">Ejercicios</Link>}
                            </li>
                            <li className="nav-item">
                                {isAuthenticated && <Link className="nav-link-header" to="/wods">Wods</Link>}
                            </li>
                            <li className="nav-item">
                                {isAuthenticated && <Link className="nav-link-header" to="/createWod/-1">Custom Wods</Link>}
                            </li>
                        </ul>
                    </div>
                    <ul className="navbar-nav">
                        <li className="nav-items">{!isAuthenticated && <Link className="nav-link-log" to="/login">Login</Link>}</li>
                        <li className="nav-items">{isAuthenticated && <Link className="nav-link-log" to="/logout" onClick={logout}>Logout</Link>}</li>              
                    </ul>
                </nav>
        </div>
        </header>


    )
}