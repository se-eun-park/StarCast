import A2HSModal from '@modal/a2hs/a2hsModal'
import SkyBackground from '@components/home/SkyBackground'
import HomeContentsLayout from '@components/layout/HomeContentsLayout'

const HomePage = () => {
  return (
    <div className='relative w-full h-screen'>
      <SkyBackground />
      <HomeContentsLayout>
        <div>하이? ㅌㅋㅋㅋㅌㅋ</div>
      </HomeContentsLayout>
      <A2HSModal />
    </div>
  )
}

export default HomePage
