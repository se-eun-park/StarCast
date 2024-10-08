import { PropsWithStrictChildren } from '../../types/propsWithStrictChildren'

type RadioGroupProps = {
  label?: string
  className?: string
}

const RadioGroup = ({ label, className, children }: PropsWithStrictChildren<RadioGroupProps>) => {
  return (
    <fieldset className={className}>
      <legend>{label}</legend>
      {children}
    </fieldset>
  )
}

export default RadioGroup
