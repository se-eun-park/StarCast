import {
  NewMoonIcon,
  MoonIcon,
  FullMoonIcon,
  SunIcon,
  PartlyCloudyIcon,
  CloudIcon,
  LightOffIcon,
  LightOnIcon,
  LightMaxIcon,
} from '@assets/svg'
import { useEffect, useState } from 'react'

type AstronomyInfoWidgetProps = {
  bgColor: string
  refresh: boolean
  details: string
  hour: string
  cloudCoverage: number
  lightPollution: number
  moonPhase: number
  precipitation: number
  moonSetTime: string
}

type ContentMapProps = {
  [key: string]: {
    img: JSX.Element
    description: string
    content: string
  }
}

const AstronomyInfoWidget = ({
  bgColor,
  refresh,
  details,
  hour,
  cloudCoverage,
  lightPollution,
  moonPhase,
  precipitation,
  moonSetTime,
}: AstronomyInfoWidgetProps) => {
  useEffect(() => {
    if (refresh) {
      setSelectedButton('달')
    }
  }, [refresh])

  const moonPhaseStatus = moonPhase === 1 ? '작음' : moonPhase === 2 ? '보통' : '큼'

  const cloudCoverageStatus =
    cloudCoverage === 1 ? '맑음' : cloudCoverage === 2 ? '구름많음' : '흐림'

  const lightPollutionStatus =
    lightPollution === 1 ? '나쁨' : lightPollution === 2 ? '보통' : '좋음'

  const precipitationStatus =
    precipitation === 0
      ? '맑을 것으로 예상됩니다.'
      : precipitation === 1
        ? '비가 내릴 것으로 보입니다.'
        : precipitation === 2
          ? '비와 눈이 섞여 내릴 가능성이 있습니다.'
          : precipitation === 3
            ? '눈이 내릴 것으로 예상됩니다.'
            : '소나기가 내릴 것으로 보입니다.'

  const contentsMap: ContentMapProps = {
    달: {
      img:
        moonPhase === 1 ? (
          <NewMoonIcon className='w-8' />
        ) : moonPhase === 2 ? (
          <MoonIcon className='w-8' />
        ) : (
          <FullMoonIcon className='w-8' />
        ),
      description: '달이 작거나 없으면 천체를 더 잘 볼 수 있어요.',
      content: `오늘의 월몰 시각은 ${moonSetTime} 입니다.`,
    },
    구름: {
      img:
        cloudCoverage === 1 ? (
          <SunIcon className='w-8' />
        ) : cloudCoverage === 2 ? (
          <PartlyCloudyIcon className='w-8' />
        ) : (
          <CloudIcon className='w-8' />
        ),
      description: '구름량은 관측에 가장 큰 영향을 줘요.',
      content: `${parseInt(hour)}시 날씨는 ${precipitationStatus}`,
    },
    광공해: {
      img:
        lightPollution === 1 ? (
          <LightMaxIcon className='w-8' />
        ) : lightPollution === 2 ? (
          <LightOnIcon className='w-8' />
        ) : (
          <LightOffIcon className='w-8' />
        ),
      description: '도심지 불빛에서 멀어질 수록 더 잘 보여요.',
      content: `${details} 광공해 점수는 ${lightPollutionStatus}입니다.`,
    },
  }

  const [selectedButton, setSelectedButton] = useState('달')

  const handleOnClickButton = (buttonName: string) => {
    setSelectedButton(buttonName)
  }

  return (
    <div className='flex flex-col'>
      <div className='grid grid-cols-3 gap-x-5'>
        <button
          onClick={() => handleOnClickButton('달')}
          className={`flex flex-col items-center px-5 py-1.5 rounded-2xl ${selectedButton === '달' && bgColor}`}
        >
          {contentsMap['달'].img}
          <span className='mt-1 text-xs text-white text-opacity-60'>달</span>
          <span className='mt-0.5 text-xs text-white'>{moonPhaseStatus}</span>
        </button>

        <button
          onClick={() => handleOnClickButton('구름')}
          className={`flex flex-col items-center px-3 py-1.5 rounded-2xl ${selectedButton === '구름' && bgColor}`}
        >
          {contentsMap['구름'].img}
          <span className='mt-1 text-xs text-white text-opacity-60'>구름</span>
          <span className='mt-0.5 text-xs text-white'>{cloudCoverageStatus}</span>
        </button>

        <button
          onClick={() => handleOnClickButton('광공해')}
          className={`flex flex-col items-center px-3 py-1.5 rounded-2xl ${selectedButton === '광공해' && bgColor}`}
        >
          {contentsMap['광공해'].img}
          <span className='mt-1 text-xs text-white text-opacity-60'>광공해</span>
          <span className='mt-0.5 text-xs text-white'>{lightPollutionStatus}</span>
        </button>
      </div>
      <div className='flex flex-col pl-2 mt-2.5'>
        <p className='text-xs text-white'>{contentsMap[selectedButton].description}</p>
        <p className='text-xs text-white'>{contentsMap[selectedButton].content}</p>
      </div>
    </div>
  )
}

export default AstronomyInfoWidget
