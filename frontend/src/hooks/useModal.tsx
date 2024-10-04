import { useCallback, useState } from 'react'
import { PropsWithStrictChildren } from '../types/propsWithStrictChildren'
import ModalComponent from '@components/common/Modal'

const useModal = ({ isBlurBackground = true } = {}) => {
  const [isOpen, setIsOpen] = useState(false)

  const open = useCallback(() => {
    setIsOpen(() => true)
  }, [])

  const close = useCallback(() => {
    setIsOpen(() => false)
  }, [])

  const Modal = ({ children }: PropsWithStrictChildren) => {
    if (!isOpen) return null
    return <ModalComponent onClose={isBlurBackground ? close : () => {}}>{children}</ModalComponent>
  }

  return {
    Modal,
    open,
    close,
  }
}

export default useModal
