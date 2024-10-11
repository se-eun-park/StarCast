import TierAvatarSelector from '@components/my/TierAvatarSelector'
import useMyProfile from '@stores/useMyProfile'
import { useReviewStore } from '@stores/useReviewStore.ts'
import { useTierStore } from '@stores/useTier.ts'
import { useUsernameStore } from '@stores/useUsername.ts'
import { useEffect } from 'react'

const ProfileEditPage = () => {
  const myProfile = useMyProfile((state) => state.myProfile)
  const tier = useTierStore((state) => state.Tier)
  const setTier = useTierStore((state) => state.setNicknameTier)
  const reviews = useReviewStore((state) => state.reviews)
  const nickname = useUsernameStore((state) => state.nickname)

  useEffect(() => {
    if (nickname) {
      setTier(reviews, nickname)
    }
  }, [nickname, setTier, tier, reviews])

  return (
    <div className='h-[calc(100dvh-56px)] w-full bg-bg-900 flex flex-col items-center'>
      <div className='flex flex-col items-center w-full pt-2 pb-4 border-b border-bg-50/30'>
        <div className='flex flex-col items-center w-[68px] aspect-square bg-bg-50/50 justify-center rounded-full'>
          <img src={`/public/svg/profile/${myProfile}.svg`} alt='현재 프로필 이미지' />
        </div>
        <p className='mt-2 text-sm font-semibold text-center'>현재 캐스타 프로필</p>
      </div>
      <div className='w-full overflow-y-auto scrollbar-hide'>
        <TierAvatarSelector tier={tier} />
      </div>
    </div>
  )
}

export default ProfileEditPage
