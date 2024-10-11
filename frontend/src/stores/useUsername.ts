import { create } from 'zustand'

type UsernameStore = {
  nickname: string
  setNickname: (value: string) => void
}

export const useUsernameStore = create<UsernameStore>((set) => {
  return {
    nickname: '즐거운캐스타당근도둑',
    setNickname: (nickname: string) => set({ nickname: nickname }),
  }
})
