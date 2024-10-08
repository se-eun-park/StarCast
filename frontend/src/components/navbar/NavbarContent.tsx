import { pathToNavContent } from '@constants/navigationContent'
import { useLocation, useNavigate } from 'react-router-dom'

export default function NavbarContent() {
  const { pathname } = useLocation()
  const navigate = useNavigate()
  const exactNavContent = pathToNavContent[pathname]

  return (
    <>
      <div className='text-white font-paperlogy'>{exactNavContent?.label ?? ''}</div>
      {exactNavContent?.Component ? (
        <exactNavContent.Component
          className='w-6 h-6 cursor-pointer'
          onClick={() => navigate(exactNavContent.navigate ?? '')}
        />
      ) : exactNavContent?.text ? (
        <div className='text-xs text-white cursor-pointer'>{exactNavContent.text}</div>
      ) : (
        <div className='w-9' />
      )}
    </>
  )
}
