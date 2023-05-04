import { apiClient } from './ApiClient'


export const saveWod
        = (username, RequestBody) => {
                apiClient.post(`/users/${username}/wods`, RequestBody)
        }
                


export const retrevieSavedWodsForUsername
        = (username) => apiClient.get(`/users/${username}/wods`)

