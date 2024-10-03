import { ApiError } from '@apis/axios/customApiError'
import { getCookie } from '@utils/cookie'
import { AUTH_COOKIE_KEYS } from '@constants/authCookieKeys'

export const getAccessToken = async (): Promise<string | null | ApiError> => {
  try {
    const refreshToken = getCookie(AUTH_COOKIE_KEYS.refreshToken)
    if (refreshToken) {
      // API 요청을 보내어 새로운 accessToken을 받아옵니다.
      return 'newAccessToken'
    } else {
      return null
    }
  } catch (e) {
    return e as ApiError
  }
}
