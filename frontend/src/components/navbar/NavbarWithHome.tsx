import RouteBack from '@components/navbar/RouteBack'
import RouteHome from '@components/navbar/RouteHome'

export default function NavbarWithHome() {
  return (
    <div className='fixed w-[37.25rem] h-14'>
      <div className='flex items-center justify-between w-full h-full px-5 bg-bg-transparent '>
        <RouteBack />
        <RouteHome />
      </div>
    </div>
  )
}
