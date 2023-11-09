import { createContext, useContext, useState } from "react";
import { apiClient } from "../api/ApiClient";
import { executeJwtAuthenticationService } from "./AuthenticationApiService";

export const AuthContext = createContext()
export const useAuth = () => useContext(AuthContext)

export default function AuthProvider({ children }) {

    const [isAuthenticated, setAuthenticated] = useState(false)
    const [email, setEmail] = useState(null)
    const [token, setToken] = useState(null)


    async function login(email, password) {

        try {
            const response = await executeJwtAuthenticationService(email, password)
            
            if(response.status === 200) {
                const jwtToken = 'Bearer ' + response.data.token
                setAuthenticated(true)
                setEmail(email)
                setToken(jwtToken)

                apiClient.interceptors.request.use(
                    (config) => {
                        config.headers.Authorization=jwtToken
                        return config
                    }
                )
                
                return true

            } else {
                logout()
                return false
            }
        } catch (error) {
            logout()
            return false
        }
    }

    function logout() {
        setAuthenticated(false)
        setToken(null)
        setEmail(null)
    }

    return(
        <AuthContext.Provider value={{ isAuthenticated, login, logout, username: email, token}}>
            {children}
        </AuthContext.Provider>
    )
}