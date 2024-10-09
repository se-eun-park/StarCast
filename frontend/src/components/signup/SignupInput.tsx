import React, { useState } from 'react'

interface SignupInputProps {
  signupInput: string
  setSignupInput: (value: string) => void
}

const SignupInput: React.FC<SignupInputProps> = ({ signupInput, setSignupInput }) => {
  const [isFocused, setIsFocused] = useState(false)
  const [isValid, setIsValid] = useState(true) // 유효성 검사 상태 추가

  const handleChange = (event: React.ChangeEvent<HTMLInputElement>) => {
    const value = event.target.value
    setSignupInput(value)

    // 유효성 검사: 한글, 영문, 숫자 2~14자
    const regex = /^[a-zA-Z0-9가-힣]{2,14}$/
    setIsValid(regex.test(value))
  }

  const handleFocus = () => {
    setIsFocused(true)
  }

  const handleBlur = () => {
    setIsFocused(false)
  }

  return (
    <div className='mb-6'>
      <input
        type='text'
        value={signupInput}
        onChange={handleChange}
        onFocus={handleFocus}
        onBlur={handleBlur}
        placeholder='즐거운캐스타당근도둑'
        className={`w-full py-[1.0938rem] pl-4 text-md rounded-md bg-btn-tertiary-text bg-opacity-7
          ${isFocused ? 'border-primary-light' : isValid ? 'border-btn-tertiary-text border-opacity-7' : 'border-red-500'}`}
      />
      <p
        className={`text-[.75rem] mt-2 ml-4 ${signupInput ? (isValid ? 'text-text-tertiary' : 'text-red-500') : 'text-comp1-light'}`}
      >
        {signupInput
          ? isValid
            ? '한글, 영문, 숫자 2~14자로 입력해주세요.'
            : '닉네임 조건을 확인해주세요.'
          : '추천하는 랜덤 닉네임이예요!'}
      </p>
    </div>
  )
}

export default SignupInput
