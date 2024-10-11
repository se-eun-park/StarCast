import { create } from 'zustand'
import { createJSONStorage, persist } from 'zustand/middleware'

type LocationControllerStateProps = {
  locationState: string
  setLocationState: (locState: string) => void
  nowLocation: string
  setNowLocation: (nowLoc: string) => void
}

const useLocationControllerState = create(
  persist<LocationControllerStateProps>(
    (set) => ({
      locationState: 'GPS',
      nowLocation: '역삼동',
      setLocationState: (locState: string) =>
        set(() => ({
          locationState: locState,
        })),

      setNowLocation: (nowLoc: string) =>
        set(() => ({
          nowLocation: nowLoc,
        })),
    }),
    { name: 'locationState', storage: createJSONStorage(() => sessionStorage) },
  ),
)

export default useLocationControllerState
