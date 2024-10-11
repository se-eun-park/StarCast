interface HomeDummyType {
  [key: string]: {
    status: string
    score: number
    details: string
    hour: string
    cloudCoverage: number
    lightPollution: number
    moonPhase: number
    precipitation: number
    moonSetTime: string
  }

  GPS: {
    status: string
    score: number
    details: string
    hour: string
    cloudCoverage: number
    lightPollution: number
    moonPhase: number
    precipitation: number
    moonSetTime: string
  }
  MYPLACE: {
    status: string
    score: number
    details: string
    hour: string
    cloudCoverage: number
    lightPollution: number
    moonPhase: number
    precipitation: number
    moonSetTime: string
  }
}

export const homeDummy: HomeDummyType = {
  GPS: {
    status: '나쁨',
    score: 23,
    details: '역삼동',
    hour: '01',
    cloudCoverage: 2,
    lightPollution: 1,
    moonPhase: 3,
    precipitation: 1,
    moonSetTime: '04:00',
  },
  MYPLACE: {
    status: '좋음',
    score: 92,
    details: '연천읍',
    hour: '23',
    cloudCoverage: 1,
    lightPollution: 3,
    moonPhase: 1,
    precipitation: 0,
    moonSetTime: '05:00',
  },
}
