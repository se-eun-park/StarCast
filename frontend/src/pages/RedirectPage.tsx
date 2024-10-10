// const RedirectPage = () => {
//   return <div></div>
// }

// export default RedirectPage

import React, { useEffect } from 'react'
import { useNavigate } from 'react-router-dom'

const RedirectPage: React.FC = () => {
  const navigate = useNavigate()

  // useEffect(() => {
  //   const params = new URLSearchParams(window.location.search)
  //   const code = params.get('code')

  //   if (code) {
  //     fetch('/api/v1/auth/redirect-login', {
  //       method: 'POST',
  //       headers: {
  //         'Content-Type': 'application/json',
  //       },
  //       body: JSON.stringify({ code }),
  //     })
  //       .then((response) => {
  //         if (!response.ok) {
  //           throw new Error('Failed to log in')
  //         }
  //         return response.json()
  //       })
  //       .then(() => {
  //         return fetch('/api/v1/auth/member-check', {
  //           method: 'GET',
  //           credentials: 'include',
  //         })
  //       })
  //       .then((response) => {
  //         if (!response.ok) {
  //           throw new Error('Failed to check member status')
  //         }
  //         return response.json()
  //       })
  //       .then((memberCheck) => {
  //         if (memberCheck.data.isSignedUp) {
  //           navigate('/home') // 신규회원이면 홈으로
  //         } else {
  //           navigate('/signup') // 기존회원이면 회원가입 페이지로
  //         }
  //       })
  //       .catch((error) => {
  //         console.error('Error:', error)
  //       })
  //   }
  // }, [navigate])

  useEffect(() => {
    navigate('/home')
  })

  return <div>로그인 중...</div> // 로딩 중 메시지
}

export default RedirectPage
