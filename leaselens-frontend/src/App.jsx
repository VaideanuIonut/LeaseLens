import { BrowserRouter, Navigate, Route, Routes } from 'react-router-dom'
import AuthPage from './features/auth/AuthPage'
import { AuthProvider } from './features/auth/context/AuthProvider'
import ProtectedRoute from './features/auth/components/ProtectedRoute'
import PublicOnlyRoute from './features/auth/components/PublicOnlyRoute'
import OverviewPage from './features/dashboard/OverviewPage'

function App() {
  return (
    <BrowserRouter>
      <AuthProvider>
        <Routes>
          <Route
            path="/login"
            element={<PublicOnlyRoute><AuthPage /></PublicOnlyRoute>}
          />
          <Route
            path="/overview"
            element={<ProtectedRoute><OverviewPage /></ProtectedRoute>}
          />
          <Route path="/" element={<Navigate to="/overview" replace />} />
          <Route path="*" element={<Navigate to="/overview" replace />} />
        </Routes>
      </AuthProvider>
    </BrowserRouter>
  )
}

export default App
