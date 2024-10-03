import ReactDom from 'react-dom'

type ModalPortalProps = {
  children: React.ReactNode
}

export const ModalPortal = ({ children }: ModalPortalProps) => {
  const modalRoot = document.getElementById('modal-root') as HTMLElement

  return ReactDom.createPortal(children, modalRoot)
}
