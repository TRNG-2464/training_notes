import { StrictMode } from 'react'
import { createRoot } from 'react-dom/client'
import './index.css'
import App from './App.tsx'
import SimpleAuthApp from './auth-intro/SimpleAuthApp.tsx'
import AuthContextApp from './auth-context/AuthContextApp.tsx'

createRoot(document.getElementById('root')!).render(
  <StrictMode>
    {/* <App /> */}
    {/* <SimpleAuthApp /> */}
    <AuthContextApp />
  </StrictMode>
)
