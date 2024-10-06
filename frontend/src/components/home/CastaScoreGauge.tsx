import { GaugeIcon, HelpIcon } from '@assets/svg/home'
import { gaugeIconPathD } from '@constants/gaugeIconPathD'
import useModal from '@hooks/useModal'
import CastaScoreInfoModal from '@modal/home/CastaScoreInfoModal'

const CastaScoreGauge = () => {
  const { Modal, open, close } = useModal()

  return (
    <>
      <div className='flex flex-col'>
        <div className='flex items-center justify-center'>
          <GaugeIcon className='relative w-[6.25rem] opacity-30 z-0' d={gaugeIconPathD[100]} />
          <GaugeIcon
            className='absolute w-[6.25rem] fill-comp1-light z-10'
            d={gaugeIconPathD[82]}
          />
          <div className='absolute flex flex-col top-7'>
            <p className='text-xs text-center text-text-secondary'>22시</p>
            <p className='text-3xl font-semibold leading-6 text-center text-comp1-light'>
              82<span className='text-sm font-normal'>점</span>
            </p>
          </div>
        </div>
        <div className='flex justify-between px-5'>
          <p className='w-5 text-xs font-semibold text-center text-text-tertiary'>0</p>
          <p className='w-5 text-xs font-semibold text-center text-text-tertiary'>100</p>
        </div>
        <button onClick={open} className='flex items-center pl-5'>
          <p className='text-sm font-semibold text-center text-text-tertiary'>캐스타점수</p>
          <p className='pb-1 mx-1 text-xl font-medium text-center font-paperlogy text-comp1-light'>
            좋음
          </p>
          <HelpIcon className='w-4' />
        </button>
      </div>
      <Modal>
        <CastaScoreInfoModal onClose={close} />
      </Modal>
    </>
  )
}

export default CastaScoreGauge
