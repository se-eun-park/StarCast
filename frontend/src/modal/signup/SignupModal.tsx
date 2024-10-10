import { useEffect, useState } from 'react'
import useModal from '@hooks/useModal'

const SignupModal = () => {
  const { Modal, open, close } = useModal()

  const [checkboxStates, setCheckboxStates] = useState<{
    allAgree: boolean
    locationInfo: boolean
    notification: boolean
  }>({
    allAgree: false,
    locationInfo: false,
    notification: false,
  })

  const handleCheckboxChange = (name: keyof typeof checkboxStates) => {
    setCheckboxStates((prev) => {
      const newStates = {
        ...prev,
        [name]: !prev[name],
      }

      if (name === 'allAgree') {
        return {
          ...newStates,
          locationInfo: true,
          notification: true,
        }
      }

      if (name === 'locationInfo' || name === 'notification') {
        if (!newStates[name]) {
          return { ...newStates, allAgree: false }
        }
      }

      return newStates
    })
  }

  useEffect(() => {
    open()
  }, [open])

  const isButtonActive = checkboxStates.allAgree || checkboxStates.locationInfo

  return (
    <>
      <Modal>
        <div className='fixed inset-0 z-30 flex items-end justify-center bg-black bg-opacity-70'>
          <div className='w-full max-w-[37.375rem] min-w-[22.5rem] pt-8 pb-6 flex flex-col justify-center items-center rounded-t-3xl bg-bg-800'>
            <div className='w-full max-w-[22.5rem] flex flex-col justify-center items-center gap-8'>
              <div className='w-[17.75rem] flex flex-col items-center'>
                <div className='flex justify-center gap-2'>
                  <p className='text-2xl text-center text-primary-light font-paperlogy font-medium'>
                    반가워요!
                  </p>
                  <p className='text-2xl text-center font-medium text-white font-paperlogy'>
                    초면에 실례지만,
                  </p>
                </div>
                <div>
                  <p className='text-2xl text-center font-medium text-white font-paperlogy'>
                    꼭 받아야 하는 권한이 있어요.
                  </p>
                </div>
              </div>

              <label className='flex items-center justify-center w-full bg-btn-tertiary-text bg-opacity-7 rounded-lg py-4 px-3'>
                <div className='flex-1 text-left'>
                  <p className='text-white text-sm'>전체동의 (선택항목 포함)</p>
                </div>
                <div className='flex-none ml-2 flex items-center'>
                  <input
                    type='checkbox'
                    checked={checkboxStates.allAgree}
                    onChange={() => handleCheckboxChange('allAgree')}
                    className={`appearance-none border-2 border-primary-light rounded w-5 h-5 cursor-pointer 
                      ${checkboxStates.allAgree ? 'bg-primary border-primary' : 'bg-transparent'} 
                      focus:outline-none`}
                  />
                </div>
              </label>

              <label className='flex items-center justify-center w-full px-3'>
                <div className='flex-1 text-left'>
                  <p className='text-white text-xs'>위치정보 (필수)</p>
                  <p className='text-2xs text-text-tertiary pt-[.125rem]'>
                    날씨 데이터 수집, 인근 관측지 추천에 이용돼요
                  </p>
                </div>
                <div className='flex-none ml-2'>
                  <input
                    type='checkbox'
                    checked={checkboxStates.locationInfo}
                    onChange={() => handleCheckboxChange('locationInfo')}
                    className={`appearance-none border-2 border-primary-light rounded w-5 h-5 cursor-pointer 
                      ${checkboxStates.locationInfo ? 'bg-primary border-primary' : 'bg-transparent'} 
                      focus:outline-none`}
                  />
                </div>
              </label>

              <label className='flex items-center justify-center w-full px-3'>
                <div className='flex-1 text-left'>
                  <p className='text-white text-xs'>알림수신 (선택)</p>
                  <p className='text-2xs text-text-tertiary pt-[.125rem]'>
                    천문 현상과 관측 적합도를 알림으로 보내드려요.
                  </p>
                </div>
                <div className='flex-none ml-2'>
                  <input
                    type='checkbox'
                    checked={checkboxStates.notification}
                    onChange={() => handleCheckboxChange('notification')}
                    className={`appearance-none border-2 border-primary-light rounded w-5 h-5 cursor-pointer 
                      ${checkboxStates.notification ? 'bg-primary border-primary' : 'bg-transparent'} 
                      focus:outline-none`}
                  />
                </div>
              </label>
            </div>

            <div className='w-full gap-2 px-5 mt-10 flex flex-col justify-center items-center'>
              <button
                onClick={isButtonActive ? close : undefined}
                type='button'
                className={isButtonActive ? 'btn-secondary-full' : 'btn-disabled-full'}
              >
                {isButtonActive ? '확인' : '필수 권한을 허용해 주세요'}
              </button>
            </div>
          </div>
        </div>
      </Modal>
    </>
  )
}

export default SignupModal
