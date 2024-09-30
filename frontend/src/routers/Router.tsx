// import { RouterProvider, createBrowserRouter, RouteObject } from 'react-router-dom'
// import NotFoundPage from '@pages/NotFoundPage'
// import LoginPage from '@pages/LoginPage'
// import HomePage from '@pages/HomePage'
// import CalendarPage from '@pages/CalendarPage'
// import ObservingSpotPage from '@pages/ObservingSpotPage'
// import Mypage from '@pages/Mypage'
// import Layout from '@components/ui/Layout'
// import ReviewListPage from '@pages/review/ReviewListPage'
// import ReviewDetailPage from '@pages/review/ReviewDetailPage'
// import ReviewDetailLayout from '@components/ui/ReviewDetailLayout'
// import CreateReviewPage from '@pages/review/CreateReviewPage'

// const Router = () => {
//   const routes: RouteObject[] = [
//     {
//       path: '/',
//       element: <Layout />,
//       errorElement: <NotFoundPage />,
//       children: [
//         {
//           path: '/login',
//           element: <LoginPage />,
//         },
//         {
//           path: '/home',
//           element: <HomePage />,
//         },
//         {
//           path: '/calendar',
//           element: <CalendarPage />,
//         },
//         {
//           path: '/observing-spot',
//           element: <ObservingSpotPage />,
//         },
//         {
//           path: '/mypage',
//           element: <Mypage />,
//         },
//         {
//           path: '/review',
//           children: [
//             {
//               index: true,
//               element: <ReviewListPage />,
//             },
//             {
//               path: ':id',
//               element: (
//                 <ReviewDetailLayout>
//                   <ReviewDetailPage />
//                 </ReviewDetailLayout>
//               ),
//             },
//             {
//               path: 'new',
//               element: <CreateReviewPage />,
//             },
//           ],
//         },
//       ],
//     },
//   ]

//   const router = createBrowserRouter([...routes])
//   return <RouterProvider router={router} />
// }

// export default Router

import { RouterProvider, createBrowserRouter, RouteObject } from 'react-router-dom'
import { useEffect } from 'react'
import NotFoundPage from '@pages/NotFoundPage'
import LoginPage from '@pages/LoginPage'
import HomePage from '@pages/HomePage'
import CalendarPage from '@pages/CalendarPage'
import ObservingSpotPage from '@pages/ObservingSpotPage'
import Mypage from '@pages/Mypage'
import Layout from '@components/ui/Layout'
import ReviewListPage from '@pages/review/ReviewListPage'
import ReviewDetailPage from '@pages/review/ReviewDetailPage'
import ReviewDetailLayout from '@components/ui/ReviewDetailLayout'
import CreateReviewPage from '@pages/review/CreateReviewPage'
import { useNavigate } from 'react-router-dom'

const RedirectToLogin = () => {
  const navigate = useNavigate()

  useEffect(() => {
    navigate('/login') // 처음 실행 시 '/login'으로 리다이렉트
  }, [navigate])

  return null // 렌더링할 내용 없음
}

const Router = () => {
  const routes: RouteObject[] = [
    {
      path: '/',
      element: <Layout />,
      errorElement: <NotFoundPage />,
      children: [
        {
          index: true, // 기본 경로에 대한 리다이렉트
          element: <RedirectToLogin />,
        },
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
        {
          path: '/review',
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
