import NavbarWithHome from '@components/navbar/NavbarWithHome'
import { PropsWithChildren } from 'react'

export default function ReviewDetailLayout({ children }: PropsWithChildren) {
  return (
    <div className='w-full h-full bg-bgGradient'>
      <div className='w-full min-h-dvh'>
        <div className='relative sm:w-[37.5rem] w-full mx-auto min-h-dvh'>
          <NavbarWithHome />
          {children}
        </div>
      </div>
    </div>
  )
}
