import AppShell from './components/AppShell'
import KpiCard from './components/KpiCard'
import RentChartCard from './components/RentChartCard'
import LeaseMixCard from './components/LeaseMixCard'
import RevenueChartCard from './components/RevenueChartCard'
import './OverviewPage.css'

function OverviewPage() {
  return (
    <AppShell>
      <main className="overview-main">
        <section className="hero-banner" aria-label="Portfolio overview">
          <div className="hero-overlay" aria-hidden="true" />
          <div className="hero-content">
            <div>
              <h1>A Clearer View of What You Own.</h1>
              <p>One always-current view of the assets, tenants, and value across your portfolio.</p>
            </div>
            <div className="hero-actions">
              <button type="button" className="hero-button hero-button-light">Upload Lease PDF</button>
              <button type="button" className="hero-button hero-button-primary">+ Add Contract</button>
            </div>
          </div>
        </section>

        <section className="kpi-grid" aria-label="Portfolio key metrics">
          <KpiCard variant="properties" />
          <KpiCard variant="tenants" />
          <KpiCard variant="rent" />
          <KpiCard variant="occupancy" />
        </section>

        <section className="chart-grid" aria-label="Portfolio charts">
          <RentChartCard />
          <LeaseMixCard />
          <RevenueChartCard />
        </section>
      </main>
    </AppShell>
  )
}

export default OverviewPage
