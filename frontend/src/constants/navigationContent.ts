import SvgCalendarIcon from '@assets/svg/calendar/CalendarIcon'
import SvgEditIcon from '@assets/svg/review/EditIcon'

interface MyComponent {
  className?: string
  onClick?: () => void
}

type NavContent = {
  label: string
  Component?: React.ComponentType<MyComponent>
  navigate?: string
}

interface PathToNavContent {
  [key: string]: NavContent
}

const pathToNavContent: PathToNavContent = {
  '/calendar': { label: '캘린더', Component: SvgCalendarIcon, navigate: '/home' },
  '/observing-spot': { label: '관측지' },
  '/mypage': { label: 'MY' },
  '/review': { label: '후기', Component: SvgEditIcon, navigate: '/home' },
}

export { pathToNavContent }
