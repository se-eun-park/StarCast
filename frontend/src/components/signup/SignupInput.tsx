import { useForm } from 'react-hook-form'
import { yupResolver } from '@hookform/resolvers/yup'
import * as yup from 'yup'
import { useNavigate } from 'react-router-dom'

type SubmitDataProps = {
  nickName: string
}

const schema = yup
  .object({
    nickName: yup
      .string()
      .required('\u200B')
      .min(2, '한글, 영문, 숫자 2~14자로 입력해주세요.')
      .max(14, '한글, 영문, 숫자 2~14자로 입력해주세요.')
      .matches(/^[A-Za-z0-9가-힣]+$/, '띄어쓰기 없이 온전한 한글, 영문, 숫자만 입력해주세요.'),
  })
  .required()

const SignupInput = () => {
  const navigate = useNavigate()
  const {
    register,
    handleSubmit,
    setValue, // useForm에서 setValue로 랜덤 닉네임 설정
    formState: { errors, isValid },
  } = useForm({
    mode: 'onChange',
    resolver: yupResolver(schema),
  })

  // 묶음1과 묶음2 정의
  const group1 = [
    '당근을좋아하는',
    '별을보는',
    '별을좋아하는',
    '상큼한',
    '귀여운',
    '사랑스러운',
    '낭만있는',
    '별먼지',
    '슈퍼스타',
    '태초의',
  ]
  const group2 = [
    '캐스타',
    '캐스타짱',
    '천문학자',
    '토끼',
    '달토끼',
    '셍셍',
    '워누',
    '곽곽',
    '쏨쏨코',
    '다다',
  ]

  // 랜덤 닉네임 생성 함수
  const generateRandomNickName = () => {
    const randomGroup1 = group1[Math.floor(Math.random() * group1.length)]
    const randomGroup2 = group2[Math.floor(Math.random() * group2.length)]
    return `${randomGroup1}${randomGroup2}`
  }

  const onRandomNickNameClick = () => {
    const randomNickName = generateRandomNickName()
    setValue('nickName', randomNickName)
  }

  const onSubmit = (data: SubmitDataProps) => {
    console.log(data)
    navigate('/home')
  }

  return (
    <div className=' w-full flex flex-col items-center px-4 py-6 h-[calc(100dvh-10rem)]'>
      <form onSubmit={handleSubmit(onSubmit)} className='flex flex-col w-full h-full'>
        <div className='flex flex-col w-full'>
          <div className='relative flex items-center w-full mt-6'>
            <input
              {...register('nickName')}
              maxLength={14}
              className={`w-full p-4 text-sm font-semibold rounded-lg bg-bg-50/7 caret-text-tertiary focus:outline focus:outline-1 ${errors.nickName ? 'focus:outline-comp2-light' : 'focus:outline-primary-light'}`}
            />
            <button
              type='button'
              onClick={onRandomNickNameClick}
              className='absolute text-xs font-semibold text-bg-50 right-4'
            >
              랜덤 생성
            </button>
          </div>
          {errors.nickName ? (
            <p className='mt-2 ml-4 text-xs text-comp2-light'>{errors.nickName.message}</p>
          ) : isValid ? (
            <p className='mt-2 ml-4 text-xs text-comp1-light'>사용가능한 멋진 닉네임이에요!</p>
          ) : (
            <p className='mt-2 ml-4 text-xs text-text-tertiary'>
              한글, 영문, 숫자 2~14자로 입력해주세요.
            </p>
          )}
        </div>

        <button
          type='submit'
          disabled={!isValid}
          className='mt-auto mb-6 btn-primary-full disabled:btn-disabled-full'
        >
          {isValid
            ? '회원가입 완료'
            : errors.nickName
              ? '한글, 영문, 숫자 2~14자로 입력해주세요.'
              : '이미 누군가 사용중인 닉네임입니다.'}
        </button>
      </form>
    </div>
  )
}

export default SignupInput
