import type { SVGProps } from 'react'
const SvgPartlyCloudyIcon = (props: SVGProps<SVGSVGElement>) => (
  <svg
    xmlns='http://www.w3.org/2000/svg'
    xmlnsXlink='http://www.w3.org/1999/xlink'
    fill='none'
    viewBox='0 0 30 30'
    {...props}
  >
    <path
      fill='#fff'
      d='M7.144 2.67c.22-.894 1.492-.894 1.712 0l.755 3.074a.88.88 0 0 0 .645.645l3.073.755c.895.22.895 1.492 0 1.712l-3.073.755a.88.88 0 0 0-.645.645l-.755 3.073c-.22.895-1.492.895-1.712 0l-.755-3.073a.88.88 0 0 0-.645-.645L2.67 8.856c-.895-.22-.895-1.492 0-1.712l3.073-.755a.88.88 0 0 0 .645-.645z'
    />
    <circle cx={10.5} cy={16.5} r={5.5} fill='#1E1E1E' />
    <path fill='url(#partly-cloudy-icon_svg__a)' d='M3 9h25v19H3z' />
    <defs>
      <pattern
        id='partly-cloudy-icon_svg__a'
        width={1}
        height={1}
        patternContentUnits='objectBoundingBox'
      >
        <use xlinkHref='#partly-cloudy-icon_svg__b' transform='matrix(.01 0 0 .01316 0 -.158)' />
      </pattern>
      <image
        xlinkHref='data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAGQAAABkCAYAAABw4pVUAAAACXBIWXMAAAsTAAALEwEAmpwYAAADkElEQVR4nO3d/2tNcRzH8SPfRmyIxC9ihPKltOZLQ6EoZrE2Ris/iPAf8Mt+8JOVL1spP6BWmhDhF4W1GpJQfvRl+ZIvI7aUb5l56tOOe2+cbffunns+59zzetR+OLXu3ue87s6553zen891HBEREREREREREREREYk0YBIwF1gGbAAqgDXAImCi7fryGjAEKAMOAJeBdwzsI9AK1AErgWG29yPygDnAQeAF2esAjgDzbO9XVIM4D/zGf+Y1rwCLbe9nVK4Lp4BfOQjCK5gmYLLt/Q4lYF2a1wa/dQHVtvc/NIDhQEOOTk+ZOA6McOIMGAlcJDxuAoVOHAFjgRbC5x5Q5MTwNNVKeJnaCpy4oPd8HXZnnTgAdhAd+5x8BhQD34mOH3l9Zw9cJXrazLM0JyKfkjYBJ4DbwDPgC/AT+OB+WjkJ1ALjgXKiq9YJ+XOmJvfAZ/Kv30V0PQGGOmHivstNED3EU40TFkAp0E68tTlhAFQB3baPRgiYZ23FtsMoz/Bake/22wxjYcTuGYLQYvNZ08NAdjFazBt0lI1ATDOBeCsJOozRbseGeNsedCC7+yhEetUFHcgD9w+Lt4YgwxgX4zvxdJ0OMpC1aZcVX81BhTHdbSCT/t0FZuQyCNOsfEmnqoz0uN0yS/wMogg4piB86X6ckG0Yy4G32dUiKd6YYzrYMCqBb6mvJr4wg3BbMw1jVwhaNvP92rIz3TDM7CKNa+SeOcarBwpjJtAZQDHS61O/g1luE7EE60ZfYWwLuBBJqvYaaHqd8gsSrFcmg9RAtgRcgPyvMjWQMLf8x+taAkzVPUcomPu+KSaQGtuVSEJ1VCbFxEWjCeSO7Sok4ZYJ5GlyWyx77ES8/T/fdJpA9CAxPLpNIJ9tVyEJXSaQ58ltsazdBHLddhWScM0Ecji5LZbVm0A22q5CEtabQArVzBAKX4Exfx8wNtuuRjiT+vh9hQ6IdWX/jhqapSHEjlavMfUStYta69Eq7avRodFOTbF2tL82oALNqg3UowFn67rzP9RgHUzj9bR+w0gJZQHwPoCi4qoDmJ9WGCmhzHLXthL/l3AqzmZy5wWfC4qzc76s9+vOFTHddTI4L4HNWQfh8Qlsr8bgMz497TErc/sahseXppgJoPXu4gEaAk4yS1TdBw4BS3MWwgABmUbt2Wbyift1QlUx+6kAVrnHINkwLSIiIiIiIiIiIiIiIo6XPxdrmfEO2OdtAAAAAElFTkSuQmCC'
        id='partly-cloudy-icon_svg__b'
        width={100}
        height={100}
      />
    </defs>
  </svg>
)
export default SvgPartlyCloudyIcon
