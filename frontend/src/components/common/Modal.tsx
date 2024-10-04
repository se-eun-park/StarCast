import { ModalPortal } from '@components/common/ModalPotal'

type ModalProps = {
  onClose: () => void
  children: React.ReactNode
}

const Modal = ({ onClose, children }: ModalProps) => {
  return (
    <ModalPortal>
      <div className='flex items-center justify-center w-full h-full'>
        <div
          onClick={onClose}
          className='fixed top-0 w-[37.5rem] min-h-dvh overflow-hidden text-center bg-black bg-opacity-70 z-50'
        >
          <div onClick={(e) => e.stopPropagation()}>{children}</div>
        </div>
      </div>
    </ModalPortal>
  )
}

export default Modal
