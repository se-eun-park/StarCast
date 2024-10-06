import NavbarWithHome from '@components/navbar/NavbarWithHome'
import { PropsWithChildren } from 'react'

export default function ReviewDetailLayout({ children }: PropsWithChildren) {
  return (
    <div className='w-full h-full bg-gradient'>
      <div className='w-full min-h-dvh'>
        <div className='w-[37.5rem] mx-auto min-h-dvh border-l border-r border-white'>
          <NavbarWithHome />
          {children}
        </div>
      </div>
    </div>
  )
}
