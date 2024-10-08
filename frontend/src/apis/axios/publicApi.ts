import { onResponse, onResponseError } from '@apis/axios/interceptor'
import axios, { AxiosInstance } from 'axios'

const BASE_API_URL = import.meta.env.VITE_BASE_API_URL

export const publicApi: AxiosInstance = axios.create({
  baseURL: BASE_API_URL,
  timeout: 5000,
  withCredentials: true,
})

publicApi.interceptors.response.use(onResponse, onResponseError)
