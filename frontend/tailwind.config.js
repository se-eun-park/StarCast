/** @type {import('tailwindcss').Config} */
export default {
  content: ['./index.html', './src/**/*.{js,ts,jsx,tsx}'],
  theme: {
    colors: {
      white: '#FFFFFF',
      black: '#000000',

      text: {
        primary: '#FFFFFF',
        secondary: '#e0e0e0',
        teritory: '#9e9e9e',
      },

      btn: {
        text: '#000814',
        secondarytext: '#8DBFF4',
        disabledtext: '#336599',
        primarybg: '#fafa33',
        secondarybg: '#002254',
        teritorybg: '##001A3F',
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
        pulse: {
          '0%, 100%': {
            opacity: '1',
          },
          '50%': {
            opacity: '.3',
          },
        },
      },
      animation: {
        slideRight: 'slideRight 40s linear infinite',
        'pulse-delay': 'pulse 3s cubic-bezier(0.4, 0, 0.6, 1) infinite var(--delay)',
      },
    },
  },
  plugins: [],
}
