import { useEffect, useState } from 'react'
import { PlaceWeatherCardDummyData } from '@dummy/dummy'
import PlaceWeatherCard from '@components/calendar/PlaceWeatherCard'
import AstroCalendar from '@components/calendar/AstroCalendar'
import { useLocation, useNavigate } from 'react-router-dom'

export default function CalendarPage() {
  const navigate = useNavigate()
  const location = useLocation()
  const [activeTab, setActiveTab] = useState('week')
  const [activeDay, setActiveDay] = useState(1)

  const dayList = Array.from({ length: 7 }, (_, index) => {
    const date = new Date()
    date.setDate(date.getDate() + index)
    return {
      index: index + 1,
      day: date.getDate(),
      month: date.getMonth() + 1,
    }
  })

  useEffect(() => {
    const searchParams = new URLSearchParams(location.search)
    const tab = searchParams.get('tab') || 'week'
    const day = parseInt(searchParams.get('day') || '1', 10)
    if (tab !== 'week') {
      setActiveTab(tab)
    }
    if (day !== 1) {
      setActiveDay(day)
    }
  }, [location.search])

  useEffect(() => {
    navigate(`/calendar?tab=${activeTab}&day=${activeDay}`, { replace: true })
  }, [activeTab, activeDay, navigate])

  const onClickTab = (tab: string) => {
    setActiveTab(tab)
    window.scrollTo(0, 0)
  }

  const onClickDay = (day: number) => {
    setActiveDay(day)
  }

  return (
    <div className='flex flex-col items-center h-full min-h-dvh bg-bg-900'>
      <div className='sticky z-20 flex justify-center w-full h-12 border-b top-14 bg-bg-900 border-bg-50/20'>
        <button
          className={`w-1/4 text-center font-paperlogy text-sm ${activeTab === 'week' ? 'border-b-2 border-bg-50' : 'text-text-tertiary'}`}
          onClick={() => {
            onClickTab('week')
          }}
        >
          주간 예보
        </button>
        <button
          className={`w-1/4 text-center font-paperlogy text-sm ${activeTab === 'month' ? 'border-b-2 border-bg-50' : 'text-text-tertiary'}`}
          onClick={() => {
            onClickTab('month')
          }}
        >
          천문 달력
        </button>
      </div>
      {activeTab === 'week' && (
        <div className='w-full flex flex-col items-center'>
          <div
            className={`sticky z-20 top-[104px] w-full max-w-[600px] flex items-center justify-center py-4 space-x-2 rounded-b-2xl bg-gradient900to800`}
          >
            {dayList.map(({ index, day }) => (
              <button
                key={index}
                className={`px-2 py-1 text-sm w-8 h-8 font-semibold rounded-full ${
                  activeDay === index ? 'text-bg-900 bg-bg-50' : 'text-bg-50'
                }`}
                onClick={() => onClickDay(index)}
              >
                {`${day}`}
              </button>
            ))}
          </div>
          <div className='w-full flex flex-col items-center py-4'>
            <PlaceWeatherCard
              idx={0}
              place_uid={PlaceWeatherCardDummyData.myGPS.place_uid}
              details={PlaceWeatherCardDummyData.myGPS.details}
              weatherOfTheNight={PlaceWeatherCardDummyData.myGPS.weatherOfTheNight}
              best={PlaceWeatherCardDummyData.myGPS.best}
              moonSetTime={PlaceWeatherCardDummyData.myGPS.moonSetTime}
              isPlanned={PlaceWeatherCardDummyData.myGPS.isPlanned}
            />
            <PlaceWeatherCard
              idx={1}
              place_uid={PlaceWeatherCardDummyData.myPlace.place_uid}
              details={PlaceWeatherCardDummyData.myPlace.details}
              weatherOfTheNight={PlaceWeatherCardDummyData.myPlace.weatherOfTheNight}
              best={PlaceWeatherCardDummyData.myPlace.best}
              moonSetTime={PlaceWeatherCardDummyData.myPlace.moonSetTime}
              isPlanned={PlaceWeatherCardDummyData.myPlace.isPlanned}
            />
            <PlaceWeatherCard
              idx={2}
              place_uid={PlaceWeatherCardDummyData.favoritePlaces[0].place_uid}
              details={PlaceWeatherCardDummyData.favoritePlaces[0].details}
              weatherOfTheNight={PlaceWeatherCardDummyData.favoritePlaces[0].weatherOfTheNight}
              best={PlaceWeatherCardDummyData.favoritePlaces[0].best}
              moonSetTime={PlaceWeatherCardDummyData.favoritePlaces[0].moonSetTime}
              isPlanned={PlaceWeatherCardDummyData.favoritePlaces[0].isPlanned}
            />
          </div>
        </div>
      )}
      {activeTab === 'month' && <AstroCalendar />}
    </div>
  )
}
