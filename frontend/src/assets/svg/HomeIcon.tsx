import type { SVGProps } from 'react'
const SvgHomeIcon = (props: SVGProps<SVGSVGElement>) => (
  <svg xmlns='http://www.w3.org/2000/svg' fill='none' viewBox='0 0 24 25' {...props}>
    <mask
      id='home-icon_svg__a'
      width={24}
      height={25}
      x={0}
      y={0}
      maskUnits='userSpaceOnUse'
      style={{
        maskType: 'alpha',
      }}
    >
      <path fill='#D9D9D9' d='M0 .5h24v24H0z' />
    </mask>
    <g mask='url(#home-icon_svg__a)'>
      <path
        fill='#fff'
        d='M6.75 19h2.625v-5.25h5.25V19h2.625v-7.875L12 7.188l-5.25 3.937zM5 20.75v-10.5L12 5l7 5.25v10.5h-6.125V15.5h-1.75v5.25z'
      />
    </g>
  </svg>
)
export default SvgHomeIcon
