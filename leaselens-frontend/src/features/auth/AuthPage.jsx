import AuthForm from './components/AuthForm'
import LeasePreviewCard from './components/LeasePreviewCard'
import './AuthPage.css'

function AuthPage() {
  return (
    <main className="auth-shell">
      <AuthForm />
      <LeasePreviewCard />
    </main>
  )
}

export default AuthPage
