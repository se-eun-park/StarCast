/* eslint-disable @typescript-eslint/no-explicit-any */
import { create } from 'zustand'

type TierStore = {
  Tier: string
  TierLevel: 0 | 1 | 2 | 3
  setNicknameTier: (reviews: any, nickname: string) => void
  setTierLevel: (Tier: string) => void
}

export const useTierStore = create<TierStore>((set) => ({
  Tier: 'BRONZE',
  TierLevel: 0,
  setNicknameTier: (reviews: any, nickname: string) => {
    const reviewCount = reviews.filter(
      (review: { author: string }) => review.author === nickname,
    ).length

    const tier =
      reviewCount === 0
        ? 'BRONZE'
        : reviewCount === 1
          ? 'SILVER'
          : reviewCount === 2
            ? 'GOLD'
            : 'PLATINUM'

    set({ Tier: tier })
  },
  setTierLevel: (Tier: string) => {
    const tierLevel = Tier === 'BRONZE' ? 0 : Tier === 'SILVER' ? 1 : Tier === 'GOLD' ? 2 : 3

    set({ TierLevel: tierLevel })
  },
}))
