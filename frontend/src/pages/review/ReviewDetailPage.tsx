import DefaultIamge2 from '@assets/image/default-review-image2.jpg'
import SvgDefaultControllerProfileIcon from '@assets/svg/DefaultControllerProfileIcon.tsx'
import SvgLocationIcon from '@assets/svg/calendar/LocationIcon'
import SvgCastarCuteIcon from '@assets/svg/response/CastarCuteIcon'
import SvgCastarHappyIcon from '@assets/svg/response/CastarHappyIcon'
import SvgCastarSmartIcon from '@assets/svg/response/CastarSmartIcon'
import { useState } from 'react'

const content = `
어젯밤, 드디어 은하수를 카메라에 담을 수 있었어요. 

사실 맨눈으로 봤을 때도 이렇게 선명하게 보이진 않았는데, 사진으로 찍으니 더욱 아름답게 드러나더군요. 
별빛이 반짝이는 광경을 보면서 우주의 광활함을 느낄 수 있었습니다. 이 순간을 어떻게 글로 다 표현할 수 있을까요? 

은하수는 끝없는 시간과 공간의 흐름을 담고 있는 것 같았어요. 한참을 바라보고 있으니 마음이 차분해지고 일상의 걱정들이 사라지는 기분이 들더라구요. 

사진 한 장에 담긴 이 우주의 신비가 여러분에게도 전해지길 바랍니다. 여러분도 언젠가 밤하늘을 올려다보며 이 은하수의 아름다움을 직접 느껴보셨으면 좋겠어요.
사진 한 장에 담긴 이 우주의 신비가 여러분에게도 전해지길 바랍니다. 여러분도 언젠가 밤하늘을 올려다보며 이 은하수의 아름다움을 직접 느껴보셨으면 좋겠어요.
사진 한 장에 담긴 이 우주의 신비가 여러분에게도 전해지길 바랍니다. 여러분도 언젠가 밤하늘을 올려다보며 이 은하수의 아름다움을 느껴보세요.
`

export default function ReviewDetailPage() {
  const [activeButton, setActiveButton] = useState<number | null>(null)
  const onClickButton = (num: number) => {
    if (activeButton !== num) {
      setActiveButton(num)
    } else {
      setActiveButton(null)
    }
  }

  return (
    <div className='w-full divide-y divide-bg-50/10 '>
      <img
        src={DefaultIamge2}
        className='w-[37.5rem] bg-white aspect-square object-cover object-center max-h-96'
      />
      <div className='relative z-50 flex justify-between w-full p-4 bg-bg-900'>
        <div className='flex items-center space-x-2'>
          <SvgDefaultControllerProfileIcon className='rounded-full w-9 h-9' />
          <p className='text-xs font-semibold'>캐스타한테당근줘야지</p>
        </div>
        <button className='flex items-center py-2 pl-3 pr-1 space-x-1 rounded-full bg-bg-50/10'>
          <p className='text-xs '>경기도 연천군 연천읍</p>
          <SvgLocationIcon className='w-4 h-4' />
        </button>
      </div>
      <div className='relative z-50 p-4 pb-6 bg-bg-900'>
        <h1 className='text-lg font-semibold'>제가 찍은 은하수 어때요 평가좀요 보정은 좀 했어요</h1>
        <p className='text-sm whitespace-pre-line'>{content}</p>
        <div className='flex justify-around px-4 mt-6 border-t border-b border-bg-50/10'>
          <button
            className='flex flex-col items-center gap-2 pt-2 pb-1 border-b-2 border-comp3-light'
            onClick={() => onClickButton(1)}
          >
            <SvgCastarCuteIcon className={`w-10 h-10 ${activeButton !== 1 && 'opacity-50'}`} />
            <div className='text-center text-2xs'>
              <p className={`font-paperlogy ${activeButton !== 1 && 'text-text-tertiary'}`}>
                사진이 예뻐요
              </p>
              <p className='text-comp3-light'>999</p>
            </div>
          </button>
          <button
            className='flex flex-col items-center gap-2 pt-2 pb-1 border-b-2 border-comp1-light'
            onClick={() => onClickButton(2)}
          >
            <SvgCastarSmartIcon className={`w-10 h-10 ${activeButton !== 2 && 'opacity-50'}`} />
            <div className='text-center text-2xs'>
              <p className={`font-paperlogy ${activeButton !== 2 && 'text-text-tertiary'}`}>
                사진이 예뻐요
              </p>
              <p className='text-comp1-light'>999</p>
            </div>
          </button>
          <button
            className='flex flex-col items-center gap-2 pt-2 pb-1 border-b-2 border-comp2-light'
            onClick={() => onClickButton(3)}
          >
            <SvgCastarHappyIcon className={`w-10 h-10 ${activeButton !== 3 && 'opacity-50'}`} />
            <div className='text-center text-2xs'>
              <p className={`font-paperlogy ${activeButton !== 3 && 'text-text-tertiary'}`}>
                사진이 예뻐요
              </p>
              <p className='text-comp2-light'>999</p>
            </div>
          </button>
        </div>
      </div>
    </div>
  )
}
