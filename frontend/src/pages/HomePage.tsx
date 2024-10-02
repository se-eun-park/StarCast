import A2HSModal from '@modal/a2hs/a2hsModal'
import SkyBackground from '@components/home/SkyBackground'

const HomePage = () => {
  return (
    <div className='relative w-full h-screen'>
      <SkyBackground />
      <A2HSModal />
    </div>
  )
}

export default HomePage
