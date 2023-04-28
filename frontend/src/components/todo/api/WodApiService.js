import { apiClient } from './ApiClient'


export const generateWod
        = (username, wod) => apiClient.post(`/users/${username}/generatewod`, wod)
