import type { Animated } from '../../types/components/common'

const AnimatedCloud = ({ width, x, y }: Animated) => {
  return (
    <div className={` absolute rounded-full h-11 effect-blur bg-white/7 ${width} ${x} ${y}`}></div>
  )
}

export default AnimatedCloud
