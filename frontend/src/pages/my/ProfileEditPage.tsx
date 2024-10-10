import TierAvatarSelector from '@components/my/TierAvatarSelector'
import useMyProfile from '@stores/useMyProfile'

const ProfileEditPage = () => {
  const myProfile = useMyProfile((state) => state.myProfile)

  return (
    <div className='h-[calc(100dvh-56px)] w-full bg-bg-900 flex flex-col items-center'>
      <div className='flex flex-col items-center w-full pt-2 pb-4 border-b border-bg-50/30'>
        <div className='flex flex-col items-center w-[68px] aspect-square bg-bg-50/50 justify-center rounded-full'>
          <img src={`/public/svg/profile/${myProfile}.svg`} alt='현재 프로필 이미지' />
        </div>
        <p className='mt-2 text-sm font-semibold text-center'>현재 캐스타 프로필</p>
      </div>
      <div className='w-full overflow-y-auto scrollbar-hide'>
        <TierAvatarSelector tier='GOLD' />
      </div>
    </div>
  )
}

export default ProfileEditPage
