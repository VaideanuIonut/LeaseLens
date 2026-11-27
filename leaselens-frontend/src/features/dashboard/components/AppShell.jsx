import { useEffect, useRef, useState } from 'react'
import { useNavigate } from 'react-router-dom'
import {
  Bell,
  Building2,
  CalendarDays,
  ChevronDown,
  ClipboardList,
  Grid2X2,
  ChartNoAxesCombined,
  LogOut,
  Settings,
  UserRound,
  X,
} from 'lucide-react'
import { useAuth } from '../../auth/context/useAuth'

const navigation = [
  { label: 'Overview', icon: Grid2X2, active: true },
  { label: 'Contracts', icon: ClipboardList },
  { label: 'Critical Dates', icon: ClipboardList },
  { label: 'Calendar', icon: CalendarDays },
  { label: 'Analytics', icon: ChartNoAxesCombined },
]

function deriveName(value) {
  if (typeof value !== 'string') return ''

  return value.trim()
    .split('@')[0]
    .split(/[.\s_-]+/)
    .filter(Boolean)
    .map((part) => `${part.charAt(0).toUpperCase()}${part.slice(1)}`)
    .join(' ')
}

function getInitials(name) {
  return name
    .trim()
    .split(/\s+/)
    .filter(Boolean)
    .slice(0, 2)
    .map((part) => part.charAt(0).toUpperCase())
    .join('')
}

