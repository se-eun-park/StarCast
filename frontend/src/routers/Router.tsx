import { RouterProvider, createBrowserRouter, RouteObject } from 'react-router-dom'
import NotFoundPage from '@pages/NotFoundPage'
import LoginPage from '@pages/LoginPage'
import HomePage from '@pages/HomePage'
import CalendarPage from '@pages/CalendarPage'
import ObservingSpotPage from '@pages/ObservingSpotPage'
import Mypage from '@pages/Mypage'

const Router = () => {
  const routes: RouteObject[] = [
    {
      path: '/',
      errorElement: <NotFoundPage />,
      children: [
        {
          path: '/login',
          element: <LoginPage />,
        },
        {
          path: '/home',
          element: <HomePage />,
        },
        {
          path: '/calendar',
          element: <CalendarPage />,
        },
        {
          path: '/observing-spot',
          element: <ObservingSpotPage />,
        },
        {
          path: '/mypage',
          element: <Mypage />,
        },
      ],
    },
  ]

  const router = createBrowserRouter([...routes])
  return <RouterProvider router={router} />
}

export default Router
