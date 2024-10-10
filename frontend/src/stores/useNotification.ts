import { create } from 'zustand'

type NotificationStore = {
  isReadAll: boolean
  setIsReadAll: (value: boolean) => void
}

export const useNotificationStore = create<NotificationStore>((set) => {
  return {
    isReadAll: false,
    setIsReadAll: (isRead: boolean) => set({ isReadAll: isRead }),
  }
})
