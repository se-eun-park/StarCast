type WeatherDetail = {
  castarPoint: number
  cloudCoverage: number
  precipitation: number
}

type WeatherOfTheNight = {
  [key: `hour${string}`]: WeatherDetail
}

type Best = {
  bestCastarPoint: number
  hour: string
  cloudCoverage: number
  lightPollution: number
  moonPhase: number
  humidity: number
}

type IsPlanned = {
  hour: string
  spot_uid: string
}

export type GetPlaceWeatherResponse = {
  place_uid: string
  details: string
  weatherOfTheNight: WeatherOfTheNight
  best: Best
  moonSetTime: string
  isPlanned: IsPlanned | null
}
