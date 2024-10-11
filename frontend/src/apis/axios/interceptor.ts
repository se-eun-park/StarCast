import { DefaultResponseType, ErrorType } from '@apis/axios/api.types'
import { ApiError } from '@apis/axios/customApiError'
import { privateApi } from '@apis/axios'
import { getCookie } from '@utils/cookie'
import { getAccessToken } from '@utils/getAccessToken'
import { AxiosError, AxiosResponse, InternalAxiosRequestConfig } from 'axios'
import { redirect } from 'react-router-dom'

export type DefaultServerResponseType<DataType> = {
  data: DataType
  statusCode: number
  success: boolean
}

export const onRequest = async (config: InternalAxiosRequestConfig) => {
  try {
    const token = getCookie('accessToken')
    if (token) {
      config.headers.Authorization = `Bearer ${token}`
    }
    return config
  } catch (error) {
    return Promise.reject(error)
  }
}

export const onResponse = <DataType>(response: AxiosResponse<DefaultResponseType<DataType>>) => {
  const defaultServerScheme: DefaultResponseType<DataType> = response.data
  const { data, statusCode, success } = defaultServerScheme
  const { headers } = response
  const returnValue = { ...data, headers, statusCode, success, data: null as DataType }
  if (typeof data !== 'object') {
    returnValue.data = data
  }

  return returnValue
}

export const onRequestError = (error: AxiosError) => Promise.reject(error)

export const onResponseError = (error: AxiosError<ErrorType, InternalAxiosRequestConfig>) => {
  // 2xx 외의 범위에 있는 상태 코드는 이 함수를 트리거 합니다.
  if (error.response) {
    const data = error.response.data
    const { success, statusCode, errorMessage } = data

    return Promise.reject(new ApiError(success, statusCode, errorMessage))
  }
  if (error.request) {
    console.log('요청이 이루어 졌으나 응답을 받지 못했습니다.', error.request)
  }

  return Promise.reject(new Error('요청 도중 에러가 발생했습니다.'))
}

export const onResponseErrorPrivate = async (
  error: AxiosError<ErrorType, InternalAxiosRequestConfig>,
) => {
  // 2xx 외의 범위에 있는 상태 코드는 이 함수를 트리거 합니다.
  try {
    if (error.response) {
      // 요청이 이루어졌으며 서버가 2xx의 범위를 벗어나는 상태 코드로 응답했습니다.

      const data = error.response.data
      const { success, statusCode, errorMessage } = data

      if (statusCode === 401) {
        try {
          const accessToken = await getAccessToken()
          if (!accessToken) {
            throw new ApiError(success, statusCode, 'accessToken 발급중 오류가 발생했습니다.')
          }

          const prevRequest = error.config
          if (!prevRequest) {
            throw new ApiError(success, statusCode, '이전 요청 정보가 없습니다.')
          }
          prevRequest.headers['Authorization'] = `Bearer ${accessToken}`
          return privateApi(prevRequest)
        } catch (error) {
          console.log(error)
          redirect('/')
          return Promise.reject(error)
        }
      }
      // 서버에서 보낸 custom 에러 메세지가 없을 경우 기본 메세지를 에러 메세지로 전달
      return Promise.reject(new ApiError(success, statusCode, errorMessage))
    }

    if (error.request) {
      console.log('요청이 이루어 졌으나 응답을 받지 못했습니다.', error.request)
    }
    return Promise.reject(new Error('요청 도중 에러가 발생했습니다.'))
  } catch (error) {
    return Promise.reject(error)
  }
}
