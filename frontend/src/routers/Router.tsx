import { RouterProvider, createBrowserRouter, RouteObject } from 'react-router-dom'
import NotFoundPage from '@pages/NotFoundPage'
import MainPage from '@pages/MainPage'

const Router = () => {
  const routes: RouteObject[] = [
    {
      path: '/',
      errorElement: <NotFoundPage />,
      children: [
        {
          path: '/main',
          element: <MainPage />,
        },
      ],
    },
  ]

  const router = createBrowserRouter([...routes])
  return <RouterProvider router={router} />
}

export default Router
