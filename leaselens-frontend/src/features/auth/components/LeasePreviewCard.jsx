import buildingImage from '../../../assets/Cladire Autentificare.jpg'

function LeasePreviewCard() {
  return (
    <aside className="showcase-panel" aria-label="LeaseLens product preview">
      <img src={buildingImage} alt="Modern commercial glass skyscraper at twilight" />
      <div className="showcase-overlay" aria-hidden="true" />
      <div className="showcase-content">
        <div className="lease-card">
          <header className="lease-card-header">
            <div>
              <span className="eyebrow">Analyzed Lease Agreement</span>
              <h2>Hudson Yards Flagship Retail</h2>
              <p>500 W 33rd St · New York, NY</p>
            </div>
            <span className="risk-badge">
              <span aria-hidden="true" />
              Low Risk · 96
            </span>
          </header>

          <div className="lease-terms">
            <div>
              <span>Extracted Monthly Rent</span>
              <strong className="tabular">$84,500</strong>
            </div>
            <div>
              <span>Lease Term</span>
              <strong className="tabular">7.5 yrs</strong>
            </div>
          </div>

          <div className="trend">
            <div className="trend-header">
              <span>Projected Rent Escalation</span>
              <strong className="tabular">+3.0% / yr</strong>
            </div>
            <svg viewBox="0 0 360 76" role="img" aria-label="Projected rent escalation chart">
              <defs>
                <linearGradient id="authMiniFill" x1="0" y1="0" x2="0" y2="1">
                  <stop offset="0%" stopColor="#0D9488" stopOpacity="0.22" />
                  <stop offset="100%" stopColor="#0D9488" stopOpacity="0" />
                </linearGradient>
              </defs>
              <line x1="0" y1="20" x2="360" y2="20" stroke="#E2E8F0" />
              <line x1="0" y1="48" x2="360" y2="48" stroke="#E2E8F0" />
              <path d="M4 62 C60 58 100 48 160 44 C220 40 280 26 356 14 L356 74 L4 74Z" fill="url(#authMiniFill)" />
              <path d="M4 62 C60 58 100 48 160 44 C220 40 280 26 356 14" fill="none" stroke="#0D9488" strokeWidth="2.5" strokeLinecap="round" />
              <circle cx="356" cy="14" r="4" fill="#0D9488" stroke="#FFFFFF" strokeWidth="2" />
            </svg>
          </div>
        </div>

        <div className="showcase-copy">
          <h2>Instant lease clause extraction &amp; risk scoring</h2>
          <p>Turn dense commercial agreements into structured rent rolls, expiration alerts, and portfolio forecasts in seconds.</p>
        </div>
      </div>
    </aside>
  )
}

export default LeasePreviewCard
