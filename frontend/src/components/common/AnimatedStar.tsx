import type { Animated } from '../../types/components/common'
import { StarIcon } from '@assets/svg'

const AnimatedStar = ({ width, x, y }: Animated) => {
  return (
    <>
      <StarIcon className={`absolute ${width} ${x} ${y}`} />
    </>
  )
}

export default AnimatedStar
