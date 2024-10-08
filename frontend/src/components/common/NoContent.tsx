import SvgNoContentIcon from '@assets/svg/NoContentIcon.tsx'

type NoContentProps = {
  label?: string
}

export default function NoContent({ label }: NoContentProps) {
  return (
    <div className='flex flex-col items-center w-full h-full pt-64'>
      <SvgNoContentIcon className='w-28 h-28' />
      <p className='text-2xs text-bg-50'>{label ?? '내용이 없습니다.'}</p>
    </div>
  )
}
