import { useCallback, useState } from 'react'
import Modal from '@components/common/Modal'

type UseModalProps = {
  children: React.ReactNode
}

const useModal = ({ useBlur = true } = {}) => {
  const [isOpen, setIsOpen] = useState(false)

  const open = useCallback(() => {
    setIsOpen(() => true)
  }, [])

  const close = useCallback(() => {
    setIsOpen(() => false)
  }, [])

  return {
    Modal: isOpen
      ? ({ children }: UseModalProps) => (
          <Modal onClose={useBlur ? close : () => {}}>{children}</Modal>
        )
      : () => null,
    open,
    close,
    isOpen,
  }
}

export default useModal
