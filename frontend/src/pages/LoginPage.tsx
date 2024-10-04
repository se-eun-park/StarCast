import LoginButton from '@components/login/LoginButton'
import LoginCarousel from '@components/login/Carousel'
import '@/components/login/CarouselCSS.css'

const LoginPage = () => {
  return (
    <>
      <div className='flex flex-col items-center justify-center'>
        <LoginCarousel
          SLIDES={['/carousel/carousel1.png', '/carousel/carousel2.png', '/carousel/carousel3.png']}
          OPTIONS={{ loop: false }}
        />
        {/* 로그인 버튼을 같은 div 안에 두어 도트 버튼 바로 아래에 위치하도록 설정 */}
        <LoginButton />
      </div>
    </>
  )
}

export default LoginPage
