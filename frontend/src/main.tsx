import { StrictMode } from 'react'
import { createRoot } from 'react-dom/client'
import App from './App.tsx'
import Router from '@routers/Router.tsx'
import './main.css'

createRoot(document.getElementById('root')!).render(
  <StrictMode>
    <App />
    <Router />
  </StrictMode>,
)