function AppShell({ children }) {
  const navigate = useNavigate()
  const { user, session, logout } = useAuth()
  const [isProfileMenuOpen, setIsProfileMenuOpen] = useState(false)
  const [activeProfilePanel, setActiveProfilePanel] = useState(null)
  const profileMenuRef = useRef(null)
  const profileButtonRef = useRef(null)
  const profileDialogCloseRef = useRef(null)
  const email = typeof user?.email === 'string' ? user.email.trim() : ''
  const displayName = [
    user?.fullName,
    user?.name,
    user?.displayName,
    user?.username,
  ].find((value) => typeof value === 'string' && value.trim())
    ?.trim() || deriveName(email) || 'Account holder'
  const initials = getInitials(displayName) || (email.charAt(0).toUpperCase() || 'U')

  useEffect(() => {
    if (!isProfileMenuOpen) return undefined

    const handlePointerDown = (event) => {
      if (!profileMenuRef.current?.contains(event.target)) {
        setIsProfileMenuOpen(false)
      }
    }
    const handleKeyDown = (event) => {
      if (event.key === 'Escape') {
        setIsProfileMenuOpen(false)
        profileButtonRef.current?.focus()
      }
    }

    document.addEventListener('pointerdown', handlePointerDown)
    document.addEventListener('keydown', handleKeyDown)
    return () => {
      document.removeEventListener('pointerdown', handlePointerDown)
      document.removeEventListener('keydown', handleKeyDown)
    }
  }, [isProfileMenuOpen])

  useEffect(() => {
    if (isProfileMenuOpen) {
      profileMenuRef.current?.querySelector('[role="menu"] button')?.focus()
    }
  }, [isProfileMenuOpen])

  useEffect(() => {
    if (!activeProfilePanel) return undefined

    profileDialogCloseRef.current?.focus()
    const handleKeyDown = (event) => {
      if (event.key === 'Escape') {
        setActiveProfilePanel(null)
        profileButtonRef.current?.focus()
      }
    }

    document.addEventListener('keydown', handleKeyDown)
    return () => document.removeEventListener('keydown', handleKeyDown)
  }, [activeProfilePanel])

  const openProfilePanel = (panel) => {
    setIsProfileMenuOpen(false)
    setActiveProfilePanel(panel)
  }

  const closeProfilePanel = () => {
    setActiveProfilePanel(null)
    profileButtonRef.current?.focus()
  }

  const signOut = () => {
    setIsProfileMenuOpen(false)
    logout()
    navigate('/login', { replace: true })
  }

  return (
    <div className="app-shell">
      <header className="app-header">
        <div className="header-left">
          <a className="brand-link" href="#" aria-label="LeaseLens overview">
            <span className="header-brand-mark"><Building2 size={17} strokeWidth={1.8} /></span>
            <span>LeaseLens</span>
          </a>
          <nav className="primary-nav" aria-label="Primary navigation">
            {navigation.map(({ label, icon: Icon, active }) => (
              <a key={label} href="#" className={active ? 'nav-link active' : 'nav-link'}>
                <Icon size={13} strokeWidth={1.8} />
                <span>{label}</span>
              </a>
            ))}
          </nav>
        </div>

        <div className="header-actions">
          <button type="button" className="header-add-button">+ Add Contract</button>
          <button type="button" className="notification-button" aria-label="Notifications">
            <Bell size={17} strokeWidth={1.8} />
            <span className="notification-badge">1</span>
          </button>
          <div className="profile-menu-container" ref={profileMenuRef}>
            <button
              ref={profileButtonRef}
              type="button"
              className="profile-button"
              aria-label={`Profile menu for ${displayName}`}
              aria-expanded={isProfileMenuOpen}
              aria-haspopup="menu"
              aria-controls="profile-menu"
              onClick={() => setIsProfileMenuOpen((isOpen) => !isOpen)}
            >
              <span className="avatar">{initials}</span>
              <span className="profile-copy">
                <strong>{displayName}</strong>
                <span>{email || 'Email unavailable'}</span>
              </span>
              <ChevronDown className="profile-chevron" size={14} aria-hidden="true" />
            </button>
            {isProfileMenuOpen && (
              <div className="profile-menu" id="profile-menu" role="menu" aria-label="Profile actions">
                <button type="button" role="menuitem" className="profile-menu-item" onClick={() => openProfilePanel('personal-info')}>
                  <UserRound size={16} aria-hidden="true" />
                  <span>Personal Info</span>
                </button>
                <button type="button" role="menuitem" className="profile-menu-item" onClick={() => openProfilePanel('settings')}>
                  <Settings size={16} aria-hidden="true" />
                  <span>Account Settings</span>
                </button>
                <div className="profile-menu-separator" role="separator" />
                <button type="button" role="menuitem" className="profile-menu-item" onClick={signOut}>
                  <LogOut size={16} aria-hidden="true" />
                  <span>Sign Out</span>
                </button>
              </div>
            )}
          </div>
        </div>
      </header>
      {children}
      {activeProfilePanel && (
        <div
          className="profile-dialog-backdrop"
          onMouseDown={(event) => {
            if (event.target === event.currentTarget) closeProfilePanel()
          }}
        >
          <section
            className="profile-dialog"
            role="dialog"
            aria-modal="true"
            aria-labelledby="profile-dialog-title"
          >
            <header className="profile-dialog-header">
              <div>
                <p className="profile-dialog-eyebrow">YOUR ACCOUNT</p>
                <h2 id="profile-dialog-title">
                  {activeProfilePanel === 'personal-info' ? 'Personal Info' : 'Account Settings'}
                </h2>
              </div>
              <button
                ref={profileDialogCloseRef}
                type="button"
                className="profile-dialog-close"
                aria-label="Close dialog"
                onClick={closeProfilePanel}
              >
                <X size={18} />
              </button>
            </header>
            {activeProfilePanel === 'personal-info' ? (
              <dl className="profile-details">
                <div>
                  <dt>Name</dt>
                  <dd>{displayName}</dd>
                </div>
                <div>
                  <dt>Email</dt>
                  <dd>{email || 'Email unavailable'}</dd>
                </div>
                <div>
                  <dt>Session status</dt>
                  <dd>{session?.authenticated ? 'Active' : 'Unavailable'}</dd>
                </div>
                <div>
                  <dt>Session persistence</dt>
                  <dd>{session?.rememberMe ? 'Remembered on this device' : 'Current browser session'}</dd>
                </div>
              </dl>
            ) : (
              <p className="profile-settings-placeholder">
                Account settings are not available yet.
              </p>
            )}
            <div className="profile-dialog-footer">
              <button type="button" className="profile-dialog-done" onClick={closeProfilePanel}>Done</button>
            </div>
          </section>
        </div>
      )}
    </div>
  )
}

export default AppShell
