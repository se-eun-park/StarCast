type DeleteReservationModalProps = {
  onClose: () => void
  place_uid: string
  delReservedState: number
  setDelReservedState: (isReserved: number) => void
}

const DeleteReservationModal = ({
  onClose,
  place_uid,
  delReservedState,
  setDelReservedState,
}: DeleteReservationModalProps) => {
  const handleOnClickCancelButton = () => {
    onClose()
  }

  const handleOnClickDeleteReservationButton = () => {
    console.log(place_uid)
    setDelReservedState(delReservedState + 1)
    onClose()
  }

  return (
    <div className='absolute bottom-0 flex flex-col items-center w-full px-6 py-8 bg-bg-800 rounded-t-3xl animate-slideUp'>
      <h1 className='text-2xl font-medium font-paperlogy text-text-primary'>
        현재 예약된 별 보는 날이에요.
      </h1>
      <h1 className='text-2xl font-medium font-paperlogy text-text-primary'>
        해당 일정을 <span className='text-comp2-light'>삭제</span>할까요?
      </h1>
      <div className='flex w-full mt-8 space-x-4'>
        <button onClick={handleOnClickCancelButton} className='btn-tertiary-full'>
          취소할게요
        </button>
        <button onClick={handleOnClickDeleteReservationButton} className='btn-secondary-full'>
          삭제할래요
        </button>
      </div>
    </div>
  )
}

export default DeleteReservationModal
