import {
  onRequest,
  onRequestError,
  onResponse,
  onResponseErrorPrivate,
} from '@apis/axios/interceptor'
import axios, { AxiosInstance } from 'axios'

const BASE_API_URL = import.meta.env.BASE_API_URL

export const privateApi: AxiosInstance = axios.create({
  baseURL: BASE_API_URL,
  timeout: 5000,
  withCredentials: true,
})

privateApi.interceptors.request.use(onRequest, onRequestError)
privateApi.interceptors.response.use(onResponse, onResponseErrorPrivate)
