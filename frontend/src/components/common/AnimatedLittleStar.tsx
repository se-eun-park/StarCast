import type { Animated } from '../../types/components/common'

const AnimatedLittleStar = ({ x, y, delay }: Animated) => {
  return (
    <div
      className={`w-1 h-1 bg-bg-50/70 rounded-full absolute animate-pulse-delay ${x} ${y}`}
      style={{ '--delay': delay } as React.CSSProperties}
    ></div>
  )
}

export default AnimatedLittleStar
