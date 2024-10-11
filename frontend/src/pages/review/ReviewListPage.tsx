import { useEffect, useState } from 'react'
import { Link, useLocation, useNavigate } from 'react-router-dom'
import { activeTagMap } from '@dummy/dummyReview'
import { useReviewStore } from '@stores/useReviewStore'

export default function ReviewListPage() {
  const navigate = useNavigate()
  const location = useLocation()
  const [activeTab, setActiveTab] = useState('popular')
  const [activeTag, setActiveTag] = useState(1)
  const [lastScrollY, setLastScrollY] = useState(0)
  const [tagVisible, setTagVisible] = useState(true)

  const Reviews = useReviewStore((state) => state.reviews)

  useEffect(() => {
    const searchParams = new URLSearchParams(location.search)
    const tab = searchParams.get('tab') || 'popular'
    const tag = parseInt(searchParams.get('tag') || '1', 10)
    if (tab !== 'popular') {
      setActiveTab(tab)
    }
    if (tag !== 1) {
      setActiveTag(tag)
    }
  }, [location.search])

  useEffect(() => {
    navigate(`/review?tab=${activeTab}&tag=${activeTag}`, { replace: true })
  }, [activeTab, activeTag, navigate])

  useEffect(() => {
    if (activeTab === 'recent') return

    const handleScroll = () => {
      const currentScrollY = window.scrollY
      if (currentScrollY > lastScrollY) {
        setTagVisible(false)
      } else {
        setTagVisible(true)
      }
      setLastScrollY(currentScrollY)
    }

    window.addEventListener('scroll', handleScroll)

    return () => {
      window.removeEventListener('scroll', handleScroll)
    }
  }, [lastScrollY, activeTab])

  const onClickTab = (tab: string) => {
    setActiveTab(tab)
    if (tab === 'popular') {
      setTagVisible(true)
    } else {
      setTagVisible(false)
    }

    window.scrollTo(0, 0)
  }

  const onClickTag = (tag: number) => {
    setActiveTag(tag)
    window.scrollTo(0, 0)
  }

  const filteredReview = Reviews.filter((review) => review.tag === activeTagMap[activeTag].tag)

  return (
    <div className=' min-h-dvh bg-bg-900'>
      <div className={`sticky top-14 rounded-b-2xl`}>
        <div
          className={`relative z-20 flex justify-center w-full h-12 border-b border-bg-50/20  bg-bg-900`}
        >
          <button
            className={`w-1/4 text-center font-paperlogy text-sm ${activeTab === 'popular' ? 'border-b-2 border-bg-50' : 'text-text-tertiary'}`}
            onClick={() => {
              onClickTab('popular')
            }}
          >
            인기
          </button>
          <button
            className={`w-1/4 text-center font-paperlogy text-sm ${activeTab === 'recent' ? 'border-b-2 border-bg-50' : 'text-text-tertiary'}`}
            onClick={() => {
              onClickTab('recent')
            }}
          >
            최신
          </button>
        </div>
        {activeTab === 'popular' && (
          <div
            className={`relative z-0 flex items-center justify-center py-4 space-x-2 rounded-b-2xl transition-all duration-500  ${tagVisible && 'bg-gradient900to800'} ${tagVisible ? 'animate-fadeIn  translate-y-0' : '-translate-y-full'} `}
          >
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
        )}
      </div>
      {activeTab === 'popular' && (
        <div className='flex flex-col gap-4 p-6'>
          {filteredReview.map((review, idx) => (
            <Link to={`/review/${review.id}`}>
              <div key={review.id} className='w-full'>
                <img
                  src={review.mainImage}
                  className='w-full aspect-[5/2] object-cover object-center rounded-lg'
                />
                <div className='flex flex-col items-start gap-1 px-1 pt-2 pb-5'>
                  <div className={`text-xs font-semibold ${activeTagMap[activeTag].color}`}>
                    {`${activeTagMap[activeTag].label} #${idx + 1}`}
                  </div>
                  <div className='font-semibold line-clamp-2'>{review.title}</div>
                  <div className='flex justify-between w-full'>
                    <p className='text-xs'>{review.author}</p>
                    <p className='text-2xs text-text-tertiary'>{review.date}</p>
                  </div>
                </div>
              </div>
            </Link>
          ))}
        </div>
      )}
      {activeTab === 'recent' && (
        <button className='grid w-full grid-cols-2 gap-4 px-8 py-4'>
          {[...Reviews].reverse().map((review) => (
            <Link to={`/review/${review.id}`}>
              <div key={review.id} className='flex flex-col gap-1 rounded-lg shadow'>
                <img
                  src={review.mainImage}
                  alt=''
                  className='object-cover object-center w-full rounded-lg aspect-square'
                />
                <div className='flex flex-col items-start gap-1 p-1'>
                  <h2 className='text-sm font-semibold '>{review.title}</h2>
                  <h3 className='text-xs'>{review.author}</h3>
                  <p className='text-2xs text-text-tertiary'>{review.date}</p>
                </div>
              </div>
            </Link>
          ))}
        </button>
      )}
    </div>
  )
}
