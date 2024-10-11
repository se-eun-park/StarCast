import { Cookies } from 'react-cookie'
import { CookieSetOptions } from 'universal-cookie'

const cookies = new Cookies()

export const setCookie = (name: string, value: string, options?: CookieSetOptions): void => {
  cookies.set(name, value, { ...options })
}

export const getCookie = (name: string): string | undefined => {
  return cookies.get(name)
}

export const removeCookie = (name: string): void => {
  cookies.remove(name, { path: '/' })
}
