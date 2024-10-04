import A2HSModal from '@modal/a2hs/a2hsModal'
import SkyBackground from '@components/home/SkyBackground'
import HomeContentsLayout from '@components/layout/HomeContentsLayout'

const HomePage = () => {
  return (
    <div className='relative w-full h-full overflow-hidden overflow-y-auto scrollbar-hide'>
      <SkyBackground />
      <div className='mt-80'>
        <HomeContentsLayout>
          <div className='w-20 h-[600px] bg-btn-tertiary-text'>하이? ㅌㅋㅋㅋㅌㅋ</div>
        </HomeContentsLayout>
      </div>
      <A2HSModal />
    </div>
  )
}

export default HomePage
