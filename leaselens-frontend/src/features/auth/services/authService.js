const API_URL = (import.meta.env.VITE_API_URL || 'http://localhost:8080').replace(/\/$/, '')

async function post(path, payload) {
  let response

  try {
    response = await fetch(`${API_URL}${path}`, {
      method: 'POST',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify(payload),
    })
  } catch {
    throw new Error('Unable to reach the authentication service.')
  }

  const contentType = response.headers.get('content-type') || ''
  const data = contentType.includes('application/json')
    ? await response.json().catch(() => null)
    : null

  if (!response.ok) {
    const fieldErrors = data?.fieldErrors
    const validationDetails = fieldErrors && typeof fieldErrors === 'object'
      ? Object.entries(fieldErrors)
        .flatMap(([field, messages]) => (Array.isArray(messages) ? messages : [messages])
          .filter((message) => typeof message === 'string' && message.trim())
          .map((message) => `${field}: ${message}`))
      : []

    if (validationDetails.length) {
      throw new Error(validationDetails.join('; '))
    }

    throw new Error(data?.message || data?.error || `Authentication request failed (${response.status})`)
  }

  return data || {}
}

export function login(payload) {
  return post('/api/auth/login', payload)
}

export function register(payload) {
  return post('/api/auth/register', payload)
}
