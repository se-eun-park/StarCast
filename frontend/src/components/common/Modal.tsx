import { ModalPortal } from '@components/common/ModalPotal'
import { PropsWithStrictChildren } from '../../types/propsWithStrictChildren'

type ModalProps = {
  onClose: () => void
}

const Modal = ({ onClose, children }: PropsWithStrictChildren<ModalProps>) => {
  const handleModalClick = (e: React.MouseEvent) => {
    e.stopPropagation()
  }

  return (
    <ModalPortal>
      <div className='flex items-center justify-center w-full h-full'>
        <div
          onClick={onClose}
          className='fixed top-0 w-[37.5rem] min-h-dvh overflow-hidden text-center bg-black bg-opacity-70 z-50'
        >
          <div onClick={handleModalClick}>{children}</div>
        </div>
      </div>
    </ModalPortal>
  )
}

export default Modal
