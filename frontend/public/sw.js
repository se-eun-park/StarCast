// const CACHE_NAME = 'StarcastCache'
// const urlsToCache = ['/', '/index.html', '/favicon.ico', '/manifest.json', '/static/js/bundle.js']

// self.addEventListener('install', (event) => {
//   event.waitUntil(
//     caches.open(CACHE_NAME).then((cache) => {
//       console.log('Opened cache')
//       return cache.addAll(urlsToCache)
//     }),
//   )
// })

// self.addEventListener('activate', (event) => {
//   const cacheWhitelist = [CACHE_NAME]

//   event.waitUntil(
//     caches.keys().then((cacheNames) => {
//       return Promise.all(
//         cacheNames.map((cacheName) => {
//           if (!cacheWhitelist.includes(cacheName)) {
//             return caches.delete(cacheName)
//           }
//         }),
//       )
//     }),
//   )
// })

// self.addEventListener('fetch', (event) => {
//   event.respondWith(
//     caches.match(event.request).then((response) => {
//       // 캐시가 있으면 캐시 리소스를 반환
//       if (response) {
//         return response
//       }

//       // 네트워크 요청을 처리하고 캐시에 추가
//       return fetch(event.request).then((networkResponse) => {
//         if (!networkResponse || networkResponse.status !== 200) {
//           return networkResponse
//         }

//         const responseToCache = networkResponse.clone()

//         caches.open(CACHE_NAME).then((cache) => {
//           cache.put(event.request, responseToCache)
//         })

//         return networkResponse
//       })
//     }),
//   )
// })

const CACHE_NAME = 'StarcastCache'
const urlsToCache = ['/', '/index.html', '/favicon.ico', '/manifest.json', '/static/js/bundle.js']

self.addEventListener('install', (event) => {
  event.waitUntil(
    caches.open(CACHE_NAME).then((cache) => {
      console.log('Opened cache')
      return cache.addAll(urlsToCache)
    }),
  )
})

self.addEventListener('activate', (event) => {
  const cacheWhitelist = [CACHE_NAME]

  event.waitUntil(
    caches.keys().then((cacheNames) => {
      return Promise.all(
        cacheNames.map((cacheName) => {
          if (!cacheWhitelist.includes(cacheName)) {
            return caches.delete(cacheName)
          }
        }),
      )
    }),
  )
})

self.addEventListener('fetch', (event) => {
  event.respondWith(
    fetch(event.request)
      .then((networkResponse) => {
        // 네트워크 요청을 처리하고 캐시에 추가
        if (!networkResponse || networkResponse.status !== 200) {
          return networkResponse
        }

        const responseToCache = networkResponse.clone()

        caches.open(CACHE_NAME).then((cache) => {
          cache.put(event.request, responseToCache)
        })

        return networkResponse
      })
      .catch(() => {
        // 네트워크 요청이 실패하면 캐시에서 리소스를 반환
        return caches.match(event.request)
      }),
  )
})

// const CACHE_NAME = 'StarcastCache-v3' // 캐시 버전 변경
// const urlsToCache = ['/', '/index.html', '/favicon.ico', '/manifest.json', '/static/js/bundle.js']

// // 서비스 워커 설치 이벤트
// self.addEventListener('install', (event) => {
//   self.skipWaiting() // 새 서비스 워커를 즉시 활성화
//   event.waitUntil(
//     caches.open(CACHE_NAME).then((cache) => {
//       console.log('Opened cache')
//       return cache.addAll(urlsToCache)
//     }),
//   )
// })

// // 서비스 워커 활성화 이벤트 (이전 캐시 삭제)
// self.addEventListener('activate', (event) => {
//   const cacheWhitelist = [CACHE_NAME]

//   event.waitUntil(
//     caches.keys().then((cacheNames) => {
//       return Promise.all(
//         cacheNames.map((cacheName) => {
//           if (!cacheWhitelist.includes(cacheName)) {
//             console.log(`Deleting cache: ${cacheName}`)
//             return caches.delete(cacheName)
//           }
//         }),
//       )
//     }),
//   )
//   return self.clients.claim() // 활성화된 서비스 워커가 즉시 페이지 제어
// })

// // 캐시 및 네트워크 요청 처리 (stale-while-revalidate)
// self.addEventListener('fetch', (event) => {
//   event.respondWith(
//     caches.open(CACHE_NAME).then((cache) => {
//       return fetch(event.request)
//         .then((networkResponse) => {
//           // 네트워크 응답 성공 시 캐시에 저장
//           if (networkResponse && networkResponse.status === 200) {
//             cache.put(event.request, networkResponse.clone())
//           }
//           return networkResponse
//         })
//         .catch(() => {
//           // 네트워크 요청 실패 시 캐시에서 리소스를 가져옴
//           return cache.match(event.request)
//         })
//     }),
//   )
// })
