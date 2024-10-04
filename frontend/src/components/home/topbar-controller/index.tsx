import LocationController from '@components/home/topbar-controller/LocationController'
import UserController from '@components/home/topbar-controller/UserController.tsx'

export default function TopbarController() {
  return (
    <div className='fixed top-0 flex justify-between p-4 w-[37.5rem] strong-blur'>
      <LocationController />
      <UserController />
    </div>
  )
}
