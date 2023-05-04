import { apiClient } from './ApiClient'


export const saveWod
        = (username, RequestBody) => {
                apiClient.post(`/users/${username}/wods`, RequestBody)
        }
                


export const retrevieSavedWodsForUsername
        = (username) => apiClient.get(`/users/${username}/wods`)


export const retriveSavedWod
        = (username, id) => apiClient.get(`/users/${username}/wods/${id}`)

export const deleteWodById
        = (username, id) => apiClient.delete(`/users/${username}/wods/${id}`)