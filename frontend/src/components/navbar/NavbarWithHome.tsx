import RouteBack from '@components/navbar/RouteBack'
import RouteHome from '@components/navbar/RouteHome'

export default function NavbarWithHome() {
  return (
    <div className='fixed top-0 z-10 flex items-center justify-between w-full px-5 sm:w-[37.5rem] h-14 bg-bg-transparent '>
      <RouteBack />
      <RouteHome />
    </div>
  )
}
