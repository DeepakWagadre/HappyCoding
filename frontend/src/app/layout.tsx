import type { Metadata } from 'next'
import { Inter } from 'next/font/google'
import './globals.css'
import { Providers } from './providers'

const inter = Inter({ subsets: ['latin'] })

export const metadata: Metadata = {
    title: 'Property AI - Intelligent Property Discovery in Bangalore',
    description: 'Find your perfect property in Bangalore using AI-powered search. Get transparent recommendations with explainable scoring.',
    keywords: 'bangalore property, real estate AI, property search, apartment bangalore, villa bangalore',
    authors: [{ name: 'Property AI Team' }],
    viewport: 'width=device-width, initial-scale=1',
    themeColor: '#0ea5e9',
}

export default function RootLayout({
    children,
}: {
    children: React.ReactNode
}) {
    return (
        <html lang="en">
            <head>
                <link rel="icon" href="/favicon.ico" />
            </head>
            <body className={inter.className}>
                <Providers>
                    {children}
                </Providers>
            </body>
        </html>
    )
}
