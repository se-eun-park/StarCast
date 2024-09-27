import { useEffect, useState } from 'react'

import SvgCastar3DIcon from '@assets/svg/Castar3DIcon'

import type { BeforeInstallPromptEvent } from '../../types/pwa'

function A2HSModal() {
  const [a2hs, setA2hs] = useState<BeforeInstallPromptEvent | undefined>(undefined)

  useEffect(() => {
    const handleBeforeInstallPrompt = (e: BeforeInstallPromptEvent) => {
      e.preventDefault()
      setA2hs(e)
    }

    if ('serviceWorker' in navigator) {
      navigator.serviceWorker
        .register('/sw.js')
        .then((reg) => console.log('service worker registered', reg))
        .catch((err) => console.log('service worker not registered', err))
    }

    window.addEventListener('beforeinstallprompt', handleBeforeInstallPrompt)
    return () => {
      window.removeEventListener('beforeinstallprompt', handleBeforeInstallPrompt)
    }
  }, [])

  const installApp = async () => {
    if (a2hs) {
      a2hs.prompt()
      a2hs.userChoice.then((choiceResult: { outcome: string }) => {
        if (choiceResult.outcome === 'accepted') {
          console.log('User accepted the A2HS prompt')
        } else {
          console.log('User dismissed the A2HS prompt')
        }
        setA2hs(undefined)
      })
    }
  }

  return (
    <div
      className={`${a2hs ? '' : 'hidden'} z-10 w-full h-full fixed left-0 bottom-0 flex items-end justify-center bg-black bg-opacity-70`}
    >
      <div className='w-[37.375rem] px-4 py-6 flex flex-col justify-center items-center rounded-t-3xl bg-bg-800 bottom-0 z-30 '>
        <div className='w-full flex flex-col justify-center items-center gap-8'>
          <div className='w-[17.75rem] h-[13.3125rem] flex flex-col items-center bottom-8 gap-4'>
            <SvgCastar3DIcon className='w-[6.25rem] h-[7.1875rem]' />
            <div>
              <p className='text-2xl text-center text-primary-light'>똑똑, 스타캐스트에요.</p>
              <p className='text-2xl text-center text-white'>홈 화면에 들어가도 될까요?</p>
              <p className='text-xs text-center py-1 text-white'>
                지금 홈 화면에 추가하지 않고 나중에 할 수도 있어요.
              </p>
            </div>
          </div>

          <div className='gap-2 px-5 flex flex-col justify-center items-center'>
            <button
              onClick={installApp}
              type='button'
              className='text-sm px-[8.0625rem] py-[.625rem] flex items-center justify-center bg-primary rounded-full font-bold text-bg-900'
            >
              당연하죠!
            </button>

            <button
              onClick={() => setA2hs(undefined)}
              type='button'
              className='text-xs px-5 py-2 flex items-center justify-center rounded-full text-bg-100 font-bold'
            >
              조금 더 친해진 뒤에 할게요
            </button>
          </div>
        </div>
      </div>
    </div>
  )
}

export default A2HSModal
