import { Outlet } from 'react-router-dom'
import Navbar from '@components/navbar'

export default function Layout() {
  return (
    <div className='w-full h-dvh bg-gradient'>
      <div className='w-[37.5rem] mx-auto h-full border-l border-r border-white'>
        <Navbar />
        <Outlet />
      </div>
    </div>
  )
}
