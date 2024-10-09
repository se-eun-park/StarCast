import React from 'react'
import { useNavigate } from 'react-router-dom'

interface SignupButtonProps {
  isValid: boolean // 유효성 검사 결과를 props로 받기
}

const SignupButton: React.FC<SignupButtonProps> = ({ isValid }) => {
  const navigate = useNavigate()

  const handleClick = () => {
    if (isValid) {
      navigate('/home')
    }
  }

  return (
    <div className='fixed w-[22.5rem] bottom-0 flex justify-center items-center'>
      <button
        onClick={handleClick}
        disabled={!isValid} // 유효하지 않을 경우 비활성화
        className={`bg-btn-primary-bg text-btn-primary-text w-full text-sm my-6 mw-4 text-center font-paperlogy font-semibold py-[.625rem] px-[3.125rem] rounded-full 
          ${!isValid ? 'opacity-50 cursor-not-allowed' : ''}`} // 비활성화 시 스타일 변경
      >
        {!isValid ? '닉네임 조건을 확인해주세요' : '회원가입 완료'}
      </button>
    </div>
  )
}

export default SignupButton
