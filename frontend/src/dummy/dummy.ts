export const PlaceWeatherCardDummyData = {
  myGPS: {
    place_uid: 'asfdfsafassf',
    address: '서울특별시 강남구 역삼동',
    details: '역삼동',
    type: 'EUP_MYUN_DONG',
    weatherOfTheNight: {
      hour21: {
        castarPoint: Math.floor(Math.random() * 22) + 1,
        cloudCoverage: 1,
        precipitation: 1,
      },
      hour22: {
        castarPoint: Math.floor(Math.random() * 22) + 1,
        cloudCoverage: 2,
        precipitation: 2,
      },
      hour23: {
        castarPoint: Math.floor(Math.random() * 22) + 1,
        cloudCoverage: 1,
        precipitation: 0,
      },
      hour00: {
        castarPoint: Math.floor(Math.random() * 22) + 1,
        cloudCoverage: 3,
        precipitation: 3,
      },
      hour01: {
        castarPoint: 23,
        cloudCoverage: 2,
        precipitation: 1,
      },
      hour02: {
        castarPoint: Math.floor(Math.random() * 22) + 1,
        cloudCoverage: 2,
        precipitation: 1,
      },
    },
    best: {
      bestCastarPoint: 23,
      hour: '01',
      moonPhase: 3,
      cloudCoverage: 2,
      lightPollution: 1,
      humidity: 20,
    },
    moonSetTime: '04:00',
    isPlanned: null,
    // isPlanned: {
    //   spot_uid: 'S827483fjwe7fb7',
    //   hour: '21',
    // },
  },
  myPlace: {
    place_uid: 'asfkdafjslfjsaf',
    address: '경기 연천군 연천읍',
    details: '연천읍',
    type: 'EUP_MYUN_DONG',
    weatherOfTheNight: {
      hour21: {
        castarPoint: Math.floor(Math.random() * 91) + 1,
        cloudCoverage: 1,
        precipitation: 1,
      },
      hour22: {
        castarPoint: Math.floor(Math.random() * 91) + 1,
        cloudCoverage: 2,
        precipitation: 2,
      },
      hour23: {
        castarPoint: 92,
        cloudCoverage: 1,
        precipitation: 0,
      },
      hour00: {
        castarPoint: Math.floor(Math.random() * 91) + 1,
        cloudCoverage: 3,
        precipitation: 3,
      },
      hour01: {
        castarPoint: Math.floor(Math.random() * 91) + 1,
        cloudCoverage: 1,
        precipitation: 4,
      },
      hour02: {
        castarPoint: Math.floor(Math.random() * 91) + 1,
        cloudCoverage: 2,
        precipitation: 1,
      },
    },
    best: {
      bestCastarPoint: 92,
      hour: '23',
      moonPhase: 1,
      cloudCoverage: 1,
      lightPollution: 3,
      humidity: 20,
    },
    moonSetTime: '05:00',
    isPlanned: null,
  },
  favoritePlaces: [
    {
      place_uid: 'askfjldfkjlafsdlfa',
      address: '서울특별시 강남구 역삼동',
      details: '송암스페이스센터',
      type: 'OBSERVATORY',
      weatherOfTheNight: {
        hour21: {
          castarPoint: 55,
          cloudCoverage: 1,
          precipitation: 0,
        },
        hour22: {
          castarPoint: Math.floor(Math.random() * 54) + 1,
          cloudCoverage: 2,
          precipitation: 2,
        },
        hour23: {
          castarPoint: Math.floor(Math.random() * 54) + 1,
          cloudCoverage: 1,
          precipitation: 0,
        },
        hour00: {
          castarPoint: Math.floor(Math.random() * 54) + 1,
          cloudCoverage: 3,
          precipitation: 3,
        },
        hour01: {
          castarPoint: Math.floor(Math.random() * 54) + 1,
          cloudCoverage: 1,
          precipitation: 4,
        },
        hour02: {
          castarPoint: Math.floor(Math.random() * 54) + 1,
          cloudCoverage: 2,
          precipitation: 1,
        },
      },
      best: {
        bestCastarPoint: 55,
        hour: '21',
        moonPhase: 2,
        cloudCoverage: 1,
        lightPollution: 1,
        humidity: 20,
      },
      moonSetTime: '05:30',
      isPlanned: null,
    },
  ],
}
