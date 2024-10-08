import LocationController from '@components/home/topbar-controller/LocationController'
import UserController from '@components/home/topbar-controller/UserController.tsx'

export default function TopbarController() {
  return (
    <div className='fixed top-0 z-10 flex justify-between w-full p-4 sm:w-[37.5rem] strong-blur'>
      <LocationController />
      <UserController />
    </div>
  )
}
