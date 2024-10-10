import SvgDefaultControllerProfileIcon from '@assets/svg/DefaultControllerProfileIcon'
import SvgLocationIcon from '@assets/svg/calendar/LocationIcon'
import SvgCastarCuteIcon from '@assets/svg/response/CastarCuteIcon'
import SvgCastarHappyIcon from '@assets/svg/response/CastarHappyIcon'
import SvgCastarSmartIcon from '@assets/svg/response/CastarSmartIcon'
import ReviewCarousel from '@components/review/ReviewCarousel'
import { useReviewStore } from '@stores/useReviewStore'
import { useState } from 'react'
import { useLocation } from 'react-router-dom'

export default function ReviewDetailPage() {
  const [activeButton, setActiveButton] = useState<number>(0)
  const location = useLocation()
  const pathSegments = location.pathname.split('/')
  const reviewId = parseInt(pathSegments[pathSegments.length - 1])
  const review = useReviewStore((state) => state.reviews.find((review) => review.id === reviewId))

  const onClickButton = (num: number) => {
    if (activeButton !== num) {
      setActiveButton(num)
    } else {
      setActiveButton(0)
    }
  }

  return (
    <div className='w-full divide-y divide-bg-50/10 '>
      <ReviewCarousel SLIDES={review!.images} OPTIONS={{ loop: false }} />

      <div className='relative z-50 flex justify-between w-full p-4 bg-bg-900'>
        <div className='flex items-center space-x-2'>
          <SvgDefaultControllerProfileIcon className='rounded-full w-9 h-9' />
          <p className='text-xs font-semibold'>{review!.author}</p>
        </div>
        <button className='flex items-center py-2 pl-3 pr-1 space-x-1 rounded-full bg-bg-50/10'>
          <p className='text-xs '>{review!.addressSummary}</p>
          <SvgLocationIcon className='w-4 h-4' />
        </button>
      </div>
      <div className='relative z-50 p-4 pb-6 bg-bg-900'>
        <h1 className='text-lg font-semibold'>{review!.title}</h1>
        <p className='text-sm whitespace-pre-line'>{review!.content}</p>
        <div className='flex justify-around px-4 mt-6 border-t border-b border-bg-50/10'>
          <button
            className={`flex flex-col items-center gap-2 pt-2 pb-1 ${activeButton === 1 && 'border-b-2 border-comp3-light'}`}
            onClick={() => onClickButton(1)}
          >
            <SvgCastarCuteIcon className={`w-10 h-10 ${activeButton !== 1 && 'opacity-50'}`} />
            <div className='text-center text-2xs'>
              <p className={`font-paperlogy ${activeButton !== 1 && 'text-text-tertiary'}`}>
                사진이 예뻐요
              </p>
              <p className='text-comp3-light'>
                {review!.reactionCount.visitAgain + (activeButton === 1 ? 1 : 0)}
              </p>
            </div>
          </button>
          <button
            className={`flex flex-col items-center gap-2 pt-2 pb-1 ${activeButton === 2 && 'border-b-2 border-comp1-light'}`}
            onClick={() => onClickButton(2)}
          >
            <SvgCastarSmartIcon className={`w-10 h-10 ${activeButton !== 2 && 'opacity-50'}`} />
            <div className='text-center text-2xs'>
              <p className={`font-paperlogy ${activeButton !== 2 && 'text-text-tertiary'}`}>
                도움이 됐어요
              </p>
              <p className='text-comp1-light'>
                {review!.reactionCount.visitAgain + (activeButton === 2 ? 1 : 0)}
              </p>
            </div>
          </button>
          <button
            className={`flex flex-col items-center gap-2 pt-2 pb-1 ${activeButton === 3 && 'border-b-2 border-comp2-light'}`}
            onClick={() => onClickButton(3)}
          >
            <SvgCastarHappyIcon className={`w-10 h-10 ${activeButton !== 3 && 'opacity-50'}`} />
            <div className='text-center text-2xs'>
              <p className={`font-paperlogy ${activeButton !== 3 && 'text-text-tertiary'}`}>
                가보고 싶어요
              </p>
              <p className='text-comp2-light'>
                {review!.reactionCount.visitAgain + (activeButton === 3 ? 1 : 0)}
              </p>
            </div>
          </button>
        </div>
      </div>
    </div>
  )
}
