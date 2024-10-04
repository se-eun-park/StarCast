import * as React from 'react'
import type { SVGProps } from 'react'
const SvgNotificationActiveIcon = (props: SVGProps<SVGSVGElement>) => (
  <svg xmlns='http://www.w3.org/2000/svg' fill='none' viewBox='0 0 20 20' {...props}>
    <mask
      id='notification-active-icon_svg__a'
      width={20}
      height={20}
      x={0}
      y={0}
      maskUnits='userSpaceOnUse'
      style={{
        maskType: 'alpha',
      }}
    >
      <path fill='#D9D9D9' d='M0 0h20v20H0z' />
    </mask>
    <g mask='url(#notification-active-icon_svg__a)'>
      <path
        fill='#fff'
        d='M10 18.334q-.687 0-1.177-.49a1.6 1.6 0 0 1-.49-1.177h3.334q0 .687-.49 1.177-.489.49-1.177.49m-6.666-2.5v-1.667H5V8.334A4.88 4.88 0 0 1 6.042 5.26 4.8 4.8 0 0 1 8.75 3.5v-.584q0-.52.365-.885.364-.365.885-.365t.886.365.364.885v.27a3.84 3.84 0 0 0-.417 1.917 10 10 0 0 0-.406-.072A3 3 0 0 0 10 5a3.2 3.2 0 0 0-2.354.98 3.2 3.2 0 0 0-.98 2.354v5.833h6.668V8.813A4.2 4.2 0 0 0 15 9.167v5h1.667v1.667zM15 7.5a2.4 2.4 0 0 1-1.77-.729 2.4 2.4 0 0 1-.73-1.77q0-1.042.73-1.772A2.4 2.4 0 0 1 15 2.5a2.4 2.4 0 0 1 1.771.73q.73.729.73 1.77 0 1.042-.73 1.771a2.4 2.4 0 0 1-1.77.73'
      />
    </g>
  </svg>
)
export default SvgNotificationActiveIcon
