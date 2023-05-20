import { useNavigate } from 'react-router-dom'
import '../css/WelcomeComponent.css'

export default function WelcomeComponent() {

    const navigate = useNavigate()

    return(
            <div className='containerCards'>
                <div class="menuCard">
                    <div class="menuCard-content" onClick={() => navigate(`/randomize`)}>
                        <h3 class="menuCard-title">Randomizar</h3>
                        <h4 class="menuCard-subtitle">creá un wod aleatorio seleccionado de tus ejercicios favoritos</h4>
                    </div>
                </div>

                <div class="menuCard">
                    <div class="menuCard-content" onClick={() => navigate(`/exercises`)} >
                        <h3 class="menuCard-title" >Ejercicios</h3>
                        <h4 class="menuCard-subtitle">agregá tus ejercicios preferidos para crear wods aleatorios o customizados a tu elección</h4>
                    </div>
                </div>

                <div class="menuCard">
                    <div class="menuCard-content" onClick={() => navigate(`/wods`)}>
                        <h3 class="menuCard-title" >Wods</h3>
                        <h4 class="menuCard-subtitle">accedé a todos tus wods favoritos guardados, también podés editar wods existentes</h4>
                    </div>
                </div>

                <div class="menuCard">
                    <div class="menuCard-content"  onClick={() => navigate(`/createWod`)}>
                        <h3 class="menuCard-title">Custom Wods</h3>
                        <h4 class="menuCard-subtitle">creá Wods a tu medida, eligiendo a tu elección tus ejercicios preferidos</h4>
                    </div>
                </div>
            </div>

        )
}