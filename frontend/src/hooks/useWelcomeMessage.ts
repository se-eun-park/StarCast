import { useWelcomeMessageStore } from '@stores/useWelcomeMessageStore'
import { useEffect } from 'react'

export function useWelcomeMessage() {
  const isWelcomeMessageShown = useWelcomeMessageStore((state) => state.isWelcomeMessageShown)
  const setIsWelcomeMessageShown = useWelcomeMessageStore((state) => state.setIsWelcomeMessageShown)

  useEffect(() => {
    if (isWelcomeMessageShown) return

    const timer = setTimeout(() => {
      setIsWelcomeMessageShown(true)
    }, 4000)

    return () => clearTimeout(timer)
  }, [isWelcomeMessageShown, setIsWelcomeMessageShown])

  return isWelcomeMessageShown
}
