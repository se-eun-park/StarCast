import { RouterProvider, createBrowserRouter, RouteObject } from 'react-router-dom'
import NotFoundPage from '@pages/NotFoundPage'
import LoginPage from '@pages/LoginPage'
import HomePage from '@pages/HomePage'
import CalendarPage from '@pages/CalendarPage'
import ObservingSpotPage from '@pages/ObservingSpotPage'
import Mypage from '@pages/my/Mypage'
import LocationSettingPage from '@pages/my/LocationSettingPage'
import MyObservationReviewsPage from '@pages/my/MyObservationReviewsPage'
import MyReactionsPage from '@pages/my/MyReactionsPage'
import NicknameEditPage from '@pages/my/NicknameEditPage'
import ProfileEditPage from '@pages/my/ProfileEditPage'
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
        {
          path: 'mypage',
          children: [
            {
              index: true,
              element: <Mypage />,
            },
            {
              path: 'location',
              element: <LocationSettingPage />,
            },
            {
              path: 'review',
              element: <MyObservationReviewsPage />,
            },
            {
              path: 'reaction',
              element: <MyReactionsPage />,
            },
            {
              path: 'nickname',
              element: <NicknameEditPage />,
            },
            {
              path: 'profile',
              element: <ProfileEditPage />,
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
