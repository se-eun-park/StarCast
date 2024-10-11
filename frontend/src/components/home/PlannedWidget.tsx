import { ArrowIcon } from '@assets/svg/calendar'

const PlannedWidget = () => {
  return (
    <div className='flex w-80 items-center justify-between bg-bg-50/10 p-1.5 rounded-full'>
      <div className='flex items-center'>
        <div className='flex items-center bg-bg-50/10 mr-2 pl-5 pr-2.5 py-2.5 rounded-full'>
          <p className='text-sm font-medium font-paperlogy mr-1.5'>별 보는 날</p>
          <ArrowIcon className='w-5 transform -rotate-90' />
        </div>
        <div className='flex flex-col'>
          <p className='text-sm truncate max-w-36'>2024.10.14 22:00</p>
          <p className='text-sm leading-none truncate max-w-36 text-text-tertiary'>
            송암 스페이스 센터
          </p>
        </div>
      </div>
      <div className='flex items-center justify-center w-10 h-10 rounded-full bg-bg-50/10'>
        <p className='font-medium text-comp1 pt-0.5'>100</p>
      </div>
    </div>
  )
}

export default PlannedWidget
