export const PlaceWeatherCardDummyData = {
  myGPS: {
    place_uid: 'asfdfsafassf',
    address: '서울특별시 강남구 역삼동',
    details: '역삼동',
    type: 'EUP_MYUN_DONG',
    weatherOfTheNight: {
      hour21: {
        castarPoint: Math.floor(Math.random() * 100) + 1,
        cloudCoverage: 1,
        precipitation: 1,
      },
      hour22: {
        castarPoint: Math.floor(Math.random() * 100) + 1,
        cloudCoverage: 2,
        precipitation: 2,
      },
      hour23: {
        castarPoint: 99,
        cloudCoverage: 1,
        precipitation: 0,
      },
      hour00: {
        castarPoint: Math.floor(Math.random() * 100) + 1,
        cloudCoverage: 3,
        precipitation: 3,
      },
      hour01: {
        castarPoint: Math.floor(Math.random() * 100) + 1,
        cloudCoverage: 1,
        precipitation: 4,
      },
      hour02: {
        castarPoint: Math.floor(Math.random() * 100) + 1,
        cloudCoverage: 2,
        precipitation: 1,
      },
    },
    best: {
      bestCastarPoint: 99,
      hour: '23',
      moonPhase: 3,
      cloudCoverage: 2,
      lightPollution: 2,
      humidity: 20,
    },
    moonSetTime: '01:30',
    isPlanned: {
      spot_uid: 'S827483fjwe7fb7',
      hour: '21',
    },
  },
  myPlace: {
    place_uid: 'asfkdafjslfjsaf',
    address: '서울특별시 강남구 압구정동',
    details: '압구정동',
    type: 'EUP_MYUN_DONG',
    weatherOfTheNight: {
      hour21: {
        castarPoint: 65,
        cloudCoverage: 1,
        precipitation: 1,
      },
      hour22: {
        castarPoint: Math.floor(Math.random() * 64) + 1,
        cloudCoverage: 2,
        precipitation: 2,
      },
      hour23: {
        castarPoint: Math.floor(Math.random() * 64) + 1,
        cloudCoverage: 1,
        precipitation: 0,
      },
      hour00: {
        castarPoint: Math.floor(Math.random() * 64) + 1,
        cloudCoverage: 3,
        precipitation: 3,
      },
      hour01: {
        castarPoint: Math.floor(Math.random() * 64) + 1,
        cloudCoverage: 1,
        precipitation: 4,
      },
      hour02: {
        castarPoint: Math.floor(Math.random() * 64) + 1,
        cloudCoverage: 2,
        precipitation: 1,
      },
    },
    best: {
      bestCastarPoint: 65,
      hour: '21',
      moonPhase: 2,
      cloudCoverage: 1,
      lightPollution: 1,
      humidity: 20,
    },
    moonSetTime: '01:30',
    isPlanned: null,
  },
  favoritePlaces: [
    {
      place_uid: 'askfjldfkjlafsdlfa',
      address: '서울특별시 강남구 역삼동',
      details: '멀티캠퍼스천문대',
      type: 'OBSERVATORY',
      weatherOfTheNight: {
        hour21: {
          castarPoint: 99,
          cloudCoverage: 1,
          precipitation: 0,
        },
        hour22: {
          castarPoint: Math.floor(Math.random() * 100) + 1,
          cloudCoverage: 2,
          precipitation: 2,
        },
        hour23: {
          castarPoint: Math.floor(Math.random() * 100) + 1,
          cloudCoverage: 1,
          precipitation: 0,
        },
        hour00: {
          castarPoint: Math.floor(Math.random() * 100) + 1,
          cloudCoverage: 3,
          precipitation: 3,
        },
        hour01: {
          castarPoint: Math.floor(Math.random() * 100) + 1,
          cloudCoverage: 1,
          precipitation: 4,
        },
        hour02: {
          castarPoint: Math.floor(Math.random() * 100) + 1,
          cloudCoverage: 2,
          precipitation: 1,
        },
      },
      best: {
        bestCastarPoint: 99,
        hour: '21',
        moonPhase: 1,
        cloudCoverage: 1,
        lightPollution: 1,
        humidity: 20,
      },
      moonSetTime: '01:30',
      isPlanned: null,
    },
  ],
}
