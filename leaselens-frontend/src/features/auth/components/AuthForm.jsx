import { useState } from 'react'
import { useNavigate } from 'react-router-dom'
import Icon from '../../../components/ui/Icon'
import { login, register } from '../services/authService'
import { useAuth } from '../context/useAuth'

function AuthForm() {
  const navigate = useNavigate()
  const { authenticate } = useAuth()
  const [mode, setMode] = useState('signin')
  const [showPassword, setShowPassword] = useState(false)
  const [username, setUsername] = useState('')
  const [email, setEmail] = useState('')
  const [password, setPassword] = useState('')
  const [rememberMe, setRememberMe] = useState(false)
  const [status, setStatus] = useState('')
  const [submitting, setSubmitting] = useState(false)

  const handleSubmit = async (event) => {
    event.preventDefault()
    setStatus('')
    setSubmitting(true)

    const isRegister = mode === 'register'
    const payload = isRegister
      ? { username, email, password }
      : { email, password }

    try {
      const response = await (isRegister ? register(payload) : login(payload))
      authenticate(response, payload, rememberMe)
      setStatus(isRegister ? 'Account created successfully.' : 'Signed in successfully.')
      navigate('/overview', { replace: true })
    } catch (error) {
      setStatus(error.message)
    } finally {
      setSubmitting(false)
    }
  }

  return (
    <section className="auth-form-panel" aria-label="Authentication form">
      <div className="auth-form-wrap">
        <header className="auth-brand">
          <div className="brand-row">
            <div className="brand-mark" aria-hidden="true">
              <Icon name="building" size={20} />
            </div>
            <h1>LeaseLens</h1>
          </div>
          <p>Lease contract intelligence for commercial real estate teams.</p>
        </header>

        <div className="auth-tabs" role="tablist" aria-label="Authentication mode">
          <button
            type="button"
            role="tab"
            aria-selected={mode === 'signin'}
            className={mode === 'signin' ? 'auth-tab active' : 'auth-tab'}
            onClick={() => setMode('signin')}
          >
            Sign In
          </button>
          <button
            type="button"
            role="tab"
            aria-selected={mode === 'register'}
            className={mode === 'register' ? 'auth-tab active' : 'auth-tab'}
            onClick={() => setMode('register')}
          >
            Create Account
          </button>
        </div>

        <form className="auth-form" onSubmit={handleSubmit}>
          <div className={`field username-field ${mode === 'register' ? 'field-visible' : ''}`}>
            <label htmlFor="auth-username">Username</label>
            <div className="input-wrap">
              <Icon name="user" size={18} />
              <input
                id="auth-username"
                type="text"
                name="username"
                autoComplete="username"
                placeholder="alex.morgan"
                value={username}
                onChange={(event) => setUsername(event.target.value)}
                tabIndex={mode === 'register' ? 0 : -1}
                required={mode === 'register'}
              />
            </div>
          </div>

          <div className="field">
            <label htmlFor="auth-email">Work Email</label>
            <div className="input-wrap">
              <Icon name="mail" size={18} />
              <input
                id="auth-email"
                type="email"
                name="email"
                autoComplete="email"
                placeholder="alex.morgan@meridiancre.com"
                value={email}
                onChange={(event) => setEmail(event.target.value)}
                required
              />
            </div>
          </div>

          <div className="field">
            <label htmlFor="auth-password">Password</label>
            <div className="input-wrap">
              <Icon name="lock" size={18} />
              <input
                id="auth-password"
                type={showPassword ? 'text' : 'password'}
                name="password"
                autoComplete={mode === 'register' ? 'new-password' : 'current-password'}
                placeholder="Enter your password"
                value={password}
                onChange={(event) => setPassword(event.target.value)}
                required
              />
              <button
                type="button"
                className="password-toggle"
                aria-label={showPassword ? 'Hide password' : 'Show password'}
                aria-pressed={showPassword}
                onClick={() => setShowPassword((visible) => !visible)}
              >
                <Icon name={showPassword ? 'eyeOff' : 'eye'} size={18} />
              </button>
            </div>
          </div>

          <div className="form-options">
            <label className="remember-option">
              <input
                type="checkbox"
                checked={rememberMe}
                onChange={(event) => setRememberMe(event.target.checked)}
              />
              <span>Remember me</span>
            </label>
            <a href="#forgot-password">Forgot password?</a>
          </div>

          <button type="submit" className="submit-button" disabled={submitting}>
            {submitting ? 'Please wait...' : mode === 'register' ? 'Create Account' : 'Sign In'}
          </button>
          {status && <p className="form-status" role="status">{status}</p>}
        </form>
      </div>
    </section>
  )
}

export default AuthForm
