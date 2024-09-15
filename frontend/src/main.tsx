import { StrictMode } from 'react'
import { createRoot } from 'react-dom/client'
import Router from '@routers/Router.tsx'
import './main.css'

createRoot(document.getElementById('root')!).render(
  <StrictMode>
    <Router />
  </StrictMode>,
)
