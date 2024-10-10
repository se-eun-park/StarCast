// import React from 'react'
// import { useNavigate } from 'react-router-dom'

// const LoginButton: React.FC = () => {
//   const navigate = useNavigate()

//   const handleClick = () => {
//     navigate('/home')
//   }

//   return (
//     <div className='fixed w-[22.5rem] bottom-0 flex justify-center items-center'>
//       <button
//         onClick={handleClick}
//         className='bg-btn-primary-bg text-btn-primary-text w-full text-sm my-6 mw-4 text-center font-paperlogy font-semibold py-[.625rem] px-[3.125rem] rounded-full'
//       >
//         카카오 계정으로 시작하기
//       </button>
//     </div>
//   )
// }

// export default LoginButton

import React from 'react'

const LoginButton: React.FC = () => {
  const handleClick = () => {
    const clientId = import.meta.env.VITE_KAKAO_CLIENT_ID
    const redirectUri = import.meta.env.VITE_KAKAO_REDIRECT_URI_LOGIN
    const kakaoAuthUrl = `${import.meta.env.VITE_KAKAO_AUTH_URI}/oauth/authorize?client_id=${clientId}&redirect_uri=${encodeURIComponent(redirectUri)}&response_type=code`
    window.location.href = kakaoAuthUrl
  }

  return (
    <div className='fixed w-[22.5rem] bottom-0 flex justify-center items-center'>
      <button
        onClick={handleClick}
        className='bg-btn-primary-bg text-btn-primary-text w-full text-sm my-6 mw-4 text-center font-paperlogy font-semibold py-[.625rem] px-[3.125rem] rounded-full'
      >
        카카오 계정으로 시작하기
      </button>
    </div>
  )
}

export default LoginButton
