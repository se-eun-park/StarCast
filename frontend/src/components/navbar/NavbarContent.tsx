import { pathToNavContent } from '@constants/navigationContent'
import { useNotificationStore } from '@stores/useNotification.ts'
import { useLocation, useNavigate } from 'react-router-dom'

export default function NavbarContent() {
  const setIsReadAll = useNotificationStore((state) => state.setIsReadAll)
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
        <div className='text-xs text-white cursor-pointer' onClick={() => setIsReadAll(true)}>
          {exactNavContent.text}
        </div>
      ) : (
        <div className='w-9' />
      )}
    </>
  )
}
