import { useNavigate } from 'react-router-dom'
import React, { useState } from 'react';
import '../css/WelcomeComponent.css'
import wodsData from '../../assets/legacyWods.json'

export default function WelcomeComponent() {

    const navigate = useNavigate()

    const [randomWOD, setRandomWOD] = useState(null);

    const getRandomWOD = () => {
      const randomIndex = Math.floor(Math.random() * wodsData.WODs.length);
      const selectedWOD = wodsData.WODs[randomIndex];
      setRandomWOD(selectedWOD);
    };

    return(
        <div className='welcomeMenu'>

            <div className='containerCards'>

                <div className="menuCard">
                    <div className="menuCard-content" onClick={() => navigate(`/randomize`)}>
                        <h3 className="menuCard-title">Randomizar !</h3>
                        <h4 className="menuCard-subtitle">creá un wod aleatorio seleccionado de tus ejercicios favoritos</h4>
                    </div>
                </div>

                <div className="menuCard">
                    <div className="menuCard-content" onClick={() => navigate(`/wods`)}>
                        <h3 className="menuCard-title" >WODs</h3>
                        <h4 className="menuCard-subtitle">accedé a todos tus wods favoritos guardados, también podés editar wods existentes</h4>
                    </div>
                </div>

                <div className="menuCard">
                    <div className="menuCard-content" onClick={() => navigate(`/exercises`)} >
                        <h3 className="menuCard-title" >Tus ejercicios</h3>
                        <h4 className="menuCard-subtitle">agregá tus ejercicios preferidos para crear wods aleatorios o customizados a tu elección</h4>
                    </div>
                </div>

                <div className="menuCard">
                    <div className="menuCard-content"  onClick={() => navigate(`/createWod/-1`)}>
                        <h3 className="menuCard-title">Custom WODs</h3>
                        <h4 className="menuCard-subtitle">creá Wods a tu medida, eligiendo a elección tus ejercicios preferidos</h4>
                    </div>
                </div>

            </div>

            {/* <div className="legacyWods">
                <h2>Legacy Wods</h2>
                <button onClick={getRandomWOD}>Pick a Random WOD</button>
                {randomWOD && (
                    <div>
                        <p>Name: {randomWOD.name}</p>
                        <tr className='legacyWodsList'>
                            {randomWOD.exercises.map((exercise, index) => (
                            <tr key={index}>{exercise}</tr>
                            ))}
                        </tr>
                    </div>
                )}
            </div> */}

            <div className="legacyWodsContainer">
                <div className='legacyWodCard'>
                    <h2 className='legacyWodTitle'>Legacy Wods</h2>
                    <button className='legacyWodsWelcomeButton' onClick={getRandomWOD}>Random WOD</button>
                    {randomWOD && (
                        <div className='legacyWodsList'>
                            <p className='legacyWodsListTitle'>{randomWOD.name}</p>
                            {/* <tr className='legacyWodRow'> */}
                                {randomWOD.exercises.map((exercise, index) => (
                                <li className='legacyWodSingleRow' key={index}>{exercise}</li>
                                ))}
                            {/* </tr> */}
                                {randomWOD.description && <p className='lWWLDescription'>{randomWOD.description} </p>
                            }
                        </div>
                    )}
                </div>
            </div>

        </div>
        )
}