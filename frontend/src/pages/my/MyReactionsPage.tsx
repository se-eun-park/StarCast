import { useEffect, useState } from 'react'
import DefaultReviewImage1 from '@assets/image/default-review-image1.jpg'
import DefaultReviewImage2 from '@assets/image/default-review-image2.jpg'
import DefaultReviewImage3 from '@assets/image/default-review-image3.jpg'
import { useLocation, useNavigate } from 'react-router-dom'

const reviewList = [
  {
    id: 1,
    image: DefaultReviewImage1,
    title: '캐스타와 함께한 은하수 여행',
    author: '캐스타들고중량스쿼트',
    date: '2024.09.29',
  },
  {
    id: 2,
    image: DefaultReviewImage2,
    title: '캐스타와 함께한 은하수 여행캐스타와 함께한 은하수 여행캐스타와 함께한 은하수 여행',
    author: '캐스타들고중량스쿼트',
    date: '2024.09.29',
  },
  {
    id: 3,
    image: DefaultReviewImage3,
    title: '캐스타와 함께한 은하수 여행',
    author: '캐스타들고중량스쿼트',
    date: '2024.09.29',
  },
]

const MyReactionsPage = () => {
  const navigate = useNavigate()
  const location = useLocation()
  const [activeTag, setActiveTag] = useState(1)

  useEffect(() => {
    const searchParams = new URLSearchParams(location.search)
    const tag = parseInt(searchParams.get('tag') || '1', 10)

    if (tag !== 1) {
      setActiveTag(tag)
    }
  }, [location.search])

  useEffect(() => {
    navigate(`/mypage/reaction?tag=${activeTag}`, { replace: true })
  }, [activeTag, navigate])

  const onClickTag = (tag: number) => {
    setActiveTag(tag)
    window.scrollTo(0, 0)
  }

  return (
    <div className='h-[calc(100dvh-56px)] w-full bg-bg-900 flex flex-col items-center'>
      <div className='flex flex-col w-full px-6 py-4 border-b border-bg-50/30'>
        <h1 className='mb-1 text-xl font-medium font-paperlogy'>내가 남긴 반응</h1>
        <h2 className='font-medium font-paperlogy text-text-secondary'>
          내가 반응을 남긴 후기들이에요
        </h2>

        <div className='flex items-center justify-center pt-4 space-x-2 rounded-b-2xl'>
          <button
            className={`px-3 py-1 text-xs border rounded-full border-bg-50/50 ${activeTag === 1 ? 'text-bg-900 bg-bg-50' : 'text-bg-50'} `}
            onClick={() => onClickTag(1)}
          >
            사진이 예뻐요
          </button>
          <button
            className={`px-3 py-1 text-xs border rounded-full border-bg-50/50 ${activeTag === 2 ? 'text-bg-900 bg-bg-50' : 'text-bg-50'} `}
            onClick={() => onClickTag(2)}
          >
            도움이 됐어요
          </button>
          <button
            className={`px-3 py-1 text-xs border rounded-full border-bg-50/50 ${activeTag === 3 ? 'text-bg-900 bg-bg-50' : 'text-bg-50'} `}
            onClick={() => onClickTag(3)}
          >
            가보고 싶어요
          </button>
        </div>
      </div>

      <div className='flex flex-col gap-4 px-6 py-4 overflow-y-auto scrollbar-hide'>
        {reviewList.map((review) => (
          <div key={review.id} className='w-full'>
            <img
              src={review.image}
              className='w-full aspect-[5/2] object-cover object-center rounded-lg'
            />
            <div className='flex flex-col items-start gap-1 px-1 pt-2 pb-5'>
              <div className='font-semibold line-clamp-2'>{review.title}</div>
              <div className='flex justify-between w-full'>
                <p className='text-xs'>{review.author}</p>
                <p className='text-2xs text-text-tertiary'>{review.date}</p>
              </div>
            </div>
          </div>
        ))}
      </div>
    </div>
  )
}

export default MyReactionsPage
