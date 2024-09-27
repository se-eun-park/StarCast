import type { SVGProps } from 'react'
const SvgSunIcon = (props: SVGProps<SVGSVGElement>) => (
  <svg
    xmlns='http://www.w3.org/2000/svg'
    xmlnsXlink='http://www.w3.org/1999/xlink'
    fill='none'
    viewBox='0 0 30 30'
    {...props}
  >
    <path fill='url(#sun-icon_svg__a)' d='M0 0h30v30H0z' />
    <defs>
      <pattern id='sun-icon_svg__a' width={1} height={1} patternContentUnits='objectBoundingBox'>
        <use xlinkHref='#sun-icon_svg__b' transform='scale(.01)' />
      </pattern>
      <image
        xlinkHref='data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAGQAAABkCAYAAABw4pVUAAAACXBIWXMAAAsTAAALEwEAmpwYAAAFJUlEQVR4nO2dS48VRRSAS90JLhRf+NZoxKgoGXXnYsZRB5BxBGdvIFGiCSyNunDh4y+gUTAaQCVqoqImxheCj4XvLSwYSFBhoYJmZLjymZKjIdd7q6v73uo61V1fcpPJMNTr61td3X3qtDGZTCaTqQtgBbAReA5Ylkc+IsDj/J/HspQ4Mi4AjvUQMgcszFLqF7KM/uSpK4KQaYeQ6dob1HbIQnRBFqILshBdkIXogixEF2QhuiAL0QVZiHNw1gJ7gCPAB8B1NQhZ5bgwXFVD/dcDHwK/A7uBB4wiGd38AYwGrvdmh5CRwHWPSh+7WRuyXt/G2W8GdUsBTgF29ah3Z6g6C2RYdoes27eBs44jNbSUBcAWacOs/Lwgkox/+huq7jKN/MTRwOBSLMCp9mMC4iHD8nHINvg2dIlHQ4NLUSDD/vsSowHgVllhFTX4NpMY+PdtzGiiiVJIVUbJr7bt4DVGOcC1njJ0T8WeUl40ygFeSl5GCSlfDKGO+cBiYBy4Wz7j8rv5Qyj/y0bI8JSysUJ5C+3tCeAVYB/F2L95GbgfOL9CfZsaI6NLSvc8/AtwZYmr8RXAu0CH6thwoXeA5bZMz7qvAn7tKudIsjL+xZ7A7TnDTlNy1PnKuAv4nuHzLbC0hJRN0nbbh0WmbQAXAm8SnjdyQF2xjDHgR+rjIDBRz6GWGMCDwF/UT0fFbXNNAI8Sn0dij4PmB1yxWGfaDHBnpGnKNX2NmzYiqyl7UtXGT61cfdW0tK3Ka6ZNyEWfdiZMG5DbIT+gn+98b7MkjdybSoXlpukA75EO202TAc7rs1FTK8dsm01TkecZqbHGNBXgVdJji2kqwH7SY0bDwF0mz6inPT73SuCzc4kInAEcJz2OA/M8lvK3yFj4jNkkcKmvjKcr3l/a5YqrlQjHVLnB0a+zgc8rlGnH+MkiGfeEmm/l4VOqjDr6ZQMpBmHKJWTrgIXP9gt6lq9pqkz26dNpwNEBy97sEmLD+QehjUL+DClkasDC2zhlbQ02ZUkFT1U8qe8sOKnfSLosLtg01Gsnl8/DsCecMk6q5GKZYnyXvSOeoZ+NXPZa7BiUXPZeZGKT6IXhXtNUJC43NTabpiKBz6mx2jSVfPtdIRLJngpvmaYjWwJSYalpA8DX6OebVgQ5WOyRh35uN21C9mdoZZtpG7Jn0IZtauNAowMbXNhpYcC9g8Omk/wewoZdLD4UezxUYDfLxDYBPBx7HFQB3BcpiK6jJj2f0n3tB2qU8TNwR+x+p7D6er0GGdtauZoCrgCeBT4FngEu9/x/E7LJf9h85XvRZ9sqbd5Rpu3ap6DDXQNyyDdIjBMBaPbe1/YBzy/2rTtvl7k3JcGCtq0nczjZZXFB8pkNFco71wY+S2TMjMdj173yt6uBcyrUt6ExyWc80jPtGEId8ySZ8Zg8o56Un+3vTh9C+TZog+SleCYwe94oh/7pmdKRUiLF39VGOcCipFP8JZ8wskmJPZsoI1kpwE05kfJ/UkY0CPkMBanGjY7zY9AXAfheuM3FkgGcJelcf5P8iC8AZ0aUcjRU3WUaORPxdRUf9aj3/ZDBCQVS4oedAuvrlmGR/Lz9CPqGH4eU+Pm25EhdL7cq5uQKN/jJDVjpELKyhvpHpK9z0vd1rQkb6kV+KZgyyG9p0wVZiC7IQnRBFqILshBdkIXogixEF2QhusCdqqNnKoxMWCGX9Mk40VGxKb+NcCINSDd+6SoywaRM2c398nEncslkMpmMGSJ/A2G+hJ1Mm2vvAAAAAElFTkSuQmCC'
        id='sun-icon_svg__b'
        width={100}
        height={100}
      />
    </defs>
  </svg>
)
export default SvgSunIcon
