import type { SVGProps } from 'react'

type Props = SVGProps<SVGSVGElement> & {
  fillColor?: string;
};

const SvgStarIconCal = ({ fillColor = '#B3D8FF', ...props }: Props) => (
  <svg xmlns='http://www.w3.org/2000/svg' fill={fillColor} viewBox='0 0 20 21' {...props}>
    <path d='M8.574 1.531c.366-1.49 2.486-1.49 2.852 0l1.259 5.122c.13.53.545.945 1.075 1.075l5.122 1.26c1.49.365 1.49 2.485 0 2.851l-5.122 1.259c-.53.13-.945.545-1.075 1.075l-1.259 5.122c-.366 1.49-2.486 1.49-2.852 0l-1.259-5.122a1.47 1.47 0 0 0-1.075-1.075l-5.122-1.259c-1.49-.366-1.49-2.486 0-2.852L6.24 7.728c.53-.13.945-.544 1.075-1.075z' />
  </svg>
)
export default SvgStarIconCal
