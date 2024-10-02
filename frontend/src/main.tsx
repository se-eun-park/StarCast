import { StrictMode } from 'react'
import { createRoot } from 'react-dom/client'
import Router from '@routers/Router.tsx'
import './main.css'
import Provider from '@components/provider/index'

createRoot(document.getElementById('root')!).render(
  <StrictMode>
    <Provider>
      <Router />
    </Provider>
  </StrictMode>,
)
