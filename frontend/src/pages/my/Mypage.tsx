import { useEffect, useState } from 'react'
import { NavLink } from 'react-router-dom'
import { EditIcon } from '@assets/svg/home'
import { ArrowIcon } from '@assets/svg/calendar'
import useMyProfile from '@stores/useMyProfile'
import { useUsernameStore } from '@stores/useUsername.ts'
import { useTierStore } from '@stores/useTier.ts'
import { useReviewStore } from '@stores/useReviewStore.ts'

const Mypage = () => {
  const nickname = useUsernameStore((state) => state.nickname)
  const myProfile = useMyProfile((state) => state.myProfile)
  const tier = useTierStore((state) => state.Tier)
  const tierLevel = useTierStore((state) => state.TierLevel)
  const reviews = useReviewStore((state) => state.reviews)
  const setTier = useTierStore((state) => state.setNicknameTier)
  const setTierLevel = useTierStore((state) => state.setTierLevel)

  const [isActive, setIsActive] = useState(false)

  useEffect(() => {
    if (nickname) {
      setTier(reviews, nickname)
    }

    if (tier) {
      setTierLevel(tier)
    }
  }, [nickname, setTier, tier, setTierLevel])

  const handleActive = () => {
    setIsActive(!isActive)
  }

  return (
    <div className='w-full h-[calc(100dvh-56px)] flex flex-col items-center bg-bg-900 pb-20'>
      <div className='flex flex-col items-center w-full pt-2 h-fit bg-gradient900to800 rounded-b-2xl'>
        <div className='flex relative items-center justify-center w-[4.25rem] h-[4.25rem] rounded-full bg-bg-50/50'>
          <img src={`/public/svg/profile/${myProfile}.svg`} alt='현재 프로필 이미지' />
          <NavLink
            to='/mypage/profile'
            className='absolute bottom-0 right-0 flex items-center justify-center w-6 h-6 rounded-full bg-bg-50'
          >
            <EditIcon className='w-3' />
          </NavLink>
        </div>
        <div className='flex flex-col items-center mt-4'>
          <p className='mb-1 font-semibold text-center'>{nickname}</p>
          <NavLink to='/mypage/nickname'>
            <div className='flex items-center'>
              <p className='text-xs text-text-secondary mr-0.5'>닉네임 변경하기</p>
              <ArrowIcon className='w-4 transform -rotate-90 fill-text-secondary' />
            </div>
          </NavLink>
        </div>
        <div className='flex flex-col justify-center w-full py-4 mt-4 border-t border-bg-50/30'>
          <div className='flex items-center justify-center'>
            <p className='mr-2 text-sm font-semibold'>{tier}</p>
            <div className='relative w-40 h-1.5 bg-text-tertiary/50 rounded-full'>
              <div
                className={`absolute left-0 w-[calc(10rem/3*${tierLevel})] h-1.5 bg-gaugeGradient rounded-full`}
              />
            </div>
            <p className='font-semibold text-2xs ml-2.5'>{tierLevel} / 3</p>
          </div>
          <p className='mt-1 text-center text-text-tertiary text-2xs'>
            후기를 작성하고 포인트를 얻어, 새로운 캐스타 프로필을 얻어보세요!
          </p>
        </div>
      </div>
      <div className='flex flex-col w-full px-8 overflow-y-auto divide-y scrollbar-hide divide-solid divide-bg-50/20'>
        <h1 className='my-4 font-semibold'>나의 스타캐스트</h1>
        <NavLink to='/mypage/location' className='flex items-center justify-between px-2 py-4'>
          <p className='text-sm font-medium text-text-secondary'>내 위치 직접 입력하기</p>
          <ArrowIcon className='w-5 transform -rotate-90 fill-text-secondary' />
        </NavLink>
        <div className='flex items-center justify-between px-2 py-4'>
          <p className='text-sm font-medium text-text-secondary'>알림 받기</p>
          <div
            onClick={handleActive}
            className={`relative flex items-center w-10 h-6 rounded-full transition-all duration-200 ease-in cursor-pointer ${isActive ? 'bg-bg-200' : 'bg-bg-50/30'}`}
          >
            <input
              type='checkbox'
              name='toggle'
              checked={isActive}
              onChange={handleActive}
              className={`appearance-none w-[1.125rem] h-[1.125rem] rounded-full absolute bg-bg-50 cursor-pointer transition-all duration-100 ease-linear ml-1 ${isActive ? 'translate-x-3.5' : 'left-0'}`}
            />
          </div>
        </div>
        <h1 className='pt-6 pb-4 font-semibold'>커뮤니티</h1>
        <NavLink to='/mypage/review' className='flex items-center justify-between px-2 py-4'>
          <p className='text-sm font-medium text-text-secondary'>나의 관측 후기</p>
          <ArrowIcon className='w-5 transform -rotate-90 fill-text-secondary' />
        </NavLink>
        <NavLink to='/mypage/reaction' className='flex items-center justify-between px-2 py-4'>
          <p className='text-sm font-medium text-text-secondary'>내가 남긴 반응</p>
          <ArrowIcon className='w-5 transform -rotate-90 fill-text-secondary' />
        </NavLink>
        <p className='px-2 pt-4 text-sm font-medium text-text-tertiary'>로그아웃</p>
      </div>
    </div>
  )
}

export default Mypage
