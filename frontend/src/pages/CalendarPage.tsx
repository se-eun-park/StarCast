import { PlaceWeatherCardDummyData } from '@dummy/dummy'
import PlaceWeatherCard from '@components/calendar/PlaceWeatherCard'

const CalendarPage = () => {
  return (
    // 테스트를 위해 하드코딩 했습니다.
    <div className='h-full min-h-screen bg-bg-900'>
      <h1>캘린더 페이지</h1>
      <PlaceWeatherCard
        place_uid={PlaceWeatherCardDummyData.myGPS.place_uid}
        details={PlaceWeatherCardDummyData.myGPS.details}
        weatherOfTheNight={PlaceWeatherCardDummyData.myGPS.weatherOfTheNight}
        best={PlaceWeatherCardDummyData.myGPS.best}
        moonSetTime={PlaceWeatherCardDummyData.myGPS.moonSetTime}
        isPlanned={PlaceWeatherCardDummyData.myGPS.isPlanned}
      />
      <br />
      <PlaceWeatherCard
        place_uid={PlaceWeatherCardDummyData.myPlace.place_uid}
        details={PlaceWeatherCardDummyData.myPlace.details}
        weatherOfTheNight={PlaceWeatherCardDummyData.myPlace.weatherOfTheNight}
        best={PlaceWeatherCardDummyData.myPlace.best}
        moonSetTime={PlaceWeatherCardDummyData.myPlace.moonSetTime}
        isPlanned={PlaceWeatherCardDummyData.myPlace.isPlanned}
      />
    </div>
  )
}

export default CalendarPage
