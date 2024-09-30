import { useState } from 'react'
import { GetPlaceWeatherResponse } from '../../types/apis'
import AstronomyInfoWidget from '@components/common/AstronomyInfoWidget'
import { ArrowIcon, LocationIcon, PlusIcon } from '@assets/svg/calendar'

const PlaceWeatherCard = ({
  place_uid,
  details,
  weatherOfTheNight,
  best,
  moonSetTime,
  isPlanned,
}: GetPlaceWeatherResponse) => {
  const scoreStatus =
    best.bestCastarPoint >= 75 ? '좋음' : best.bestCastarPoint >= 40 ? '보통' : '나쁨'

  const timeList = ['21', '22', '23', '00', '01', '02']

  const [istoggled, setIstoggled] = useState(false)
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

  const handleOnClickPlusButton = () => {
    // 나중엔 즐겨찾기 추가 api 호출
    console.log('즐겨찾기 추가 완료!', 'id:', place_uid, '시간:', selectedTime)
  }

  return (
    <>
      <button
        onClick={handleOnClickToggleButton}
        className={`w-[20.5rem] h-[7.5rem] bg-bg-700 py-4 px-5 ${istoggled ? 'rounded-t-2xl' : 'rounded-2xl delay-200 transition-all'}`}
      >
        <div className='flex items-center justify-between'>
          <div className='flex'>
            <h1 className='mr-1 font-semibold text-white'>{details}</h1>
            <LocationIcon className='w-4' />
          </div>
          <ArrowIcon
            className={`w-6 transition duration-200 ${istoggled ? ' rotate-180' : 'rotate-0'}`}
          />
        </div>

        <div className='flex items-center justify-between mt-2.5'>
          <div className='text-left'>
            <h2 className='text-2xl font-semibold text-white mb-0.5'>{scoreStatus}</h2>
            <p className='text-xs text-white'>{best.hour}시에 제일 잘 보여요!</p>
          </div>
          <div className='flex items-center justify-center bg-white rounded-full w-14 h-14 bg-opacity-10'>
            <span className='text-2xl font-semibold text-primary'>{best.bestCastarPoint}</span>
          </div>
        </div>
      </button>

      {/* 확장 화면 */}
      <div
        className={`w-[20.5rem] ${istoggled ? 'h-[17rem]' : 'h-0'} bg-bg-700 rounded-b-2xl duration-300 transition-height flex felx-col justify-center relative`}
      >
        <div
          className={`absolute top-5 grid grid-cols-6 gap-x-1.5 transition-all duration-300 ease-out ${istoggled ? 'opacity-100' : 'opacity-0 scale-95 pointer-events-none'}`}
        >
          {timeList.map((time, idx) => (
            <div
              key={idx}
              onClick={() => handleOnClickTimeButton(time)}
              className={`flex flex-col items-center justify-center w-11 rounded-full ${isPlanned === null ? 'transition-height duration-200' : 'transition-colors duration-100'}  ${selectedTime === time ? 'bg-primary pt-4 pb-1.5 h-full' : isPlanned?.hour === time ? 'bg-secondary-dark' : 'bg-white bg-opacity-10 max-h-[4.125rem] h-[4.125rem] py-4 cursor-pointer hover:bg-bg-500'}`}
            >
              <p
                className={`text-[0.625rem] leading-none text-opacity-70 text-center mb-1.5 ${selectedTime === time ? 'text-bg-700' : 'text-white '}`}
              >
                {parseInt(time)}시
              </p>
              <p
                className={`font-semibold text-center leading-none ${selectedTime === time ? 'text-bg-900 mb-2' : 'text-white '}`}
              >
                {weatherOfTheNight[`hour${time}`]['castarPoint']}
              </p>

              {isPlanned === null && (
                <button
                  onClick={handleOnClickPlusButton}
                  className={`flex items-center justify-center rounded-full bg-primary-light ${selectedTime === time ? 'w-8 h-8 transition-all duration-200' : 'w-0 h-0'}`}
                >
                  <PlusIcon
                    className={selectedTime === time ? 'w-3.5 transition-all duration-200' : 'w-0'}
                  />
                </button>
              )}
            </div>
          ))}
        </div>
        <div
          className={`absolute bottom-5 transition-all duration-100 ease-in-out ${istoggled ? 'opacity-100 delay-150' : 'opacity-0 scale-95 pointer-events-none'}`}
        >
          <AstronomyInfoWidget
            bgColor='bg-black bg-opacity-30'
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
          className={`w-[20.5rem] ${istoggled ? 'h-[17rem]' : 'h-0'} bg-black bg-opacity-20 rounded-b-2xl transition-all`}
        />
      </div>
    </>
  )
}

export default PlaceWeatherCard
