import SvgDeleteIcon from '@assets/svg/DeleteIcon'
import SvgSearchIcon from '@assets/svg/SearchIcon'
import ReviewEditCarousel from '@components/review/ReviewEditCarousel'
import { ChangeEvent, useRef, useState } from 'react'

export default function CreateReviewPage() {
  const [images, setImages] = useState<string[]>([])
  const [location, setLocation] = useState<string>('')
  const [title, setTitle] = useState<string>('')
  const [content, setContent] = useState<string>('')
  const fileInputRef = useRef<HTMLInputElement>(null)

  const handleFileUpload = (e: ChangeEvent<HTMLInputElement>) => {
    const {
      target: { files },
    } = e

    if (files?.length === 0) {
      return
    }

    const file = files?.[0]
    const fileReader = new FileReader()
    fileReader.readAsDataURL(file as Blob)
    fileReader.onloadend = (finishedEvent) => {
      const {
        currentTarget: { result },
        // eslint-disable-next-line @typescript-eslint/no-explicit-any
      }: any = finishedEvent
      setImages([result, ...images])
    }
  }

  const isSubmitButtonEnabled = location && title && content && images.length > 0

  return (
    <div>
      <div className='w-full py-2 pr-4 border-t border-b aspect-video border-bg-50/20'>
        <input
          ref={fileInputRef}
          type='file'
          accept='image/*'
          className='hidden'
          onChange={handleFileUpload}
        />
        <div className='flex justify-end h-full space-x-3 overflow-hidden'>
          <ReviewEditCarousel
            slides={images}
            options={{ dragFree: true, direction: 'rtl' }}
            fileUploader={handleFileUpload}
          />
        </div>
      </div>
      <div className='flex flex-col gap-4 p-4 text-sm'>
        <div className='flex flex-col gap-2'>
          <h2 className='font-semibold '>어디서 관측한 사진인가요?</h2>
          <div className='flex justify-between w-full p-4 border rounded-lg border-bg-50/10 bg-bg-50/10 focus-within:border-primary-light'>
            <input
              id='location'
              type='text'
              value={location}
              onChange={(e) => setLocation(e.target.value)}
              placeholder='주소 검색하기'
              className='w-full border-none outline-none text-primary-light placeholder:text-text-secondary bg-bg-transparent '
            />
            {location ? (
              <SvgDeleteIcon className='w-5 h-5 cursor-pointer' onClick={() => setLocation('')} />
            ) : (
              <label htmlFor='location'>
                <SvgSearchIcon className='w-5 h-5 cursor-pointer' />
              </label>
            )}
          </div>
        </div>
        <div className='flex flex-col gap-2'>
          <div className='p-4 border rounded-lg border-bg-50/20 focus-within:border-primary-light'>
            <input
              type='text'
              value={title}
              onChange={(e) => setTitle(e.target.value)}
              placeholder='제목'
              className='w-full outline-none placeholder:text-text-secondary bg-bg-transparent'
            />
          </div>
          <div className='h-64 p-4 border rounded-lg border-bg-50/20 focus-within:border-primary-light'>
            <textarea
              placeholder='내용'
              value={content}
              onChange={(e) => setContent(e.target.value)}
              className='w-full h-full outline-none resize-none placeholder:text-text-secondary bg-bg-transparent scrollbar-hide'
            />
          </div>
        </div>
      </div>
      <div className='p-4 pt-0'>
        <button
          disabled={!isSubmitButtonEnabled}
          className={isSubmitButtonEnabled ? 'btn-primary-full' : 'btn-disabled-full'}
        >
          작성 완료
        </button>
      </div>
    </div>
  )
}
