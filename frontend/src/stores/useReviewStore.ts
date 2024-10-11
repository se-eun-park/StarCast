import { Reviews } from '@dummy/dummyReview'
import { create } from 'zustand'

type Review = {
  id: number
  mainImage: string
  images: string[]
  title: string
  tag: string
  author: string
  content: string
  addressSummary: string
  date: string
  reactionCount: {
    visitAgain: number
    helpful: number
    nicePhotos: number
  }
}

type RequestType = {
  mainImage: string
  images: string[]
  title: string
  tag?: string
  author: string
  content: string
  addressSummary: string
  date?: string
  reactionCount?: {
    visitAgain?: number
    helpful?: number
    nicePhotos?: number
  }
}

type ReviewStore = {
  reviews: Review[] // reviews는 배열이어야 합니다.
  createReview: (req: RequestType) => void
  changeReviewAuthor: (author: string, nickName: string) => void
}

export const useReviewStore = create<ReviewStore>((set) => ({
  reviews: Reviews, // 초기 상태 설정
  createReview: (req) => {
    set((state) => ({
      ...state,
      reviews: [
        ...state.reviews,
        {
          id: state.reviews.length + 1,
          mainImage: req.mainImage,
          images: req.images,
          title: req.title,
          tag: req.tag || 'Default Tag',
          author: req.author,
          content: req.content,
          addressSummary: req.addressSummary,
          date: req.date || new Date().toISOString().split('T')[0],
          reactionCount: {
            visitAgain: req.reactionCount?.visitAgain || 0,
            helpful: req.reactionCount?.helpful || 0,
            nicePhotos: req.reactionCount?.nicePhotos || 0,
          },
        },
      ],
    }))
  },
  changeReviewAuthor: (author: string, nickName: string) =>
    set((state) => ({
      reviews: state.reviews.map((review) =>
        review.author === author ? { ...review, author: nickName } : review,
      ),
    })),
}))
