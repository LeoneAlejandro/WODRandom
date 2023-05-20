import { useEffect, useState } from "react"
import { useAuth } from "./security/AuthContext"
import { deleteWodById, retrevieSavedWodsForUsername, retriveSavedWod } from "./api/SaveWodService"
import '../css/SavedWodsComponent.css'


export default function SavedWodsComponent() {

    const authContext = useAuth()
    const username = authContext.username
 

    const [savedWod, setSavedWod] = useState([])
    const [selectedWod, setSelectedWod] = useState('')

    useEffect(
        () => {
            refreshSavedWods()
        } , []
    )

    function refreshSavedWods() {
        retrevieSavedWodsForUsername(username)
            .then(response => {
                setSavedWod(response.data)
            })
            .catch(error => {
                console.log(error) 
            })
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
            .then(() => {
                refreshSavedWods()
                setSelectedWod(null)
            })
            .catch(error => console.log(error))
    }


    return(
        <div className="savedWods">
            <div className="wodList">
                <select className="form-selection" onChange={(e) =>  getSelectedWod(e.target.value)}  defaultValue="default">
                             <option value="default" disabled selected={!selectedWod}>Selecciona tu WOD</option>
                    { savedWod &&
                    savedWod.map(
                        (savedWod, index) => (
                            <option key={index} value={savedWod.id}>{savedWod.wodName}</option>
                        )
                    )}
                </select>
            </div>
            


            <div className="selected-wod">
                {selectedWod && 
                    <table className='tableSavedWods'>
                        <thead>
                            <tr className="savedWodsTableHeader">
                                <th className="selectedExTitle">Ejercicio</th>
                                <th >Tipo</th>
                                {/* <th className="Update"></th>
                                <th className="Delete"></th> */}
                            </tr>
                        </thead>
                        <tbody>
                            { selectedWod.exercises.map(
                                    (selectedWod, index) => (
                                        <tr className="savedWodsTableRows" key={selectedWod.id}>
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
            
            { selectedWod &&
                <button className="" onClick={deleteWod}>Delete WOD</button>
            }


        </div>
    )
}