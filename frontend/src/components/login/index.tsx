import React from 'react'
import { EmblaOptionsType } from 'embla-carousel'
import useEmblaCarousel from 'embla-carousel-react'
import ClassNames from 'embla-carousel-class-names'
import { NextButton, PrevButton, usePrevNextButtons } from './CarouselArrowButton'
import { DotButton, useDotButton } from './CarouselDotButton'

type PropType = {
  SLIDES: string[]
  OPTIONS?: EmblaOptionsType
}

const LoginCarousel: React.FC<PropType> = (props) => {
  const { SLIDES, OPTIONS } = props
  const [emblaRef, emblaApi] = useEmblaCarousel(OPTIONS, [ClassNames()])

  const { selectedIndex, scrollSnaps, onDotButtonClick } = useDotButton(emblaApi)

  const { prevBtnDisabled, nextBtnDisabled, onPrevButtonClick, onNextButtonClick } =
    usePrevNextButtons(emblaApi)

  return (
    <div className='embla mx-auto relative'>
      <div className='embla__viewport overflow-hidden relative' ref={emblaRef}>
        <div className='embla__container flex'>
          {SLIDES.map((src, index) => (
            <div className='embla__slide flex justify-center items-center' key={index}>
              <img className='embla__slide__img' src={src} alt={`Slide ${index + 1}`} />
            </div>
          ))}
        </div>
      </div>

      <div className='embla__controls inset-x-0 top-1/2 absolute transform -translate-y-1/2 flex justify-between z-10 max-w-[37.5rem]'>
        <PrevButton onClick={onPrevButtonClick} disabled={prevBtnDisabled} />
        <NextButton onClick={onNextButtonClick} disabled={nextBtnDisabled} />
      </div>

      <div className='embla__dots flex justify-center space-x-2 absolute bottom-20 left-1/2 transform -translate-x-1/2 my-4'>
        {scrollSnaps.map((_, index) => (
          <DotButton
            key={index}
            onClick={() => onDotButtonClick(index)}
            className={`embla__dot transition-transform duration-300 ease-in-out transform ${index === selectedIndex ? 'scale-x-125 bg-primary' : 'bg-[#B2D8FF]'}`}
          />
        ))}
      </div>
    </div>
  )
}

export default LoginCarousel
