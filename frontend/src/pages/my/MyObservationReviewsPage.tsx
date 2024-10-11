import NoContent from '@components/common/NoContent'
import { useReviewStore } from '@stores/useReviewStore'
import { useUsernameStore } from '@stores/useUsername.ts'
import { useNavigate } from 'react-router-dom'

const MyObservationReviewsPage = () => {
  const navigate = useNavigate()
  const reviews = useReviewStore((state) => state.reviews)
  const nickname = useUsernameStore((state) => state.nickname)
  const myReviews = reviews.filter((review) => review.author === nickname)

  return (
    <div className='h-[calc(100dvh-56px)] w-full bg-bg-900 flex flex-col items-center'>
      <div className='flex flex-col w-full px-6 py-4 border-b border-bg-50/30'>
        <h1 className='mb-1 text-xl font-medium font-paperlogy'>나의 관측 후기</h1>
        <h2 className='font-medium font-paperlogy text-text-secondary'>
          내가 작성한 관측 후기들이에요
        </h2>
      </div>

      <div className='flex flex-col gap-4 px-6 py-4 overflow-y-auto scrollbar-hide'>
        {myReviews.map((review) => (
          <div
            key={review.id}
            className='w-full cursor-pointer'
            onClick={() => navigate('/review/' + review.id)}
          >
            <img
              src={review.mainImage}
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
        {myReviews.length === 0 && <NoContent label='아직 작성한 관측 후기가 없어요!' />}
      </div>
    </div>
  )
}

export default MyObservationReviewsPage
