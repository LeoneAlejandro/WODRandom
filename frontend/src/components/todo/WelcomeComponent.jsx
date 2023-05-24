import { useNavigate } from 'react-router-dom'
import '../css/WelcomeComponent.css'

export default function WelcomeComponent() {

    const navigate = useNavigate()

    return(
            <div className='containerCards'>
                <div className="menuCard">
                    <div className="menuCard-content" onClick={() => navigate(`/randomize`)}>
                        <h3 className="menuCard-title">Randomizar</h3>
                        <h4 className="menuCard-subtitle">creá un wod aleatorio seleccionado de tus ejercicios favoritos</h4>
                    </div>
                </div>

                <div className="menuCard">
                    <div className="menuCard-content" onClick={() => navigate(`/wods`)}>
                        <h3 className="menuCard-title" >Wods</h3>
                        <h4 className="menuCard-subtitle">accedé a todos tus wods favoritos guardados, también podés editar wods existentes</h4>
                    </div>
                </div>

                <div className="menuCard">
                    <div className="menuCard-content" onClick={() => navigate(`/exercises`)} >
                        <h3 className="menuCard-title" >Ejercicios</h3>
                        <h4 className="menuCard-subtitle">agregá tus ejercicios preferidos para crear wods aleatorios o customizados a tu elección</h4>
                    </div>
                </div>

                <div className="menuCard">
                    <div className="menuCard-content"  onClick={() => navigate(`/createWod`)}>
                        <h3 className="menuCard-title">Custom Wods</h3>
                        <h4 className="menuCard-subtitle">creá Wods a tu medida, eligiendo a tu elección tus ejercicios preferidos</h4>
                    </div>
                </div>
            </div>

        )
}