import SvgEditIcon from '@assets/svg/review/EditIcon'

type MyComponent = {
  className?: string
  onClick?: () => void
}

type NavContent = {
  label: string
  Component?: React.ComponentType<MyComponent>
  navigate?: string
}

type PathToNavContent = {
  [key: string]: NavContent
}

const pathToNavContent: PathToNavContent = {
  '/calendar': { label: '캘린더' },
  '/observing-spot': { label: '관측지' },
  '/mypage': { label: 'MY' },
  '/review': { label: '후기', Component: SvgEditIcon, navigate: '/review/new' },
  '/review/new': { label: '후기 작성' },
}

export { pathToNavContent }
