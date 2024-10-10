import SvgDefaultControllerProfileIcon from '@assets/svg/DefaultControllerProfileIcon'
import SvgNotificationActiveIcon from '@assets/svg/NotificationActiveIcon'
import SvgNotificationInactiveIcon from '@assets/svg/NotificationInactiveIcon'
import { useWelcomeMessage } from '@hooks/useWelcomeMessage'
import { useState } from 'react'
import { Link } from 'react-router-dom'
import useMyProfile from '@stores/useMyProfile'

export default function UseController() {
  const myProfile = useMyProfile((state) => state.myProfile)

  const [hasNotification] = useState(false)
  const isWelcomeMessageShown = useWelcomeMessage()

  return (
    <div
      className={`absolute top-0 right-0  p-4 h-fit transition-all duration-500 ease-in-out  ${isWelcomeMessageShown ? 'w-[7.25rem]' : 'w-full sm:w-[37.375rem]'}`}
    >
      <div className='p-[5px] flex items-center justify-between space-x-[10px] bg-bg-50/25 rounded-full'>
        <Link to='/mypage'>
          <img
            src={`/public/svg/profile/${myProfile}.svg`}
            alt='현재 프로필 이미지'
            className='w-8 h-8'
          />
        </Link>
        {!isWelcomeMessageShown && (
          <p className='text-sm font-pafont-paperlogy'>반가워요, 즐거운캐스타당근도둑님!</p>
        )}
        <div
          className={`flex items-center justify-center w-8 h-8 rounded-full  ${
            hasNotification ? 'bg-bg-50/50 shadow-md shadow-bg-50/20' : 'bg-bg-50/15'
          }`}
        >
          <Link to='/notification'>
            {hasNotification ? (
              <SvgNotificationActiveIcon className='w-5 h-5 cursor-pointer' />
            ) : (
              <SvgNotificationInactiveIcon className='w-5 h-5 cursor-pointer' />
            )}
          </Link>
        </div>
      </div>
    </div>
  )
}
