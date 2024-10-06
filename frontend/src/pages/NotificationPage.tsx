import SvgGtIcon from '@assets/svg/GtIcon'
import NoContent from '@components/common/NoContent'

const dummyNotifications = [
  { id: 1, message: '오늘 밤에 {예정된 천문 이벤트}를 볼 수 있어요!' },
  { id: 2, message: '2024.09.12에 진행한 관측은 어떠셨나요?\n사진과 후기를 남겨주세요!' },
  { id: 3, message: '익명의 스타캐스터가 내 후기에 반응했어요.\n도움이 됐어요 +1' },
  {
    id: 4,
    message:
      '별 보는 날 : 2024.10.04 일정에 대한 오늘의 점수 업데이트 입니다.별 보는 날 : 2024.10.04 일정에 대한 오늘의 점수 업데이트 입니다.별 보는 날 : 2024.10.04 일정에 대한 오늘의 점수 업데이트 입니다.별 보는 날 : 2024.10.04 일정에 대한 오늘의 점수 업데이트 입니다.별 보는 날 : 2024.10.04 일정에 대한 오늘의 점수 업데이트 입니다.별 보는 날 : 2024.10.04 일정에 대한 오늘의 점수 업데이트 입니다.별 보는 날 : 2024.10.04 일정에 대한 오늘의 점수 업데이트 입니다.',
  },
]

export default function NotificationPage() {
  return (
    <div className='w-full h-dvh bg-bg-900'>
      <div className='w-full px-8 pt-5'>
        {dummyNotifications.length === 0 ? (
          <NoContent label='모든 알림을 읽으셨어요!' />
        ) : (
          <div className='w-full border-t border-b divide-y border-bg-50/20 divide-bg-50/20'>
            {dummyNotifications.map((notification) => (
              <div
                key={notification.id}
                className='flex items-center justify-between w-full h-20 px-2 border-b cursor-pointer border-bg-800'
              >
                <p className='w-3/4 pr-4 text-sm whitespace-pre-line line-clamp-2'>
                  {notification.message}
                </p>
                <SvgGtIcon className='w-5 h-5' />
              </div>
            ))}
          </div>
        )}
      </div>
    </div>
  )
}
