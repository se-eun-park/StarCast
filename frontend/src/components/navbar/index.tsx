/* eslint-disable no-prototype-builtins */

import NavbarContent from '@components/navbar/NavbarContent.tsx'
import RouteBack from '@components/navbar/RouteBack'
import { pathToNavContent } from '@constants/navigationContent.ts'
import { useLocation } from 'react-router-dom'

export default function Navbar() {
  const { pathname } = useLocation()

  if (!pathToNavContent.hasOwnProperty(pathname)) {
    return null
  }

  return (
    <div className='flex items-center justify-between w-full px-5 h-14'>
      <RouteBack />
      <NavbarContent />
    </div>
  )
}
