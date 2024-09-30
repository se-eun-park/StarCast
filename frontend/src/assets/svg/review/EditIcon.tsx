import * as React from 'react'
import type { SVGProps } from 'react'
const SvgEditIcon = (props: SVGProps<SVGSVGElement>) => (
  <svg xmlns='http://www.w3.org/2000/svg' fill='none' viewBox='0 0 24 24' {...props}>
    <mask
      id='edit-icon_svg__a'
      width={24}
      height={24}
      x={0}
      y={0}
      maskUnits='userSpaceOnUse'
      style={{
        maskType: 'alpha',
      }}
    >
      <path fill='#D9D9D9' d='M0 0h24v24H0z' />
    </mask>
    <g mask='url(#edit-icon_svg__a)'>
      <path
        fill='#fff'
        d='M5 21q-.824 0-1.412-.588A1.93 1.93 0 0 1 3 19V5q0-.825.587-1.413A1.93 1.93 0 0 1 5 3h8.925l-2 2H5v14h14v-6.95l2-2V19q0 .825-.587 1.412A1.93 1.93 0 0 1 19 21zm4-6v-4.25l9.175-9.175q.3-.3.675-.45t.75-.15a1.98 1.98 0 0 1 1.425.6L22.425 3q.275.3.425.662T23 4.4t-.137.737a1.9 1.9 0 0 1-.438.663L13.25 15zm2-2h1.4l5.8-5.8-.7-.7-.725-.7L11 11.575z'
      />
    </g>
  </svg>
)
export default SvgEditIcon
