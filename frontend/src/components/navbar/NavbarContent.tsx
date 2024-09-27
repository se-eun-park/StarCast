import { pathToNavContent } from '@constants/navigationContent'
import { useLocation, useNavigate } from 'react-router-dom'

export default function NavbarContent() {
  const { pathname } = useLocation()
  const navigate = useNavigate()
  const excatNavContent = pathToNavContent[pathname]

  return (
    <>
      <div className='text-white'>{excatNavContent?.label ?? ''}</div>
      {excatNavContent?.Component ? (
        <excatNavContent.Component
          className='w-6 h-6 cursor-pointer'
          onClick={() => navigate(excatNavContent.navigate ?? '')}
        />
      ) : (
        <div></div>
      )}
    </>
  )
}
