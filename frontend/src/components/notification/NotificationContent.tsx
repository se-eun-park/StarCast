import SvgGtIcon from '@assets/svg/GtIcon'

type NotificationContentProps = {
  id: number
  message: string
}

export default function NotificationContent({ id, message }: NotificationContentProps) {
  return (
    <div
      key={id}
      className='flex items-center justify-between w-full h-20 px-2 border-b cursor-pointer border-bg-800'
    >
      <p className='w-3/4 pr-4 text-sm whitespace-pre-line line-clamp-2'>{message}</p>
      <SvgGtIcon className='w-5 h-5' />
    </div>
  )
}
