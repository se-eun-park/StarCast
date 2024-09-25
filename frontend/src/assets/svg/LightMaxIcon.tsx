import * as React from 'react'
import type { SVGProps } from 'react'
const SvgLightMaxIcon = (props: SVGProps<SVGSVGElement>) => (
  <svg
    xmlns='http://www.w3.org/2000/svg'
    xmlnsXlink='http://www.w3.org/1999/xlink'
    fill='none'
    viewBox='0 0 30 30'
    {...props}
  >
    <path fill='url(#light-max-icon_svg__a)' d='M0 0h30v30H0z' />
    <defs>
      <pattern
        id='light-max-icon_svg__a'
        width={1}
        height={1}
        patternContentUnits='objectBoundingBox'
      >
        <use xlinkHref='#light-max-icon_svg__b' transform='scale(.01111)' />
      </pattern>
      <image
        xlinkHref='data:image/png;base64,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'
        id='light-max-icon_svg__b'
        width={90}
        height={90}
      />
    </defs>
  </svg>
)
export default SvgLightMaxIcon
