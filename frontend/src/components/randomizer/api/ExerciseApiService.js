import { apiClient } from './ApiClient'


export const retriveAllExercisesForUsernameApi
        = (userId) => apiClient.get(`/users/${userId}/exercises`)

export const deleteExerciseApi
        = (userId, id) => apiClient.delete(`/users/${userId}/exercises/${id}`)


export const retriveExerciseApi
        = (userId, id) => apiClient.get(`/users/${userId}/exercises/${id}`)

export const updateExerciseApi
        = (userId, id, exercise) => apiClient.put(`/users/${userId}/exercises/${id}`, exercise)

export const createExerciseApi
        = (userId, exercise) => apiClient.post(`/users/${userId}/exercises`, exercise)

export const retriveRandomExerciseByType
        = (userId, exerciseId) => apiClient.post(`/users/${userId}/exercises/type/${exerciseId}`)


