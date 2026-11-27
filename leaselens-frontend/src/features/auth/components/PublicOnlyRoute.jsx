import { Navigate } from 'react-router-dom'
import { useAuth } from '../context/useAuth'

function PublicOnlyRoute({ children }) {
  const { isAuthenticated } = useAuth()
  return isAuthenticated ? <Navigate to="/overview" replace /> : children
}

export default PublicOnlyRoute
