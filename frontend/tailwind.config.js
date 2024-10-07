/** @type {import('tailwindcss').Config} */
export default {
  content: ['./index.html', './src/**/*.{js,ts,jsx,tsx}'],
  theme: {
    fontFamily: {
      paperlogy: ['Paperlogy', 'sans-serif'],
      pretendard: ['Pretendard', 'sans-serif'],
    },
    fontSize: {
      '3xl': ['1.5rem', { lineHeight: '2rem' }],
      '2xl': ['1.375rem', { lineHeight: '1.875rem' }],
      xl: ['1.25rem', { lineHeight: '1.625rem' }],
      lg: ['1.125rem', { lineHeight: '1.5rem' }],
      md: ['1rem', { lineHeight: '1.375rem' }],
      sm: ['0.875rem', { lineHeight: '1.25rem' }],
      xs: ['0.75rem', { lineHeight: '1rem' }],
      '2xs': ['0.6875rem', { lineHeight: '0.875rem' }],
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
        outlined: {
          text: '#FAFA33',
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
        transparent: 'transparent',
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
      screens: {
        sm: '600px',
      },
      opacity: {
        7: '0.07',
      },
      backgroundImage: {
        gradient900to700: 'linear-gradient(#000814, #001A3F)',
        gradient900to800: 'linear-gradient(#000814, #00122A)',
        bgGradient: 'linear-gradient(180deg, #00122a 0%, #000814 100%)',
      },
      keyframes: {
        slideRight: {
          '0%': {
            transform: 'translateX(0px)',
          },
          '100%': {
            transform: 'translateX(450px)',
          },
        },
        slideUp: {
          '0%': {
            transform: 'translateY(100%)',
          },
          '100%': {
            transform: 'translateY(0%)',
          },
        },
        slideDown: {
          '0%': {
            transform: 'translateY(0%)',
          },
          '100%': {
            transform: 'translateY(100%)',
          },
        },
        pulse: {
          '0%, 100%': {
            opacity: '1',
          },
          '50%': {
            opacity: '.3',
          },
        },
        fadeIn: {
          '0%, 50%': {
            opacity: '0',
          },
          '100%': {
            opacity: '1',
          },
        },
      },
      animation: {
        slideRight: 'slideRight 40s linear infinite',
        'pulse-delay': 'pulse 3s cubic-bezier(0.4, 0, 0.6, 1) infinite var(--delay)',
        slideUp: 'slideUp 0.3s ease-in-out',
        slideDown: 'slideDown 0.3s ease-in-out',
        fadeIn: 'fadeIn 0.3s ease-in-out',
      },
    },
  },
  plugins: [require('tailwind-scrollbar-hide')],
}
