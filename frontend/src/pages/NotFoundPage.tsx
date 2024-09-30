import Svg404CastarIcon from '@assets/svg/404CastarIcon'
import ReviewDetailLayout from '@components/ui/ReviewDetailLayout'

const NotFoundPage = () => {
  return (
    <ReviewDetailLayout>
      <div className='flex flex-col items-center w-full gap-2 min-h-dvh bg-bg-900 pt-60'>
        <Svg404CastarIcon className='w-28 h-28' />
        <div className='text-[#B2D8FF] opacity-50'>
          <p className='text-lg'>404 ERROR</p>
          <p className='text-xs'>여기 들어오시면 안돼요!</p>
        </div>
      </div>
    </ReviewDetailLayout>
  )
}

export default NotFoundPage
