import SvgAddIcon from '@assets/svg/AddIcon'
import ReviewEditCarousel from '@components/review/ReviewEditCarousel.tsx'
import { ChangeEvent, useRef, useState } from 'react'

export default function CreateReviewPage() {
  const [images, setImages] = useState<string[]>([])
  const fileInputRef = useRef<HTMLInputElement>(null)

  const SLIDES = [
    '/review/default-review-image1.jpg',
    '/review/default-review-image2.jpg',
    '/review/default-review-image3.jpg',
  ]

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

  return (
    <div>
      <div className='w-full py-2 aspect-video'>
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
          {/* <img
            src={imageUrl}
            alt='uploaded'
            className='object-cover object-center w-full h-full aspect-square'
          />
          <button
            onClick={handleClick}
            className='flex flex-col items-center justify-center h-full gap-1 mx-auto rounded-lg aspect-square bg-bg-50/10'
          >
            <SvgAddIcon className='w-6 h-6' />
            <div className='font-semibold text-center text-2xs'>
              <p className='text-text-secondary'>사진 추가하기</p>
              <p className='text-text-tertiary'>0 / 3</p>
            </div>
          </button> */}
        </div>
      </div>
    </div>
  )
}
