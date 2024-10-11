import { create } from 'zustand'

type WelcomeMessageStore = {
  isWelcomeMessageShown: boolean
  setIsWelcomeMessageShown: (value: boolean) => void
}

export const useWelcomeMessageStore = create<WelcomeMessageStore>((set) => {
  return {
    isWelcomeMessageShown: false,
    setIsWelcomeMessageShown: (value: boolean) => set(() => ({ isWelcomeMessageShown: value })),
  }
})
