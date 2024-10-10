import AnimatedCloud from '@components/common/AnimatedCloud'
import AnimatedStar from '@components/common/AnimatedStar'
import AnimatedLittleStar from '@components/common/AnimatedLittleStar'
import {
  cloudPositionList,
  StarPositionList,
  LittleStarPositionList,
} from '@constants/animatedPositions'

type SkyBackgroundProps = {
  status: string
}

const SkyBackground = ({ status }: SkyBackgroundProps) => {
  return (
    <div className='fixed w-[37.5rem] overflow-hidden h-80 bg-gradient900to700'>
      <div className='absolute z-0'>
        {status === '좋음' &&
          LittleStarPositionList.map((littleStar, index) => (
            <AnimatedLittleStar
              key={index}
              x={littleStar.x}
              y={littleStar.y}
              delay={littleStar.delay}
            />
          ))}
      </div>

      <div className='absolute z-10'>
        {(status === '좋음' || status === '보통') &&
          StarPositionList.map((star, index) => (
            <AnimatedStar key={index} width={star.width} x={star.x} y={star.y} />
          ))}
      </div>

      <div className='absolute z-20 w-full h-full animate-slideRight'>
        {cloudPositionList.map((cloud, index) => (
          <AnimatedCloud key={index} width={cloud.width} x={cloud.x} y={cloud.y} />
        ))}
      </div>
    </div>
  )
}

export default SkyBackground
