import NoContent from '@components/common/NoContent'
import NotificationContent from '@components/notification/NotificationContent'
import { dummyNotifications } from '@dummy/dummyNotifications'
import { useNotificationStore } from '@stores/useNotification.ts'

export default function NotificationPage() {
  const isReadAll = useNotificationStore((state) => state.isReadAll)

  return (
    <div className='w-full h-dvh bg-bg-900'>
      <div className='w-full px-8 pt-5'>
        {dummyNotifications.length === 0 || isReadAll ? (
          <NoContent label='모든 알림을 읽으셨어요!' />
        ) : (
          <div className='w-full border-t border-b divide-y border-bg-50/20 divide-bg-50/20'>
            {dummyNotifications.map((notification) => (
              <NotificationContent
                key={notification.id}
                id={notification.id}
                message={notification.message}
              />
            ))}
          </div>
        )}
      </div>
    </div>
  )
}
