import SvgArrowBackIcon from '@assets/svg/calendar/ArrowBackIcon'

import { useNavigate } from 'react-router-dom'

export default function RouteBack() {
  const navigate = useNavigate()
  const routeBack = () => {
    navigate(-1)
  }

  return <SvgArrowBackIcon className='w-6 h-6 cursor-pointer' onClick={routeBack} />
}
