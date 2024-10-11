'use client'

import { QueryClientProvider, QueryClient } from '@tanstack/react-query'
import { PropsWithChildren } from 'react'
import { CookiesProvider } from 'react-cookie'

const Provider = ({ children }: PropsWithChildren) => {
  const queryClient = new QueryClient({
    defaultOptions: {
      queries: {
        retry: 2,
        staleTime: 5000,
        refetchOnMount: true,
        refetchOnWindowFocus: true,
        refetchOnReconnect: true,
        refetchInterval: 5000,
        refetchIntervalInBackground: false,
      },
      mutations: {
        retry: 2,
        retryDelay: (attemptIndex) => attemptIndex * 1000,
      },
    },
  })

  return (
    <QueryClientProvider client={queryClient}>
      <CookiesProvider>{children}</CookiesProvider>
    </QueryClientProvider>
  )
}

export default Provider
