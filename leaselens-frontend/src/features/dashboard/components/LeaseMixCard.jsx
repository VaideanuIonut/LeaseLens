import { Cell, Pie, PieChart, ResponsiveContainer, Tooltip } from 'recharts'

const data = [
  { name: 'Long-Term', detail: '(2+ years)', value: 342, percent: 43, color: '#0D9488' },
  { name: 'Medium-Term', detail: '(1–2 years)', value: 279, percent: 35, color: '#94A3B8' },
  { name: 'Short-Term', detail: '(< 1 year)', value: 175, percent: 22, color: '#334155' },
]

function DonutTooltip({ active, payload }) {
  if (!active || !payload?.length) return null
  const item = payload[0].payload
  return (
    <div className="chart-tooltip">
      <strong>{item.name}</strong>
      <span>{item.value} leases · {item.percent}%</span>
    </div>
  )
}

function LeaseMixCard() {
  return (
    <article className="chart-card lease-card">
      <h2>Lease Duration Mix</h2>
      <div className="lease-mix-content">
        <div className="donut-wrap">
          <ResponsiveContainer width="100%" height="100%">
            <PieChart>
              <Pie data={data} dataKey="value" nameKey="name" cx="50%" cy="50%" innerRadius="62%" outerRadius="88%" startAngle={90} endAngle={-270} stroke="#FFFFFF" strokeWidth={1} paddingAngle={0} animationDuration={850}>
                {data.map((item) => <Cell key={item.name} fill={item.color} />)}
              </Pie>
              <Tooltip
                content={<DonutTooltip />}
                offset={14}
                wrapperStyle={{ zIndex: 20, pointerEvents: 'none' }}
                allowEscapeViewBox={{ x: true, y: true }}
              />
            </PieChart>
          </ResponsiveContainer>
          <div className="donut-center">
            <strong className="tabular">796</strong>
            <span>Leases</span>
          </div>
        </div>
        <div className="lease-legend" aria-label="Lease duration legend">
          {data.map((item) => (
            <div className="lease-legend-item" key={item.name}>
              <i style={{ background: item.color }} />
              <span><strong>{item.name}</strong><small>{item.detail} ({item.percent}%)</small></span>
            </div>
          ))}
        </div>
      </div>
    </article>
  )
}

export default LeaseMixCard
