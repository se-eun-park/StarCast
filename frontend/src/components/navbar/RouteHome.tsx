import SvgHomeIcon from '@assets/svg/HomeIcon'

import { useNavigate } from 'react-router-dom'

export default function RouteHome() {
  const navigate = useNavigate()
  const routeHome = () => {
    navigate('/home')
  }

  return (
    <div
      className='flex items-center justify-center rounded-full cursor-pointer bg-bg-800 w-9 h-9'
      onClick={routeHome}
    >
      <SvgHomeIcon className='w-6 h-6' />
    </div>
  )
}
