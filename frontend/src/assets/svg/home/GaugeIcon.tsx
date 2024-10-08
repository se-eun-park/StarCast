import type { SVGProps } from 'react'
const SvgGaugeIcon = ({ d, ...props }: SVGProps<SVGSVGElement>) => (
  <svg xmlns='http://www.w3.org/2000/svg' fill='#B3D8FF' viewBox='0 0 100 68' {...props}>
    <path d={d} />
  </svg>
)
export default SvgGaugeIcon
