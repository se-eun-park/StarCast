import { useEffect, useState } from 'react'
import type { GetPlaceWeatherResponse } from '../../types/apis'
import AstronomyInfoWidget from '@components/common/AstronomyInfoWidget'
import { ArrowIcon, LocationIcon, PlusIcon, CheckIcon, DeleteIcon } from '@assets/svg/calendar'
import {
  SunIcon,
  PartlyCloudyIcon,
  CloudIcon,
  DarkSunIcon,
  DarkPartlyCloudyIcon,
  DarkCloudIcon,
  CancelIcon,
} from '@assets/svg'
import ReservationModal from '@modal/calendar/ReservationModal'
import DeleteReservationModal from '@modal/calendar/DeleteReservationModal'
import useModal from '@hooks/useModal'

const PlaceWeatherCard = ({
  idx,
  place_uid,
  details,
  weatherOfTheNight,
  best,
  moonSetTime,
  isPlanned,
}: GetPlaceWeatherResponse) => {
  const { Modal, open, close } = useModal()

  const scoreStatus =
    best.bestCastarPoint >= 75 ? '좋음' : best.bestCastarPoint >= 40 ? '보통' : '나쁨'

  const timeList = ['21', '22', '23', '00', '01', '02']

  const [istoggled, setIstoggled] = useState(false)
  const [isReserved, setIsReserved] = useState(false)
  const [isReservedAnimation, setIsReservedAnimation] = useState(false)
  const [isDelReservedAnimation, setIsDelReservedAnimation] = useState(false)
  const [isDelReserved, setIsDelReserved] = useState(false)
  const [reservedState, setReservedState] = useState(0)
  const [delReservedState, setDelReservedState] = useState(0)
  const [refresh, setRefresh] = useState(true)
  const [selectedTime, setSelectedTime] = useState('')
  const [astronomyInfo, setAstronomyInfo] = useState({
    details: details,
    hour: best.hour,
    cloudCoverage: best.cloudCoverage,
    lightPollution: best.lightPollution,
    moonPhase: best.moonPhase,
    precipitation: weatherOfTheNight[`hour${best.hour}`].precipitation,
    moonSetTime: moonSetTime,
  })

  useEffect(() => {
    if (reservedState !== 0) {
      setIsReserved(true)
      setIsReservedAnimation(true)

      setTimeout(() => {
        setIsReservedAnimation(false)
        setTimeout(() => {
          setIsReserved(false)
        }, 100)
      }, 2000)
    }
  }, [reservedState])

  useEffect(() => {
    if (delReservedState !== 0) {
      setIsDelReserved(true)
      setIsDelReservedAnimation(true)

      setTimeout(() => {
        setIsDelReservedAnimation(false)
        setTimeout(() => {
          setIsDelReserved(false)
        }, 100)
      }, 2000)
    }
  }, [delReservedState])

  const handleOnClickToggleButton = () => {
    setIstoggled(!istoggled)
    setRefresh(!refresh)
    setSelectedTime('')
  }

  const handleOnClickTimeButton = (time: string) => {
    setSelectedTime(time)
    setAstronomyInfo({
      ...astronomyInfo,
      hour: time,
      cloudCoverage: weatherOfTheNight[`hour${time}`].cloudCoverage,
      precipitation: weatherOfTheNight[`hour${time}`].precipitation,
    })
  }

  const handleOnClickButton = () => {
    open()
  }

  return (
    <>
      <button
        onClick={handleOnClickToggleButton}
        className={`w-full max-w-96 relative h-[7.5rem] bg-bg-700 py-4 px-5 ${istoggled ? 'rounded-t-2xl' : 'rounded-2xl delay-200 transition-all mb-3'}`}
      >
        <div className='flex items-center justify-between'>
          <div className='flex items-center'>
            <h1 className='mr-1 font-semibold text-white'>{details}</h1>
            <LocationIcon className='w-4' />
            {idx === 0 && (
              <p className='px-2 py-1 ml-2 text-xs font-semibold leading-none border rounded-full text-bg-50 border-bg-50'>
                내 위치
              </p>
            )}
          </div>
          <ArrowIcon
            className={`w-6 transition duration-200 ${istoggled ? ' rotate-180' : 'rotate-0'}`}
          />
        </div>

        <div className='flex items-center justify-between mt-2.5'>
          <div className='text-left'>
            <h2
              className={`text-2xl font-medium font-paperlogy mb-0.5 ${scoreStatus === '좋음' ? 'text-comp1-light' : scoreStatus === '보통' ? 'text-primary-light' : 'text-comp2-light'}`}
            >
              {scoreStatus}
            </h2>
            <p className='text-xs text-text-secondary'>{best.hour}시에 제일 잘 보여요!</p>
          </div>
          <div className='flex items-center justify-center bg-white rounded-full w-14 h-14 bg-opacity-10'>
            <span
              className={`text-2xl font-semibold ${scoreStatus === '좋음' ? 'text-comp1' : scoreStatus === '보통' ? 'text-primary' : 'text-comp2'}`}
            >
              {best.bestCastarPoint}
            </span>
          </div>
        </div>
      </button>

      {/* 확장 화면 */}
      <div
        className={`w-full max-w-96 ${istoggled ? 'h-[18.125rem] mb-3' : 'h-0'} bg-bg-700 rounded-b-2xl duration-300 transition-height flex felx-col justify-center relative`}
      >
        <div
          className={`absolute top-5 grid grid-cols-6 gap-x-1.5 transition-all duration-300 ease-out ${istoggled ? 'opacity-100' : 'opacity-0 scale-95 pointer-events-none'}`}
        >
          {timeList.map((time, idx) => (
            <div
              key={idx}
              onClick={() => handleOnClickTimeButton(time)}
              className={`flex flex-col items-center justify-center w-11 rounded-full ${isPlanned === null && selectedTime === time ? 'transition-height duration-200 pt-4 pb-1.5 h-full' : isPlanned?.hour === time && selectedTime === time ? 'transition-height duration-200 pt-4 pb-1.5 h-full bg-comp3-light' : 'transition-colors duration-100'}  ${selectedTime === time && isPlanned?.hour !== time ? 'bg-primary-light' : isPlanned?.hour === time ? 'bg-comp3-light' : 'bg-bg-50 bg-opacity-10 max-h-[5.125rem] h-[5.125rem] py-3 cursor-pointer hover:bg-black/30'}`}
            >
              <p
                className={`text-[0.625rem] leading-none text-center mb-1.5 ${selectedTime === time || isPlanned?.hour === time ? 'text-bg-900' : 'text-text-tertiary '}`}
              >
                {parseInt(time)}시
              </p>
              {weatherOfTheNight[`hour${time}`]['cloudCoverage'] === 1 ? (
                selectedTime === time || isPlanned?.hour === time ? (
                  <DarkSunIcon className='w-5' />
                ) : (
                  <SunIcon className='w-5' />
                )
              ) : weatherOfTheNight[`hour${time}`]['cloudCoverage'] === 2 ? (
                selectedTime === time || isPlanned?.hour === time ? (
                  <DarkPartlyCloudyIcon className='w-5' />
                ) : (
                  <PartlyCloudyIcon className='w-5' />
                )
              ) : selectedTime === time || isPlanned?.hour === time ? (
                <DarkCloudIcon className='w-5' />
              ) : (
                <CloudIcon className='w-5' />
              )}
              <p
                className={`font-semibold text-center leading-none ${selectedTime === time || isPlanned?.hour === time ? 'text-bg-900 mb-0.5' : 'text-text-primary '}`}
              >
                {weatherOfTheNight[`hour${time}`]['castarPoint']}
              </p>

              {isPlanned === null ? (
                <button
                  onClick={handleOnClickButton}
                  className={`flex items-center justify-center rounded-full bg-bg-900/10 ${selectedTime === time ? 'w-8 h-8 transition-all duration-200' : 'w-0 h-0'}`}
                >
                  <PlusIcon
                    className={selectedTime === time ? 'w-3.5 transition-all duration-200' : 'w-0'}
                  />
                </button>
              ) : (
                isPlanned?.hour === time && (
                  <button
                    onClick={handleOnClickButton}
                    className={`flex items-center justify-center rounded-full bg-bg-900/10 ${selectedTime === time ? 'w-8 h-8 transition-all duration-200' : 'w-0 h-0'}`}
                  >
                    <CancelIcon
                      className={
                        selectedTime === time
                          ? 'w-6 transition-all duration-200 fill-bg-900/70'
                          : 'w-0'
                      }
                    />
                  </button>
                )
              )}
            </div>
          ))}
        </div>
        <div
          className={`absolute bottom-4 transition-all duration-100 ease-in-out ${istoggled ? 'opacity-100 delay-150' : 'opacity-0 scale-95 pointer-events-none'}`}
        >
          <AstronomyInfoWidget
            buttonBgColor='bg-black bg-opacity-30'
            refresh={refresh}
            details={astronomyInfo.details}
            hour={astronomyInfo.hour}
            cloudCoverage={astronomyInfo.cloudCoverage}
            lightPollution={astronomyInfo.lightPollution}
            moonPhase={astronomyInfo.moonPhase}
            precipitation={astronomyInfo.precipitation}
            moonSetTime={astronomyInfo.moonSetTime}
          />
        </div>
        <div
          className={`w-full max-w-96 ${istoggled ? 'h-[18.125rem]' : 'h-0'} bg-black bg-opacity-20 rounded-b-2xl transition-all`}
        />
      </div>
      {isReserved && (
        <div
          className={`fixed z-10 bottom-5 bg-bg-50 max-w-60 py-1.5 pr-3 pl-2 rounded-full left-0 right-0 mx-auto ${isReservedAnimation ? 'animate-slideUp' : 'animate-slideDown'}`}
        >
          <div className='flex items-center'>
            <div className='flex items-center justify-center w-6 mr-2 rounded-full aspect-square bg-bg-900/10'>
              <CheckIcon className='w-6' />
            </div>
            <p className='font-semibold test-xs text-bg-900'>별 보는 날로 등록되었습니다!</p>
          </div>
        </div>
      )}

      {isDelReserved && (
        <div
          className={`fixed z-10 bottom-5 bg-bg-50 max-w-60 py-1.5 pr-3 pl-2 rounded-full left-0 right-0 mx-auto ${isDelReservedAnimation ? 'animate-slideUp' : 'animate-slideDown'}`}
        >
          <div className='flex items-center'>
            <div className='flex items-center justify-center w-6 mr-2 rounded-full aspect-square bg-bg-900/10'>
              <DeleteIcon className='w-6' />
            </div>
            <p className='font-semibold test-xs text-bg-900'>별 보는 날이 삭제되었습니다.</p>
          </div>
        </div>
      )}

      <Modal>
        {isPlanned === null ? (
          <ReservationModal
            onClose={close}
            place_uid={place_uid}
            reservedState={reservedState}
            setReservedState={setReservedState}
          />
        ) : (
          <DeleteReservationModal
            onClose={close}
            place_uid={place_uid}
            delReservedState={delReservedState}
            setDelReservedState={setDelReservedState}
          />
        )}
      </Modal>
    </>
  )
}

export default PlaceWeatherCard
