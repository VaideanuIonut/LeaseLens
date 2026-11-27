import { Area, AreaChart, CartesianGrid, Line, ResponsiveContainer, Tooltip, XAxis, YAxis } from 'recharts'

const data = [
  { month: "Jan '26", contracted: 2.72, collected: 2.12 },
  { month: "Feb '26", contracted: 2.82, collected: 2.18 },
  { month: "Mar '26", contracted: 2.98, collected: 2.36 },
  { month: "Apr '26", contracted: 3.14, collected: 2.48 },
  { month: "May '26", contracted: 3.28, collected: 2.62 },
  { month: "Jun '26", contracted: 3.42, collected: 2.74 },
  { month: "Jul '26", contracted: 3.54, collected: 2.86 },
  { month: "Aug '26", contracted: 3.70, collected: 3.04 },
  { month: "Sep '26", contracted: 3.84, collected: 3.14 },
  { month: "Oct '26", contracted: 4.02, collected: 3.32 },
  { month: "Nov '26", contracted: 4.28, collected: 3.58 },
  { month: "Dec '26", contracted: 4.48, collected: 3.92 },
]

function formatMonth(month) {
  return month.split(' ')[0]
}

function ChartTooltip({ active, payload, label }) {
  if (!active || !payload?.length) return null
  return (
    <div className="chart-tooltip">
      <span>{formatMonth(label)}</span>
      {payload.map((item) => (
        <strong key={item.dataKey} style={{ color: item.dataKey === 'contracted' ? '#334155' : '#0D9488' }}>
          {item.name}: ${item.value.toFixed(2)}M
        </strong>
      ))}
    </div>
  )
}

function RentChartCard() {
  return (
    <article className="chart-card rent-chart-card">
      <h2>Contracted vs. Collected Rent (Last 12 Months)</h2>
      <div className="rent-chart-wrap">
        <ResponsiveContainer width="100%" height="100%">
          <AreaChart data={data} margin={{ top: 12, right: 14, bottom: 0, left: 0 }}>
            <defs>
              <linearGradient id="collectedFill" x1="0" y1="0" x2="0" y2="1">
                <stop offset="0%" stopColor="#0D9488" stopOpacity="0.82" />
                <stop offset="100%" stopColor="#0D9488" stopOpacity="0.18" />
              </linearGradient>
            </defs>
            <CartesianGrid stroke="#E2E8F0" strokeDasharray="3 3" vertical />
            <XAxis dataKey="month" tickFormatter={formatMonth} tick={{ fill: '#334155', fontSize: 9 }} axisLine={{ stroke: '#94A3B8' }} tickLine={false} interval={0} />
            <YAxis domain={[0, 5]} ticks={[0, 1, 2, 3, 4, 5]} tickFormatter={(value) => value === 0 ? '$0' : `$${value.toFixed(1)}M`} tick={{ fill: '#334155', fontSize: 10 }} axisLine={{ stroke: '#94A3B8' }} tickLine={false} width={50} />
            <Tooltip content={<ChartTooltip />} cursor={{ stroke: '#94A3B8', strokeDasharray: '3 3' }} />
            <Area type="monotone" dataKey="collected" name="Collected Rent" fill="url(#collectedFill)" stroke="#0D9488" strokeWidth={1.5} animationDuration={900} />
            <Line type="monotone" dataKey="contracted" name="Contracted Rent" stroke="#334155" strokeWidth={2} dot={false} activeDot={{ r: 4, fill: '#334155', stroke: '#fff', strokeWidth: 2 }} animationDuration={900} />
          </AreaChart>
        </ResponsiveContainer>
      </div>
      <div className="chart-legend rent-legend">
        <span><i className="legend-dot contracted" />Contracted Rent (Base Rent + Escalations)</span>
        <span><i className="legend-dot collected" />Collected Rent</span>
      </div>
    </article>
  )
}

export default RentChartCard
