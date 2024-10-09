import { PropsWithStrictChildren } from '../../types/propsWithStrictChildren'

type RadioProps = {
  value: string
  name: string
  className?: string
  defaultChecked?: boolean
  disabled?: boolean
  onChange?: () => void
}

const Radio = ({
  children,
  value,
  name,
  className,
  onChange,
  disabled,
}: PropsWithStrictChildren<RadioProps>) => {
  return (
    <label>
      <input
        type='radio'
        value={value}
        name={name}
        onChange={onChange}
        disabled={disabled}
        className={`hidden peer ${className}`}
      />
      {children}
    </label>
  )
}

export default Radio
