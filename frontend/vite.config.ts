import { defineConfig } from 'vite'
import react from '@vitejs/plugin-react-swc'
import svgr from 'vite-plugin-svgr'

// https://vitejs.dev/config/
export default defineConfig({
  plugins: [
    react(),
    svgr({
      svgrOptions: {
        icon: true,
      },
    }),
  ],
  resolve: {
    alias: [
      { find: '@', replacement: '/src' },
      { find: '@apis', replacement: '/src/apis' },
      { find: '@assets', replacement: '/src/assets' },
      { find: '@components', replacement: '/src/components' },
      { find: '@hooks', replacement: '/src/hooks' },
      { find: '@pages', replacement: '/src/pages' },
      { find: '@queries', replacement: '/src/queries' },
      { find: '@routers', replacement: '/src/routers' },
      { find: '@stores', replacement: '/src/stores' },
      { find: '@types', replacement: '/src/types' },
      { find: '@dummy', replacement: '/src/dummy' },
    ],
  },
  server: {
    port: 3000,
  },
})
