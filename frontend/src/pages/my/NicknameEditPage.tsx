import { useForm } from 'react-hook-form'
import { yupResolver } from '@hookform/resolvers/yup'
import * as yup from 'yup'
import { useUsernameStore } from '@stores/useUsername.ts'
import { useNavigate } from 'react-router-dom'
import { useReviewStore } from '@stores/useReviewStore.ts'

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

const NicknameEditPage = () => {
  const navigate = useNavigate()
  const changeReviewAuthor = useReviewStore((state) => state.changeReviewAuthor)
  const nickname = useUsernameStore((state) => state.nickname)
  const setNickname = useUsernameStore((state) => state.setNickname)
  const {
    register,
    handleSubmit,
    formState: { errors, isValid },
  } = useForm({
    mode: 'onChange',
    resolver: yupResolver(schema),
  })

  const onSubmit = (data: SubmitDataProps) => {
    changeReviewAuthor(nickname, data.nickName)
    setNickname(data.nickName)

    navigate('/mypage')
  }

  return (
    <div className='h-[calc(100dvh-56px)] w-full bg-bg-900 flex flex-col items-center px-4 py-6'>
      <div className='flex flex-col w-full'>
        <h1 className='mb-1 text-xl font-medium font-paperlogy'>닉네임 변경하기</h1>
        <h2 className='font-medium font-paperlogy text-text-secondary'>
          새로운 닉네임을 정해주세요!
        </h2>
      </div>

      <form onSubmit={handleSubmit(onSubmit)} className='flex flex-col w-full h-full'>
        <div className='flex flex-col w-full'>
          <div className='relative flex items-center w-full mt-6'>
            <input
              {...register('nickName')}
              maxLength={14}
              className={`w-full p-4 text-sm font-semibold rounded-lg bg-bg-50/7 caret-text-tertiary focus:outline focus:outline-1 ${errors.nickName ? 'focus:outline-comp2-light' : 'focus:outline-primary-light'}`}
            />
            <button className='absolute text-xs font-semibold text-bg-50 right-4'>랜덤 생성</button>
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
          className='mt-auto btn-primary-full disabled:btn-disabled-full'
        >
          {isValid
            ? '닉네임 변경완료'
            : errors.nickName
              ? '닉네임 조건을 확인해주세요'
              : '기존 닉네임과 다르게 입력해주세요'}
        </button>
      </form>
    </div>
  )
}

export default NicknameEditPage
