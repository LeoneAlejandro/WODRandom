import '../css/WodRandomizerApp.css'
import { BrowserRouter, Routes, Route } from 'react-router-dom'
import LogoutComponent from './LogoutComponent'
import HeaderComponent from './HeaderComponent'
import ErrorComponent from './ErrorComponent'
import WelcomeComponent from './WelcomeComponent'
import LoginComponent from './LoginComponent'
import AuthProvider, { useAuth } from './security/AuthContext'
import { Navigate } from 'react-router-dom'
import ExerciseComponent from './ExerciseComponent'
import ListExercisesComponent from './ListExercisesComponent'
import SavedWodsComponent from './SavedWodsComponent'
import CreateCustomWodComponent from './CreateCustomWodComponent'


function AuthenticatedRoute({children}) {
    const authContext = useAuth()
    if(authContext.isAuthenticated) {
        return children
    }
    return <Navigate to ="/"/>
}

export default function WodRandomizerApp() {
    return(
        <div className="WodRandomizerApp">
            <AuthProvider>
                <BrowserRouter> 
                
                <HeaderComponent />
                    <Routes>
                        <Route path='/' element={<LoginComponent />} />
                        <Route path='/login' element={<LoginComponent />}/>

                        <Route path='/welcome/:username' element={
                            <AuthenticatedRoute>
                                <WelcomeComponent />
                            </AuthenticatedRoute>
                        } />
                        <Route path='/exercises' element={
                            <AuthenticatedRoute>
                                <ListExercisesComponent />
                            </AuthenticatedRoute>
                        } />

                        <Route path='/exercises/:id' element={
                            <AuthenticatedRoute>
                                <ExerciseComponent />
                            </AuthenticatedRoute>
                        } />

                        <Route path='/wods' element={
                            <AuthenticatedRoute>
                                <SavedWodsComponent />
                            </AuthenticatedRoute>
                        } />

                        <Route path='/createwod' element={
                            <AuthenticatedRoute>
                                <CreateCustomWodComponent />
                            </AuthenticatedRoute>
                        } />

                        <Route path='/logout' element={
                            <AuthenticatedRoute>
                                <LogoutComponent />
                            </AuthenticatedRoute>
                        } />
                    
                        <Route path='*' element={<ErrorComponent />} />
                    </Routes>
                
                </BrowserRouter>
            </AuthProvider>
            <br />
        </div>
    )
}
















