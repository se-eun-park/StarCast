import SvgHappyCastarIcon from '@assets/svg/castar/HappyCastarIcon'
import SvgCalendarIcon from '@assets/svg/menu/CalendarIcon'
import SvgMypageIcon from '@assets/svg/menu/MypageIcon'
import SvgObservingSpotIcon from '@assets/svg/menu/ObservingSpotIcon'
import SvgReviewIcon from '@assets/svg/menu/ReviewIcon'
import { NavLink } from 'react-router-dom'

export default function Menubar() {
  return (
    <div className='fixed bottom-0 left-1/2 transform -translate-x-1/2 flex justify-center w-[37.375rem] pb-2 bg-bg-900/50 backdrop-blur z-40'>
      <div className='w-[21.25rem] bg-bg-50/25 px-8 flex justify-between py-2 rounded-3xl text-2xs '>
        <NavLink
          to='/observing-spot'
          className='space-y-0.5 text-center hover:bg-bg-900/30 w-9 h-11 rounded-[.25rem]'
        >
          <SvgObservingSpotIcon className='w-6 h-6 mx-auto' />
          <p>관측지</p>
        </NavLink>
        <NavLink
          to='/calendar'
          className='space-y-0.5 text-center hover:bg-bg-900/30 w-9 h-11 rounded-[.25rem]'
        >
          <SvgCalendarIcon className='w-6 h-6 mx-auto' />
          <p>캘린더</p>
        </NavLink>
        <SvgHappyCastarIcon className='w-11 h-11 ' />
        <NavLink
          to='/review'
          className='space-y-0.5 text-center hover:bg-bg-900/30 w-9 h-11 rounded-[.25rem]'
        >
          <SvgReviewIcon className='w-6 h-6 mx-auto' />
          <p>후기</p>
        </NavLink>
        <NavLink
          to='/mypage'
          className='space-y-0.5 text-center hover:bg-bg-900/30 w-9 h-11 rounded-[.25rem]'
        >
          <SvgMypageIcon className='w-6 h-6 mx-auto' />
          <p>MY</p>
        </NavLink>
      </div>
    </div>
  )
}
