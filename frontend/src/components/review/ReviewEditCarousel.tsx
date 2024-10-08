import React, { ChangeEvent, useRef } from 'react'
import { EmblaOptionsType } from 'embla-carousel'
import useEmblaCarousel from 'embla-carousel-react'
import SvgAddIcon from '@assets/svg/AddIcon'
import styles from './review.module.css'

type PropType = {
  slides: string[]
  options?: EmblaOptionsType
  fileUploader: (e: ChangeEvent<HTMLInputElement>) => void
}

const ReviewEditCarousel: React.FC<PropType> = (props) => {
  const { slides, options, fileUploader } = props
  const [emblaRef] = useEmblaCarousel(options)
  const fileInputRef = useRef<HTMLInputElement>(null)

  const handleClick = () => {
    if (slides.length === 3) {
      alert('이미지는 최대 3개까지 업로드 가능합니다.')
      return
    }

    fileInputRef.current?.click()
  }

  return (
    <section className='h-full embla' dir='rtl'>
      <input
        ref={fileInputRef}
        type='file'
        accept='image/*'
        className='hidden'
        onChange={fileUploader}
      />
      <div className='h-full embla__viewport' ref={emblaRef}>
        <div className='flex h-full space-x-3 embla__container'>
          <button
            onClick={handleClick}
            className='flex flex-col items-center justify-center h-full gap-1 mx-auto ml-3 rounded-lg aspect-square bg-bg-50/10'
          >
            <SvgAddIcon className='w-6 h-6' />
            <div className='font-semibold text-center text-2xs'>
              <p className='text-text-secondary'>사진 추가하기</p>
              <p className='text-text-tertiary'>3 / {slides.length}</p>
            </div>
          </button>
          {slides.map((src, index) => (
            <div className={`${styles.embla}`} key={index}>
              <img
                className='object-cover object-center h-full rounded-lg aspect-square'
                src={src}
                alt={`Slide ${index + 1}`}
              />
            </div>
          ))}
        </div>
      </div>
    </section>
  )
}

export default ReviewEditCarousel
