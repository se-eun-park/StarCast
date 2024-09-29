/** @type {import('tailwindcss').Config} */
export default {
  content: ['./index.html', './src/**/*.{js,ts,jsx,tsx}'],
  theme: {
    fontFamily: {
      paperlogy: ['Paperlogy', 'sans-serif'],
      pretendard: ['Pretendard', 'sans-serif'],
    },
    fontSize: {
      '3xl': '1.5rem',
      '2xl': '1.375rem',
      'xl': '1.25rem',
      'lg': '1.125rem',
      'md': '1rem',
      'sm': '0.875rem',
      'xs': '0.75rem',
      '2xs': '0.6875rem',
    },
    colors: {
      white: '#FFFFFF',
      black: '#000000',

      text: {
        primary: '#FFFFFF',
        secondary: '#CCCCCC',
        tertiary: '#999999',
      },

      btn: {
        primary: {
          text: '#000814',
          bg: '#FAFA33',
        },
        secondary: {
          text: '#FAFA33',
          bg: '#002254',
        },
        tertiary: {
          text: '#B2D8FF',
          bg: '#002254',
        },
        outline: {
          text: '#FAFA33',
          bg: '#000814',
        },
        disabled: {
          text: '#336599',
          bg: '#001A3F',
        },
      },

      bg: {
        50: '#B2D8FF',
        100: '#0064CC',
        200: '#0056B4',
        300: '#004A9B',
        400: '#003C83',
        500: '#00306B',
        600: '#002254',
        700: '#001A3F',
        800: '#00122A',
        900: '#000814',
      },

      primary: {
        DEFAULT: '#FAFA33',
        light: '#E9E996',
        dark: '#A2A220',
      },

      comp1: {
        DEFAULT: '#70F58C',
        light: '#88DD9A',
        dark: '#489D5B',
      },

      comp2: {
        DEFAULT: '#F26B76',
        light: '#DB8289',
        dark: '#9B444B',
      },

      comp3: {
        DEFAULT: '#9557D5',
        light: '#D8B4FD',
        dark: '#58248F',
      },
    },

    extend: {
      borderRadius: {
        '2xl': '1.25rem',
        '3xl': '1.875rem',
      },
    },
  },
  plugins: [],
}
