import {
  BASIC_1,
  BASIC_2,
  BASIC_3,
  BRONZE_1,
  BRONZE_2,
  SILVER_1,
  SILVER_2,
  SILVER_3,
  GOLD_1,
  GOLD_2,
  GOLD_3,
  PLATINUM_1,
  PLATINUM_2,
} from '@assets/svg/profile'
import RadioGroup from '@components/common/RadioGroup'
import Radio from '@components/common/Radio'
import { useMemo, useState } from 'react'

type TierAvatarSelectorProps = {
  tier: string
}

const TierAvatarSelector = ({ tier }: TierAvatarSelectorProps) => {
  const [isChecked, setIsChecked] = useState(false)
  const [numTier, setNumTier] = useState(0)

  const handleOnSubmit = (e: React.FormEvent<HTMLFormElement>) => {
    e.preventDefault()
    const target = e.target as HTMLFormElement

    console.log(target.tier.value)
  }

  const handleOnChange = () => {
    setIsChecked(true)
  }

  useMemo(() => {
    switch (tier) {
      case 'BRONZE':
        setNumTier(1)
        break
      case 'SILVER':
        setNumTier(2)
        break
      case 'GOLD':
        setNumTier(3)
        break
      case 'PLATINUM':
        setNumTier(4)
        break
    }
  }, [tier])

  return (
    <div className='flex flex-col items-center'>
      <form onSubmit={handleOnSubmit} onChange={handleOnChange}>
        <RadioGroup className='mb-20'>
          <h1 className='mt-4 mb-2 text-sm font-semibold text-text-tertiary'>Basic</h1>
          <div className='flex space-x-10'>
            <Radio name='tier' value='BASIC_1'>
              <BASIC_1 className='w-[3.75rem] cursor-pointer outline-primary rounded-full peer-checked:outline-4 peer-checked:outline' />
            </Radio>
            <Radio name='tier' value='BASIC_2'>
              <BASIC_2 className='w-[3.75rem] cursor-pointer outline-primary rounded-full peer-checked:outline-4 peer-checked:outline' />
            </Radio>
            <Radio name='tier' value='BASIC_3'>
              <BASIC_3 className='w-[3.75rem] cursor-pointer outline-primary rounded-full peer-checked:outline-4 peer-checked:outline' />
            </Radio>
          </div>

          <h1 className='mt-3 mb-2 text-sm font-semibold text-comp2'>Bronze</h1>
          <div className='flex space-x-10'>
            <Radio name='tier' value='BRONZE_1' disabled={numTier < 1}>
              <div className='relative flex items-center justify-center rounded-full outline-primary peer-checked:outline-4 peer-checked:outline'>
                <BRONZE_1
                  className={`w-[3.75rem] ${numTier < 1 ? 'opacity-30' : 'cursor-pointer'}`}
                />
                <p className={`absolute text-xs font-semibold ${numTier < 1 ? '' : 'hidden'}`}>
                  미획득
                </p>
              </div>
            </Radio>
            <Radio name='tier' value='BRONZE_2' disabled={numTier < 1}>
              <div className='relative flex items-center justify-center rounded-full outline-primary peer-checked:outline-4 peer-checked:outline'>
                <BRONZE_2
                  className={`w-[3.75rem] ${numTier < 1 ? 'opacity-30' : 'cursor-pointer'}`}
                />
                <p className={`absolute text-xs font-semibold ${numTier < 1 ? '' : 'hidden'}`}>
                  미획득
                </p>
              </div>
            </Radio>
          </div>

          <h1 className='mt-3 mb-2 text-sm font-semibold text-bg-50'>Silver</h1>
          <div className='flex space-x-10'>
            <Radio name='tier' value='SILVER_1' disabled={numTier < 2}>
              <div className='relative flex items-center justify-center rounded-full outline-primary peer-checked:outline-4 peer-checked:outline'>
                <SILVER_1
                  className={`w-[3.75rem] ${numTier < 2 ? 'opacity-30' : 'cursor-pointer'}`}
                />
                <p className={`absolute text-xs font-semibold ${numTier < 2 ? '' : 'hidden'}`}>
                  미획득
                </p>
              </div>
            </Radio>
            <Radio name='tier' value='SILVER_2' disabled={numTier < 2}>
              <div className='relative flex items-center justify-center rounded-full outline-primary peer-checked:outline-4 peer-checked:outline'>
                <SILVER_2
                  className={`w-[3.75rem] ${numTier < 2 ? 'opacity-30' : 'cursor-pointer'}`}
                />
                <p className={`absolute text-xs font-semibold ${numTier < 2 ? '' : 'hidden'}`}>
                  미획득
                </p>
              </div>
            </Radio>
            <Radio name='tier' value='SILVER_3' disabled={numTier < 2}>
              <div className='relative flex items-center justify-center rounded-full outline-primary peer-checked:outline-4 peer-checked:outline'>
                <SILVER_3
                  className={`w-[3.75rem] ${numTier < 2 ? 'opacity-30' : 'cursor-pointer'}`}
                />
                <p className={`absolute text-xs font-semibold ${numTier < 2 ? '' : 'hidden'}`}>
                  미획득
                </p>
              </div>
            </Radio>
          </div>

          <h1 className='mt-3 mb-2 text-sm font-semibold text-primary-light'>Gold</h1>
          <div className='flex space-x-10'>
            <Radio name='tier' value='GOLD_1' disabled={numTier < 3}>
              <div className='relative flex items-center justify-center rounded-full outline-primary peer-checked:outline-4 peer-checked:outline'>
                <GOLD_1
                  className={`w-[3.75rem] ${numTier < 3 ? 'opacity-30' : 'cursor-pointer'}`}
                />
                <p className={`absolute text-xs font-semibold ${numTier < 3 ? '' : 'hidden'}`}>
                  미획득
                </p>
              </div>
            </Radio>
            <Radio name='tier' value='GOLD_2' disabled={numTier < 3}>
              <div className='relative flex items-center justify-center rounded-full outline-primary peer-checked:outline-4 peer-checked:outline'>
                <GOLD_2
                  className={`w-[3.75rem] ${numTier < 3 ? 'opacity-30' : 'cursor-pointer'}`}
                />
                <p className={`absolute text-xs font-semibold ${numTier < 3 ? '' : 'hidden'}`}>
                  미획득
                </p>
              </div>
            </Radio>
            <Radio name='tier' value='GOLD_3' disabled={numTier < 3}>
              <div className='relative flex items-center justify-center rounded-full outline-primary peer-checked:outline-4 peer-checked:outline'>
                <GOLD_3
                  className={`w-[3.75rem] ${numTier < 3 ? 'opacity-30' : 'cursor-pointer'}`}
                />
                <p className={`absolute text-xs font-semibold ${numTier < 3 ? '' : 'hidden'}`}>
                  미획득
                </p>
              </div>
            </Radio>
          </div>

          <h1 className='mt-3 mb-2 text-sm font-semibold text-comp3-light'>Platinum</h1>
          <div className='flex space-x-10'>
            <Radio name='tier' value='PLATINUM_1' disabled={numTier < 4}>
              <div className='relative flex items-center justify-center rounded-full outline-primary peer-checked:outline-4 peer-checked:outline'>
                <PLATINUM_1
                  className={`w-[3.75rem] ${numTier < 4 ? 'opacity-30' : 'cursor-pointer'}`}
                />
                <p className={`absolute text-xs font-semibold ${numTier < 4 ? '' : 'hidden'}`}>
                  미획득
                </p>
              </div>
            </Radio>
            <Radio name='tier' value='PLATINUM_2' disabled={numTier < 4}>
              <div className='relative flex items-center justify-center rounded-full outline-primary peer-checked:outline-4 peer-checked:outline'>
                <PLATINUM_2
                  className={`w-[3.75rem] ${numTier < 4 ? 'opacity-30' : 'cursor-pointer'}`}
                />
                <p className={`absolute text-xs font-semibold ${numTier < 4 ? '' : 'hidden'}`}>
                  미획득
                </p>
              </div>
            </Radio>
          </div>
        </RadioGroup>
        <div className='fixed flex justify-center left-1/2 transform -translate-x-1/2 bottom-0 max-w-[37.5rem] w-full bg-bg-900 border-t border-bg-50/30 py-3 px-4'>
          <button
            disabled={!isChecked}
            type='submit'
            className='btn-primary-full max-w-80 disabled:btn-disabled-full'
          >
            캐스타 프로필 변경하기
          </button>
        </div>
      </form>
    </div>
  )
}

export default TierAvatarSelector
