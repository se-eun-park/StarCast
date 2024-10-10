import axios from 'axios'
import { useQuery } from '@tanstack/react-query'

export const getKakaoAddress = async (search: string) => {
  const response = await axios
    .create({
      baseURL: 'https://dapi.kakao.com/',
      headers: {
        Authorization: `KakaoAK ${import.meta.env.VITE_KAKAO_REST_API_KEY}`,
      },
    })
    .get(`v2/local/search/address.json`, {
      params: { query: search },
    })

  return response?.data?.documents
}

export const useKakaoAddressQuery = (search: string) =>
  useQuery({
    queryKey: ['kakaoAddress', search],
    queryFn: () => {
      const response = getKakaoAddress(search)
      return response
    },
    enabled: !!search,
  })
