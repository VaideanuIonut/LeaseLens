import { Building2, UsersRound } from 'lucide-react'

function Sparkline({ variant }) {
  if (variant === 'tenants') {
    return (
      <svg className="kpi-sparkline" viewBox="0 0 220 42" preserveAspectRatio="none" aria-hidden="true">
        <defs>
          <linearGradient id="tenantsSpark" x1="0" y1="0" x2="0" y2="1">
            <stop offset="0%" stopColor="#0D9488" stopOpacity="0.18" />
            <stop offset="100%" stopColor="#0D9488" stopOpacity="0" />
          </linearGradient>
        </defs>
        <path d="M0 35 C28 35 32 29 58 31 C82 34 104 25 128 24 C154 23 172 27 220 14 L220 42 L0 42Z" fill="url(#tenantsSpark)" />
        <path d="M0 35 C28 35 32 29 58 31 C82 34 104 25 128 24 C154 23 172 27 220 14" fill="none" stroke="#94A3B8" strokeWidth="1.5" />
      </svg>
    )
  }

  return (
    <svg className="kpi-sparkline" viewBox="0 0 220 42" preserveAspectRatio="none" aria-hidden="true">
      <defs>
        <linearGradient id="rentSpark" x1="0" y1="0" x2="0" y2="1">
          <stop offset="0%" stopColor="#0D9488" stopOpacity="0.14" />
          <stop offset="100%" stopColor="#0D9488" stopOpacity="0" />
        </linearGradient>
      </defs>
      <path d="M0 29 C45 22 70 31 112 28 C158 24 178 24 220 20 L220 42 L0 42Z" fill="url(#rentSpark)" />
      <path d="M0 29 C45 22 70 31 112 28 C158 24 178 24 220 20" fill="none" stroke="#CBD5E1" strokeWidth="1.5" />
    </svg>
  )
}

function KpiCard({ variant }) {
  if (variant === 'occupancy') {
    return (
      <article className="kpi-card occupancy-card">
        <span className="kpi-label occupancy-label">OCCUPANCY RATE</span>
        <strong className="kpi-value occupancy-value tabular">55.96%</strong>
        <span className="occupancy-subtext">5-year avg unit occupancy</span>
        <svg className="occupancy-spark" viewBox="0 0 220 42" preserveAspectRatio="none" aria-hidden="true">
          <path d="M0 38 C44 34 80 31 112 30 C154 28 186 17 220 10 L220 42 L0 42Z" fill="rgba(255,255,255,0.12)" />
          <path d="M0 38 C44 34 80 31 112 30 C154 28 186 17 220 10" fill="none" stroke="rgba(255,255,255,0.34)" strokeWidth="1.5" />
        </svg>
      </article>
    )
  }

  const content = {
    properties: { label: 'TOTAL PROPERTIES', value: '101', subtext: '27 currently active', icon: Building2 },
    tenants: { label: 'ACTIVE TENANTS', value: '642', subtext: '+8 this month', icon: UsersRound },
    rent: { label: 'AVG. MONTHLY COLLECTED RENT (12M)', value: '$318,400', subtext: null, icon: null },
  }[variant]
  const Icon = content.icon

  return (
    <article className="kpi-card">
      <span className="kpi-label">{content.label}</span>
      {Icon && (
        <span className="kpi-icon" aria-hidden="true"><Icon size={17} strokeWidth={1.7} /></span>
      )}
      <strong className="kpi-value tabular">{content.value}</strong>
      {content.subtext && (
        variant === 'tenants'
          ? <span className="kpi-pill">{content.subtext}</span>
          : <span className="kpi-subtext">{content.subtext}</span>
      )}
      {(variant === 'tenants' || variant === 'rent') && <Sparkline variant={variant} />}
    </article>
  )
}

export default KpiCard
