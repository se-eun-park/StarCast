import { RouterProvider, createBrowserRouter, RouteObject, Navigate } from 'react-router-dom'
import NotFoundPage from '@pages/NotFoundPage'
import LoginPage from '@pages/LoginPage'
import HomePage from '@pages/HomePage'
import CalendarPage from '@pages/CalendarPage'
import ObservingSpotPage from '@pages/ObservingSpotPage'
import Mypage from '@pages/Mypage'
import Layout from '@components/layout/Layout'
import ReviewListPage from '@pages/review/ReviewListPage'
import ReviewDetailPage from '@pages/review/ReviewDetailPage'
import ReviewDetailLayout from '@components/layout/ReviewDetailLayout'
import CreateReviewPage from '@pages/review/CreateReviewPage'
import NotificationPage from '@pages/NotificationPage'

const Router = () => {
  const routes: RouteObject[] = [
    {
      path: '/',
      element: <Layout />,
      errorElement: <NotFoundPage />,
      children: [
        {
          index: true,
          element: <Navigate to='/login' />, // 기본 경로로 /login으로 리디렉션
        },
        {
          path: 'login',
          element: <LoginPage />,
        },
        {
          path: 'home',
          element: <HomePage />,
        },
        {
          path: 'calendar',
          element: <CalendarPage />,
        },
        {
          path: 'observing-spot',
          element: <ObservingSpotPage />,
        },
        {
          path: 'mypage',
          element: <Mypage />,
        },
        {
          path: 'notification',
          element: <NotificationPage />,
        },
        {
          path: 'review',
          children: [
            {
              index: true,
              element: <ReviewListPage />,
            },
            {
              path: ':id',
              element: (
                <ReviewDetailLayout>
                  <ReviewDetailPage />
                </ReviewDetailLayout>
              ),
            },
            {
              path: 'new',
              element: <CreateReviewPage />,
            },
          ],
        },
      ],
    },
  ]

  const router = createBrowserRouter([...routes])
  return <RouterProvider router={router} />
}

export default Router
