import Menubar from '@components/home/menubar/index.tsx'
import TopbarController from '@components/home/topbar-controller'
import A2HSModal from '@modal/a2hs/a2hsModal'
import SkyBackground from '@components/home/SkyBackground'

const HomePage = () => {
  return (
    <div className='relative pt-[4.625rem] w-full h-screen'>
      <TopbarController />
      <SkyBackground />
      <Menubar />
      <A2HSModal />
    </div>
  )
}

export default HomePage
