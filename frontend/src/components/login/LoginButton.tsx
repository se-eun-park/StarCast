import React from 'react'

const LoginButton: React.FC = () => {
  const handleClick = async () => {
    const clientId = import.meta.env.VITE_KAKAO_CLIENT_ID
    const redirectUri = import.meta.env.VITE_KAKAO_REDIRECT_URI_LOGIN

    try {
      // 백엔드에 로그인 요청을 보냄
      const response = await fetch('api/v1/auth/login', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify({
          clientId: clientId,
          redirectUri: redirectUri,
        }),
      })

      // 응답 처리
      if (response.ok) {
        const data = await response.json()
        const kakaoAuthUrl = data.data.url

        // 응답 URL로 새 창 열기
        window.location.href = kakaoAuthUrl
      } else {
        console.error('로그인 요청 실패:', response.status)
      }
    } catch (error) {
      console.error('오류 발생:', error)
    }
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
