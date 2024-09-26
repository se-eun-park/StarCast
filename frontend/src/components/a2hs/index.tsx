import { useEffect, useState } from 'react'

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
      className={`${a2hs ? '' : 'hidden'} z-[10] w-full h-[100dvh] fixed left-0 top-0 flex items-end justify-center bg-[#4C4C4C] bg-opacity-80`}
    >
      <div className='w-full max-w-[600px] px-7 py-4 flex flex-col justify-center items-center rounded-t-3xl bg-white bottom-0 z-30 max-h-[400px]'>
        <div className='w-full text-xl'>
          <p className='text-3xl py-8 text-center'>스타캐스트 바로가기를 추가하시겠어요?</p>

          <div className='gap-x-5 flex justify-between items-center'>
            <button
              onClick={() => setA2hs(undefined)}
              type='button'
              className='py-6 flex-1 flex items-center justify-center bg-[#E5E5ED] rounded-xl text-[#6B6E78]'
            >
              취소
            </button>

            <button
              onClick={installApp}
              type='button'
              className='py-6 flex-1 flex items-center justify-center bg-primary rounded-xl'
            >
              추가하기
            </button>
          </div>
        </div>
      </div>
    </div>
  )
}

export default A2HSModal
