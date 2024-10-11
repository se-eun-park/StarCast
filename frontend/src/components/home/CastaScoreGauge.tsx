import { GaugeIcon, HelpIcon } from '@assets/svg/home'
import { gaugeIconPathD } from '@constants/gaugeIconPathD'
import useModal from '@hooks/useModal'
import CastaScoreInfoModal from '@modal/home/CastaScoreInfoModal'

type CastaScoreGaugeProps = {
  status: string
  score: number
}

const CastaScoreGauge = ({ status, score }: CastaScoreGaugeProps) => {
  const { Modal, open, close } = useModal()

  return (
    <>
      <div className='relative flex flex-col'>
        <div className='flex items-center justify-center'>
          <GaugeIcon className='relative w-[6.25rem] opacity-30 z-0' d={gaugeIconPathD[100]} />
          <GaugeIcon
            className={`absolute w-[6.25rem] z-10 ${status === '좋음' ? 'fill-comp1-light' : status === '보통' ? 'fill-primary-light' : 'fill-comp2-light'}`}
            d={gaugeIconPathD[score]}
          />
          <div className='absolute flex flex-col top-7'>
            <p className='text-xs text-center text-text-secondary'>01시</p>
            <p
              className={`text-3xl font-semibold leading-6 text-center ${status === '좋음' ? 'text-comp1-light' : status === '보통' ? 'text-primary-light' : 'text-comp2-light'}`}
            >
              {score}
              <span className='text-sm font-normal'>점</span>
            </p>
          </div>
        </div>
        <div className='flex justify-between px-5'>
          <p className='w-5 text-xs font-semibold text-center text-text-tertiary'>0</p>
          <p className='w-5 text-xs font-semibold text-center text-text-tertiary'>100</p>
        </div>
        <button onClick={open} className='flex items-center pl-5'>
          <p className='text-sm font-semibold text-center text-text-tertiary'>캐스타점수</p>
          <p
            className={`pb-1 mx-1 text-xl font-medium text-center font-paperlogy ${status === '좋음' ? 'text-comp1-light' : status === '보통' ? 'text-primary-light' : 'text-comp2-light'}`}
          >
            나쁨
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
