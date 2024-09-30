/* eslint-disable no-prototype-builtins */

import NavbarContent from '@components/navbar/NavbarContent.tsx'
import RouteBack from '@components/navbar/RouteBack'
import { pathToNavContent } from '@constants/navigationContent.ts'
import { useLocation } from 'react-router-dom'

export default function NavbarWithLabel() {
  const { pathname } = useLocation()

  if (!pathToNavContent.hasOwnProperty(pathname)) {
    return null
  }

  return (
    <div className='sticky top-0 flex items-center justify-between w-full px-5 h-14 bg-bg-900'>
      <RouteBack />
      <NavbarContent />
    </div>
  )
}
