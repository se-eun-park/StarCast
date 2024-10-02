import * as React from 'react'
import type { SVGProps } from 'react'
const SvgReviewIcon = (props: SVGProps<SVGSVGElement>) => (
  <svg xmlns='http://www.w3.org/2000/svg' fill='none' viewBox='0 0 25 24' {...props}>
    <mask
      id='review-icon_svg__a'
      width={25}
      height={24}
      x={0}
      y={0}
      maskUnits='userSpaceOnUse'
      style={{
        maskType: 'alpha',
      }}
    >
      <path fill='#D9D9D9' d='M.5 0h24v24H.5z' />
    </mask>
    <g mask='url(#review-icon_svg__a)'>
      <path
        fill='#D7D7D7'
        d='M5.5 21q-.824 0-1.412-.587A1.93 1.93 0 0 1 3.5 19V5q0-.824.588-1.412A1.93 1.93 0 0 1 5.5 3h14q.824 0 1.413.587.587.588.587 1.413v14q0 .824-.587 1.413A1.93 1.93 0 0 1 19.5 21zm1-4h12l-3.75-5-3 4-2.25-3zM9 10q.624 0 1.063-.437.437-.438.437-1.063t-.437-1.062A1.45 1.45 0 0 0 9 7q-.625 0-1.062.438A1.45 1.45 0 0 0 7.5 8.5q0 .624.438 1.063Q8.374 10 9 10'
      />
    </g>
  </svg>
)
export default SvgReviewIcon
