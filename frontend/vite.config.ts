import { defineConfig } from 'vite'
import react from '@vitejs/plugin-react-swc'
import svgr from 'vite-plugin-svgr'
import { VitePWA } from 'vite-plugin-pwa'

// https://vitejs.dev/config/
export default defineConfig({
  plugins: [
    react(),
    svgr({
      svgrOptions: {
        icon: true,
      },
    }),
    VitePWA({
      registerType: 'autoUpdate',
      injectRegister: 'auto',
      workbox: {
        maximumFileSizeToCacheInBytes: 4 * 1024 * 1024,
      },
    }),
  ],
  resolve: {
    alias: [
      { find: '@', replacement: '/src' },
      { find: '@apis', replacement: '/src/apis' },
      { find: '@assets', replacement: '/src/assets' },
      { find: '@components', replacement: '/src/components' },
      { find: '@constants', replacement: '/src/constants' },
      { find: '@hooks', replacement: '/src/hooks' },
      { find: '@pages', replacement: '/src/pages' },
      { find: '@queries', replacement: '/src/queries' },
      { find: '@routers', replacement: '/src/routers' },
      { find: '@stores', replacement: '/src/stores' },
      { find: '@types', replacement: '/src/types' },
      { find: '@dummy', replacement: '/src/dummy' },
      { find: '@modal', replacement: '/src/modal' },
      { find: '@utils', replacement: '/src/utils' },
    ],
  },
  server: {
    host: '0.0.0.0',
    port: 3000,
    hmr: false, // HMR 비활성화
  },
  // server: {
  //   host: '0.0.0.0',
  //   port: 3000,
  //   hmr: {
  //     protocol: 'wss', // WebSocket Secure 사용
  //     host: 'j11a609.p.ssafy.io', // 실제 서버 도메인
  //   },
  // },
})
