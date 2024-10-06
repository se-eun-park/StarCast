import SvgHomeIcon from '@assets/svg/HomeIcon'

import { useLocation, useNavigate } from 'react-router-dom'

export default function RouteHome() {
  const { pathname } = useLocation()
  const navigate = useNavigate()
  const routeHome = () => {
    navigate('/home')
  }

  const isReviewDetailPath = /\/review\/\d+/.test(pathname)

  return (
    <div
      className={`flex items-center justify-center rounded-full cursor-pointer ${isReviewDetailPath ? 'bg-bg-800' : 'bg-bg-900'} w-9 h-9`}
      onClick={routeHome}
    >
      <SvgHomeIcon className='w-6 h-6' />
    </div>
  )
}
