import { create } from 'zustand'
import { createJSONStorage, persist } from 'zustand/middleware'

type MyProfileProps = {
  myProfile: string
  setMyProfile: (profile: string) => void
}

const useMyProfile = create(
  persist<MyProfileProps>(
    (set) => ({
      myProfile: 'BRONZE_2',
      setMyProfile: (profile: string) =>
        set(() => ({
          myProfile: profile,
        })),
    }),
    { name: 'myProfile', storage: createJSONStorage(() => localStorage) },
  ),
)

export default useMyProfile
