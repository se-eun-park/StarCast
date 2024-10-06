import { PropsWithChildren } from 'react'

const HomeContentsLayout = ({ children }: PropsWithChildren) => {
  return (
    <div className='relative flex flex-col items-center w-full h-full min-h-[calc(100vh-20rem)] bg-bg-900'>
      <div className='z-40'>{children}</div>
      <div className='absolute'>
        <div className='relative flex min-w-[27.9375rem] top-[-3.625rem] h-fit'>
          <div className='z-30 min-w-[17.125rem] h-[17.125rem] mt-[1.875rem] rounded-full bg-bg-900' />
          <div className='z-30 absolute min-w-[19.25rem] h-[19.25rem] right-0 rounded-full bg-bg-900' />
        </div>
      </div>
    </div>
  )
}

export default HomeContentsLayout
