type ReservationModalProps = {
  onClose: () => void
  place_uid: string
}

const ReservationModal = ({ onClose, place_uid }: ReservationModalProps) => {
  const handleOnClickCancelButton = () => {
    onClose()
  }

  const handleOnClickReservationButton = () => {
    console.log(place_uid)
    onClose()
  }

  return (
    <div className='absolute bottom-0 flex flex-col items-center w-full px-6 py-8 bg-bg-800 rounded-t-3xl animate-slideUp'>
      <h1 className='text-2xl font-medium font-paperlogy text-text-primary'>
        등록된 일정이 없어요.
      </h1>
      <h1 className='text-2xl font-medium font-paperlogy text-primary-light'>
        해당 일정을 예약할까요?
      </h1>
      <p className='text-xs text-text-secondary'>나의 관측 일정은 하나만 등록할 수 있어요.</p>
      <div className='flex w-full mt-8 space-x-4'>
        <button onClick={handleOnClickCancelButton} className='btn-tertiary-full'>
          취소할게요
        </button>
        <button onClick={handleOnClickReservationButton} className='btn-secondary-full'>
          예약할래요!
        </button>
      </div>
    </div>
  )
}

export default ReservationModal
