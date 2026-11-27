import { useMemo, useState } from 'react'
import { AuthContext } from './authContext'

const SESSION_KEY = 'leaselens_session'

function getText(value) {
  return typeof value === 'string' ? value.trim() : ''
}

function deriveName(value) {
  return getText(value)
    .split('@')[0]
    .split(/[.\s_-]+/)
    .filter(Boolean)
    .map((part) => `${part.charAt(0).toUpperCase()}${part.slice(1)}`)
    .join(' ')
}

function readSession() {
  const stored = localStorage.getItem(SESSION_KEY) || sessionStorage.getItem(SESSION_KEY)
  if (!stored) return null

  try {
    return JSON.parse(stored)
  } catch {
    localStorage.removeItem(SESSION_KEY)
    sessionStorage.removeItem(SESSION_KEY)
    return null
  }
}

export function AuthProvider({ children }) {
  const [session, setSession] = useState(readSession)

  const authenticate = (response, payload, rememberMe) => {
    const responseUser = response?.user || response?.data?.user || {}
    const email = getText(responseUser.email || response?.email || response?.data?.email || payload.email)
    const responseName = getText(
      responseUser.fullName
      || responseUser.displayName
      || responseUser.name
      || response?.fullName
      || response?.displayName
      || response?.name,
    )
    const firstAndLastName = [
      getText(responseUser.firstName || response?.firstName),
      getText(responseUser.lastName || response?.lastName),
    ]
      .filter(Boolean)
      .join(' ')
    const derivedName = deriveName(
      responseUser.username
      || response?.username
      || payload.fullName
      || payload.name
      || payload.username
      || email,
    )
    const user = {
      name: responseName || firstAndLastName || derivedName || 'Account holder',
      email,
    }
    const token = response?.token || response?.accessToken || null
    const nextSession = {
      authenticated: true,
      token,
      user,
      rememberMe: Boolean(rememberMe),
    }
    const storage = rememberMe ? localStorage : sessionStorage

    localStorage.removeItem(SESSION_KEY)
    sessionStorage.removeItem(SESSION_KEY)
    storage.setItem(SESSION_KEY, JSON.stringify(nextSession))
    setSession(nextSession)
  }

  const logout = () => {
    localStorage.removeItem(SESSION_KEY)
    sessionStorage.removeItem(SESSION_KEY)
    setSession(null)
  }

  const value = useMemo(() => ({
    session,
    user: session?.user || null,
    isAuthenticated: Boolean(session?.authenticated),
    authenticate,
    logout,
  }), [session])

  return <AuthContext.Provider value={value}>{children}</AuthContext.Provider>
}
