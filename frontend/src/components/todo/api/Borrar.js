import { apiClient } from "./ApiClient"

export const retriveHelloWorldBean = (token) => apiClient.get('/hello-world-bean')

//no uso una función flecha en este caso ya que tengo que pasar un parámetro, y tengo que llamar a retriveHelloWorld con paréntesis en WelcomeComponent
export const retriveHelloWorld = (token) => apiClient.get('/hello-world')      

//si uso una función flecha en este caso, y no tengo que llamar a retriveHelloWorld con paréntesis en WelcomeComponent
export const retriveHelloWorldWithPath = 
    (username, token) => apiClient.get(`/hello-world/path-variable/${username}`
    // , {
    //     headers: {
    //         Authorization: token 
    //     }}
    // Ahora esto  lo hace AuthContext mediante interceptors
    )