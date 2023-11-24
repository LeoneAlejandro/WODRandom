import { apiClient } from "../api/ApiClient"

// export const executeBasicAuthenticationService = 
//     (token) => apiClient.get(`/basicauth`, {
//         headers: {
//             Authorization: token
//         }
//     })

export const executeJwtAuthenticationService 
    = (email, password) => apiClient.post(`/authenticate`, { email, password })