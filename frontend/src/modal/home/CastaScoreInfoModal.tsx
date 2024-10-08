import { SmartCastarIcon } from '@assets/svg/castar'
import { useState } from 'react'

type CastaScoreInfoModalProps = {
  onClose: () => void
}

const CastaScoreInfoModal = ({ onClose }: CastaScoreInfoModalProps) => {
  const [isClose, setIsClose] = useState(false)

  const handleOnClickClose = () => {
    setIsClose(true)
    setTimeout(() => onClose(), 250)
  }

  return (
    <div
      className={`absolute bottom-0 flex flex-col items-center w-full px-6 py-8 bg-bg-800 rounded-t-3xl ${isClose ? 'animate-slideDown' : 'animate-slideUp'}`}
    >
      <h1 className='text-2xl font-paperlogy text-text-primary'>
        잠깐, <span className=' text-primary-light'>캐스타 점수</span>가 뭔가요?
      </h1>
      <SmartCastarIcon className='w-[12.5rem] my-6' />
      <p className='text-sm font-medium text-left max-w-72 text-text-primary'>
        캐스타 점수는 천체 관측의 다양한 환경 변수를 고려한{' '}
        <span className='text-primary-light'>스타캐스트만의 고유한 관측 추천도</span>에요.
        <br /> 구름량, 달의 밝기, 광공해, 습도, 강수 확률을 고려하여 매일 업데이트하고 있어요!
      </p>
      <div className='flex flex-col p-4 rounded-lg max-w-[18.75rem] bg-bg-50 bg-opacity-10 mt-6 mb-8'>
        <div className='flex items-center mb-2'>
          <p className='mr-4 w-[6.25rem] text-left text-sm font-medium text-comp1-light'>
            좋음 <span className='text-text-primary'>(75-100점)</span>
          </p>
          <p className='text-xs text-text-tertiary'>별자리 대부분을 볼 수 있어요!</p>
        </div>
        <div className='flex items-center mb-2'>
          <p className='mr-4 w-[6.25rem] text-left text-sm font-medium text-primary-light'>
            보통 <span className='text-text-primary'>(40-74점)</span>
          </p>
          <p className='text-xs text-text-tertiary'>밝은 천체들을 볼 수 있어요.</p>
        </div>
        <div className='flex items-center'>
          <p className='mr-4 w-[6.25rem] text-left text-sm font-medium text-comp2-light'>
            나쁨 <span className='text-text-primary'>(39점 이하)</span>
          </p>
          <p className='text-xs text-text-tertiary'>천체들이 거의 보이지 않아요...</p>
        </div>
      </div>
      <button onClick={handleOnClickClose} className='btn-secondary-full max-w-[19.5rem]'>
        확인했어요
      </button>
    </div>
  )
}

export default CastaScoreInfoModal
