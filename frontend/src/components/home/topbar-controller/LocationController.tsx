import SvgCloseIcon from '@assets/svg/CloseIcon'
import SvgGtIcon from '@assets/svg/GtIcon'
import { useCheckOutsideClick } from '@hooks/useCheckOutsideClick'
import { useWelcomeMessage } from '@hooks/useWelcomeMessage'
import { useEffect, useRef, useState } from 'react'
import { useNavigate } from 'react-router-dom'
import useLocationControllerState from '@stores/useLocationControllerState'

export default function LocationController() {
  const setLocationState = useLocationControllerState((state) => state.setLocationState)
  const nowLocation = useLocationControllerState((state) => state.nowLocation)
  const setNowLocation = useLocationControllerState((state) => state.setNowLocation)

  const navigate = useNavigate()
  const divRef = useRef<HTMLDivElement>(null)
  const buttonRef = useRef<HTMLButtonElement>(null)
  const [isOpen, setIsOpen] = useState(false)
  const [isExpanded, setIsExpanded] = useState(false)
  const isWelcomeMessageShown = useWelcomeMessage()

  useEffect(() => {
    if (isOpen) {
      setTimeout(() => {
        setIsExpanded(true)
      }, 250)
    } else {
      setIsExpanded(false)
    }
  }, [isOpen])

  useCheckOutsideClick(buttonRef, () => {
    setIsOpen(false)
  })

  return (
    <button
      ref={buttonRef}
      onClick={() => {
        if (!isOpen) setIsOpen(!isOpen)
      }}
      className={`relative font-paperlogy text-sm bg-bg-50/25 backdrop-blur ${!isWelcomeMessageShown && 'opacity-0'} transition-all duration-300 ease-in-out transform rounded-2xl ${isOpen ? 'min-w-56 min-h-32 c cursor-default' : 'min-w-0 min-h-0'}`}
    >
      <div
        ref={divRef}
        className={`flex space-x-[6px] pl-5 pr-[10px] py-[10px] ${isOpen && 'opacity-0 absolute'}`}
      >
        <p>{nowLocation}</p>
        <SvgGtIcon className='w-5 h-5' />
      </div>
      {isOpen && isExpanded && (
        <div className='top-0 left-0 flex-col w-full divide-y lex divide-bg-50/30'>
          <div className='flex items-center justify-between w-full pt-1 pl-5 '>
            <p>내 위치 설정</p>
            <div
              className='flex items-center justify-end h-10 pr-4 cursor-pointer w-14'
              onClick={() => {
                setNowLocation('역삼동')
                setLocationState('GPS')
                setIsOpen(!isOpen)
              }}
            >
              <SvgCloseIcon className='w-4 h-4' />
            </div>
          </div>
          <div className='py-2 '>
            <div className='flex items-center justify-between w-full pl-5 pr-4 cursor-pointer hover:bg-bg-900/30'>
              <div className='py-1'>
                <p>GPS 위치 등록</p>
                <p className='font-pretendard text-2xs text-text-tertiary text-start'>
                  현재 위치 : 역삼동
                </p>
              </div>
              <SvgGtIcon className='w-5 h-5' />
            </div>
            <div
              className='flex items-center justify-between w-full py-2 pl-5 pr-4 cursor-pointer hover:bg-bg-900/30'
              onClick={() => {
                setNowLocation('연천읍')
                setLocationState('MYPLACE')
                navigate('/mypage/location')
              }}
            >
              <p>주소지 직접 입력</p>
              <SvgGtIcon className='w-5 h-5' />
            </div>
          </div>
        </div>
      )}
    </button>
  )
}
