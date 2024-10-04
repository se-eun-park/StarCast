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
        <LoginButton />
      </div>
    </>
  )
}

export default LoginPage
