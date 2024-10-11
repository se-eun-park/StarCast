import type { SVGProps } from 'react'
const SvgDarkSunIcon = (props: SVGProps<SVGSVGElement>) => (
  <svg
    xmlns='http://www.w3.org/2000/svg'
    xmlnsXlink='http://www.w3.org/1999/xlink'
    fill='none'
    viewBox='0 0 20 20'
    {...props}
  >
    <path fill='url(#dark-sun-icon_svg__a)' d='M0 0h20v20H0z' />
    <defs>
      <pattern
        id='dark-sun-icon_svg__a'
        width={1}
        height={1}
        patternContentUnits='objectBoundingBox'
      >
        <use xlinkHref='#dark-sun-icon_svg__b' transform='scale(.00833)' />
      </pattern>
      <image
        xlinkHref='data:image/png;base64,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'
        id='dark-sun-icon_svg__b'
        width={120}
        height={120}
      />
    </defs>
  </svg>
)
export default SvgDarkSunIcon
