import Menubar from '@components/home/menubar/index.tsx'
import TopbarController from '@components/home/topbar-controller'
import A2HSModal from '@modal/a2hs/a2hsModal'
import SkyBackground from '@components/home/SkyBackground'
import HomeContentsLayout from '@components/layout/HomeContentsLayout'
import CastaScoreGauge from '@components/home/CastaScoreGauge'
import CastaScoreReaction from '@components/home/CastaScoreReaction'
import AstronomyInfoWidget from '@components/common/AstronomyInfoWidget'
import PlannedWidget from '@components/home/PlannedWidget'

const HomePage = () => {
  return (
    <div className='relative w-full h-full overflow-hidden overflow-y-auto scrollbar-hide'>
      <TopbarController />
      <SkyBackground />
      <div className='mb-20 mt-80'>
        <HomeContentsLayout>
          <div className='relative flex items-end justify-center mr-5 space-x-2 -top-4'>
            <CastaScoreGauge />
            <CastaScoreReaction status='좋음' />
          </div>
          <div className='w-full h-full px-12 py-4 bg-gradient900to800 rounded-3xl'>
            <AstronomyInfoWidget
              buttonBgColor='bg-bg-50/10'
              bgColor='bg-bg-50/5'
              textAlign='text-center'
              details='역삼동'
              hour='22'
              cloudCoverage={1}
              lightPollution={1}
              moonPhase={2}
              precipitation={0}
              moonSetTime='00:00'
            />
          </div>
          <div className='flex justify-center mt-3'>
            <PlannedWidget />
          </div>
        </HomeContentsLayout>
        <Menubar />
      </div>
      <A2HSModal />
    </div>
  )
}

export default HomePage
