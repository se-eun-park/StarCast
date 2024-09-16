/** @type {import('tailwindcss').Config} */
export default {
  content: ['./index.html', './src/**/*.{js,ts,jsx,tsx}'],
  theme: {
    colors: {
      white: '#FFFFFF',

      bg: {
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
        light: '#FAFA7A',
        dark: '#A2A220',
      },

      secondary: {
        DEFAULT: '#9432FA',
        light: '#B573FA',
        dark: '#5F20A2',
      },

      comp1: {
        DEFAULT: '#70F58C',
        light: '#ADF5BC',
        dark: '#489D5B',
      },

      comp2: {
        DEFAULT: '#F26B76',
        light: '#F3959D',
        dark: '#9B444B',
      },
    },

    extend: {},
  },
  plugins: [],
}
