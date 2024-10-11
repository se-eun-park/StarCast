/* eslint-disable react-hooks/exhaustive-deps */
import { useEffect, useMemo } from 'react'
import useTyping from '@hooks/useTyping'
import { HappyCastarIcon, NormalCastarIcon, SadCastarIcon } from '@assets/svg/castar'

type CastaScoreReactionProps = {
  status: '좋음' | '보통' | '나쁨'
}

const CastaScoreReaction = ({ status }: CastaScoreReactionProps) => {
  const statusElements = useMemo(() => {
    switch (status) {
      case '좋음':
        return { icon: HappyCastarIcon, text: '별자리 모양이 보여!' }
      case '보통':
        return { icon: NormalCastarIcon, text: '밝은 별은 볼 수 있어!' }
      case '나쁨':
        return { icon: SadCastarIcon, text: '아무 것도 안보여...' }
    }
  }, [status])

  const { text, reset } = useTyping(statusElements.text)

  useEffect(() => {
    reset()
  }, [status])

  return (
    <div className='flex flex-col items-end w-36'>
      <p
        className={`text-xs font-normal border font-paperlogy bg-bg-50/15 border-bg-50/70 py-1 px-2.5 rounded-3xl rounded-br-md mb-1`}
      >
        {text}
      </p>
      <statusElements.icon className='w-[6.6875rem]' />
    </div>
  )
}

export default CastaScoreReaction
