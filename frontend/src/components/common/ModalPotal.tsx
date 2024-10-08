import ReactDom from 'react-dom'
import { PropsWithStrictChildren } from '../../types/propsWithStrictChildren'

export const ModalPortal = ({ children }: PropsWithStrictChildren) => {
  const modalRoot = document.getElementById('modal-root') as HTMLElement

  return ReactDom.createPortal(children, modalRoot)
}
