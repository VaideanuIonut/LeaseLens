import { Bar, BarChart, CartesianGrid, LabelList, ResponsiveContainer, Tooltip, XAxis, YAxis } from 'recharts'

const data = [
  { name: 'Apex Tower', value: 84.2 },
  { name: 'Northgate Logistics Hub', value: 68.4 },
  { name: 'Harbor Plaza Retail', value: 51.9 },
  { name: 'Midtown Commerce Center', value: 57.9 },
  { name: 'Bayside Medical Pavilion', value: 51.4 },
]

function RevenueTooltip({ active, payload, label }) {
  if (!active || !payload?.length) return null
  return (
    <div className="chart-tooltip">
      <strong>{label}</strong>
      <span>${payload[0].value.toFixed(1)}K monthly rent</span>
    </div>
  )
}

function RevenueChartCard() {
  return (
    <article className="chart-card revenue-card">
      <h2>Top Revenue-Generating Properties</h2>
      <div className="revenue-chart-wrap">
        <ResponsiveContainer width="100%" height="100%">
          <BarChart data={data} layout="vertical" margin={{ top: 18, right: 24, bottom: 8, left: -18 }} barCategoryGap="26%">
            <CartesianGrid stroke="#E2E8F0" strokeDasharray="3 3" horizontal={false} />
            <XAxis type="number" domain={[0, 100]} ticks={[0, 20, 40, 60, 80]} tickFormatter={(value) => value === 0 ? '$0' : `$${value}K`} tick={{ fill: '#334155', fontSize: 9.5 }} axisLine={{ stroke: '#94A3B8' }} tickLine={false} />
            <YAxis type="category" dataKey="name" width={96} tick={{ fill: '#334155', fontSize: 9.5, textAnchor: 'end' }} axisLine={false} tickLine={false} />
            <Tooltip content={<RevenueTooltip />} cursor={{ fill: '#F8FAFC' }} />
            <Bar dataKey="value" radius={[0, 3, 3, 0]} isAnimationActive animationDuration={900} shape={(props) => {
              const { x, y, width, height, index } = props
              return <rect x={x} y={y} width={width} height={height} rx={3} fill={index % 2 === 0 ? '#0D9488' : '#334155'} />
            }}>
              <LabelList dataKey="value" position="right" formatter={(value) => `$${value.toFixed(1)}K`} style={{ fill: '#334155', fontSize: 9.5, fontWeight: 500 }} />
            </Bar>
          </BarChart>
        </ResponsiveContainer>
      </div>
      <div className="axis-caption">Monthly Rent Total Portfolio</div>
    </article>
  )
}

export default RevenueChartCard
