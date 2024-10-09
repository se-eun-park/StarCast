import { SearchIcon, CancelIcon } from '@assets/svg'
import { ArrowIcon } from '@assets/svg/calendar'
import { useState } from 'react'

const locationList = [
  { address: '서울 강남구 신사동' },
  { address: '서울 강남구 역삼동' },
  { address: '서울 강남구 역삼동' },
  { address: '서울 강남구 역삼동' },
  { address: '서울 강남구 역삼동' },
  { address: '서울 강남구 역삼동' },
  { address: '서울 강남구 역삼동' },
  { address: '서울 강남구 역삼동' },
  { address: '서울 강남구 역삼동' },
  { address: '서울 강남구 역삼동' },
  { address: '서울 강남구 역삼동' },
  { address: '서울 강남구 역삼동' },
  { address: '서울 강남구 역삼동' },
]

const LocationSettingPage = () => {
  const [address, setAddress] = useState('서울 강남구 압구정동')
  const [isSearching, setIsSearching] = useState(true)

  const handleOnChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    setAddress(e.target.value)
  }

  const handleOnClickAddress = (address: string) => {
    setAddress(address)
    setIsSearching(true)
  }

  const handleOnClickCancel = () => {
    setAddress('')
    setIsSearching(false)
  }

  return (
    <div className='h-[calc(100dvh-56px)] w-full bg-bg-900 flex flex-col items-center'>
      <div className='flex flex-col w-full px-6 py-4'>
        <h1 className='mb-1 text-xl font-medium font-paperlogy'>내 위치 설정하기</h1>
        <h2 className='font-medium font-paperlogy text-text-secondary'>
          홈 화면 기준 위치를 직접 설정할 수 있어요
        </h2>
      </div>

      <div className='relative flex items-center w-full p-4 bg-gradient900to800 rounded-b-2xl'>
        <input
          disabled={isSearching}
          value={address}
          onChange={handleOnChange}
          type='text'
          className='w-full py-4 pl-4 pr-12 text-sm rounded-lg bg-bg-50/7 text-text-primary focus:outline-primary-light focus:outline disabled:text-primary-light'
        />

        {isSearching ? (
          <button onClick={handleOnClickCancel} className='absolute right-8'>
            <CancelIcon className='w-5' />
          </button>
        ) : (
          <button className='absolute right-8'>
            <SearchIcon className='w-5' />
          </button>
        )}
      </div>
      {!isSearching && (
        <div className='flex flex-col w-full px-8 overflow-y-auto'>
          <h3 className='py-4 font-semibold border-b border-bg-50/20'>검색 결과</h3>
          <div className='flex flex-col w-full overflow-y-auto divide-y scrollbar-hide divide-solid divide-bg-50/20'>
            {locationList.map((location, index) => (
              <button
                key={index}
                onClick={() => handleOnClickAddress(location.address)}
                className='flex items-center justify-between px-2 py-4 hover:bg-bg-50/10'
              >
                <p className='text-sm font-medium'>{location.address}</p>
                <ArrowIcon className='w-5 transform -rotate-90 fill-text-primary' />
              </button>
            ))}
          </div>
        </div>
      )}
    </div>
  )
}

export default LocationSettingPage
