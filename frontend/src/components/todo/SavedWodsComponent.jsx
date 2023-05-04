import { useEffect, useState } from "react"
import { useAuth } from "./security/AuthContext"
import { retrevieSavedWodsForUsername } from "./api/SaveWodService"
import '../css/SavedWodsComponent.css'

export default function SavedWodsComponent() {

    const authContext = useAuth()
    const username = authContext.username

    const [wods, setWods] = useState('')
    const [savedWod, setSavedWod] = useState('')

    useEffect(
        () => { refreshSavedWods() }, []
    )

    function refreshSavedWods() {
        retrevieSavedWodsForUsername(username)
            .then(response => 
                setSavedWod(response.data),
                console.log(savedWod)
                )
            .catch(error => console.log(error)) 
    }

    function getSelectedWod(id) {
        // retrevieSavedWodsForUsername(username)
        //     .then(response => 
        //         setSavedWod(response.data),
        //         console.log(savedWod)
        //         )
        //     .catch(error => console.log(error)) 
        console.log("gay" + id)
    }
    


    return(
        <div className="container">
            <div className="lista-wods">
                <select className="form-select" aria-label="Default select example">
                    <option selected disabled>Selecciona tu WOD</option>
                    { savedWod &&
                    
                    savedWod.map(
                        savedWod => (
                            <option >
                                <option value={savedWod.id} onClick={getSelectedWod(savedWod.id)}>{savedWod.wodName} {savedWod.id}</option>
                            </option>
                        )
                    )
                    }
                </select>
                <button className="btn btn-success" onClick={getSelectedWod(savedWod.id)}>asd</button>



                {/* { savedWod && 
                
                
                <table className='table table-hover'>
                    <thead>
                        <tr>
                            <th className="ExercisesTitle">Ejercicio</th>
                            <th className="TypeTitle">Tipo</th>
                            <th className="UpdateTitle"></th>
                            <th className="DeleteTitle"></th>
                        </tr>
                    </thead>
                    <tbody>
                        { savedWod.map(
                                savedWod => (
                                    <tr key={savedWod.id}>
                                        <td>{savedWod.exerciseId}</td>
                                        <td>{savedWod.exerciseType}</td>

                                    </tr>
                                )
                            )
                        }
                    </tbody>
                

                </table>
                } */}
            </div>
                <button className="btn btn-danger" onClick={refreshSavedWods}>refresh</button>
        </div>
    )
}