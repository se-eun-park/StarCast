import SvgArrowBackIcon from '@assets/svg/calendar/ArrowBackIcon'
import SvgCloseIcon from '@assets/svg/CloseIcon'

import { useLocation, useNavigate } from 'react-router-dom'

export default function RouteBack() {
  const { pathname } = useLocation()
  const navigate = useNavigate()
  const routeBack = () => {
    navigate(-1)
  }

  const isReviewDetailPath = /\/review\/\d+/.test(pathname)
  const isCreateReviewPath = /\/review\/new/.test(pathname)

  return (
    <div
      className={`flex items-center justify-center rounded-full cursor-pointer ${isReviewDetailPath ? 'bg-bg-800' : 'bg-bg-900'} w-9 h-9`}
      onClick={routeBack}
    >
      {isCreateReviewPath ? (
        <SvgCloseIcon className='w-6 h-6' />
      ) : (
        <SvgArrowBackIcon className='w-6 h-6' />
      )}
    </div>
  )
}
