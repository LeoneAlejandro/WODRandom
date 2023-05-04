import { useEffect, useState } from "react"
import { useAuth } from "./security/AuthContext"
import { deleteWodById, retrevieSavedWodsForUsername, retriveSavedWod } from "./api/SaveWodService"
import '../css/SavedWodsComponent.css'
import { useNavigate } from "react-router-dom"

export default function SavedWodsComponent() {

    const authContext = useAuth()
    const username = authContext.username
 

    const [savedWod, setSavedWod] = useState('')
    const [selectedWod, setSelectedWod] = useState('')

    useEffect(
        () => refreshSavedWods(), []
    )

    function refreshSavedWods() {
        retrevieSavedWodsForUsername(username)
            .then(response => 
                setSavedWod(response.data),
                setSelectedWod(null)
                // console.log(savedWod)
                )
            .catch(error => console.log(error)) 
    }

    function getSelectedWod(id) {
        retriveSavedWod(username, id)
            .then(response => {
                setSelectedWod(response.data)
            })
            .catch(error => console.log(error.data)) 
    }

    function deleteWod() {
        deleteWodById(username, selectedWod.id) 
            .then(response => {
                refreshSavedWods()
            })
            .catch(error => console.log(error))
        
    }


    return(
        <div className="savedWods">
            <div className="lista-wods">
                <select className="form-selection" onChange={(e) => getSelectedWod(e.target.value)} aria-label="Default select example">
                             <option selected disabled>Selecciona tu WOD</option>
                    { savedWod &&
                    savedWod.map(
                        savedWod => (
                                <option value={savedWod.id}>{savedWod.wodName}</option>
                        )
                    )
                    }
                </select>
            </div>

            <button className="btn btn-danger" onClick={deleteWod}>Delete WOD</button>

            <div className="selected-wod">
                {selectedWod && 
                    <table className='table table-hover'>
                        <thead>
                            <tr>
                                <th className="selectedExTitle">Ejercicio</th>
                                <th className="selectedExType">Tipo</th>
                                {/* <th className="Update"></th>
                                <th className="Delete"></th> */}
                            </tr>
                        </thead>
                        <tbody>
                            { selectedWod.exercises.map(
                                    selectedWod => (
                                        <tr key={selectedWod.id}>
                                            <td>{selectedWod.exerciseName}</td>
                                            <td>{selectedWod.exerciseType}</td>
                                        </tr>
                                    )
                                )
                            }
                        </tbody>
                    </table>
                }
            </div>
        </div>
    )
}