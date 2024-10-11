import { EmblaOptionsType } from 'embla-carousel'
import useEmblaCarousel from 'embla-carousel-react'
import ClassNames from 'embla-carousel-class-names'
import { DotButton, useDotButton } from '../login/CarouselDotButton'

type PropType = {
  SLIDES: string[]
  OPTIONS?: EmblaOptionsType
}

const ReviewCarousel: React.FC<PropType> = (props) => {
  const { SLIDES, OPTIONS } = props
  const [emblaRef, emblaApi] = useEmblaCarousel(OPTIONS, [ClassNames()])

  const { selectedIndex, scrollSnaps, onDotButtonClick } = useDotButton(emblaApi)

  return (
    <div className='relative mx-auto embla'>
      <div className='relative overflow-hidden embla__viewport' ref={emblaRef}>
        <div className='flex embla__container'>
          {SLIDES.map((src, index) => (
            <div className='flex items-center justify-center embla__slide' key={index}>
              <img
                className='w-full sm:w-[37.5rem] bg-white aspect-square object-cover object-center max-h-96'
                src={src}
                alt={`Slide ${index + 1}`}
              />
            </div>
          ))}
        </div>
      </div>

      <div className='absolute flex justify-center space-x-2 transform -translate-x-1/2 bottom-5 left-1/2'>
        {scrollSnaps.map((_, index) => (
          <DotButton
            key={index}
            onClick={() => onDotButtonClick(index)}
            className={`embla__dot transition-transform duration-300 ease-in-out transform ${index === selectedIndex ? 'scale-x-125 bg-bg-50' : 'bg-bg-50/20'}`}
          />
        ))}
      </div>
    </div>
  )
}

export default ReviewCarousel
