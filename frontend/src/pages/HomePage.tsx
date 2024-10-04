import Menubar from '@components/home/menubar/index.tsx'
import TopbarController from '@components/home/topbar-controller'
import A2HSModal from '@modal/a2hs/a2hsModal'

const HomePage = () => {
  return (
    <div className='relative pt-[4.625rem]'>
      <TopbarController />
      <h1>홈페이지</h1>
      <Menubar />

      <A2HSModal />
    </div>
  )
}

export default HomePage
