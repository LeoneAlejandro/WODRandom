import { apiClient } from './ApiClient'


export const retriveAllExercisesForUsernameApi
        = (username) => apiClient.get(`/users/${username}/exercises`)

export const deleteExerciseApi
        = (username, id) => apiClient.delete(`/users/${username}/exercises/${id}`)


export const retriveExerciseApi
        = (username, id) => apiClient.get(`/users/${username}/exercises/${id}`)

export const updateExerciseApi
        = (username, id, exercise) => apiClient.put(`/users/${username}/exercises/${id}`, exercise)

export const createExerciseApi
        = (username, exercise) => apiClient.post(`/users/${username}/exercises`, exercise)




