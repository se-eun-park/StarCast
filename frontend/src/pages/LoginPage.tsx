import LoginButton from '@components/login/LoginButton'

const LoginPage = () => {
  return (
    <div className='flex flex-col items-center justify-center h-screen'>
      <p className='text-2xl text-center'>
        가장 쉬운 별빛 여행 <br /> 스타캐스트
      </p>
      <LoginButton />
    </div>
  )
}

export default LoginPage
