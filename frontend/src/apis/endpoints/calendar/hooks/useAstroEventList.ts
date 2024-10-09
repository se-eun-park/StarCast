import { useQuery } from "@tanstack/react-query";
import { publicApi } from "@apis/axios";
import { DefaultResponseType } from "@apis/axios/api.types";
import { AstroEventResponse } from "../types/astroEvent";

const fetchAstroEventList = async (year: number, month: number): Promise<AstroEventResponse> => {
  const { data } = await publicApi.get<DefaultResponseType<AstroEventResponse>>(
    `/api/v1/calendar/astronomical-events?date=${year}-${String(month).padStart(2, '0')}`
  );
  return data.data;
};

export const useAstroEventList = (year: number, month: number) => {
  return useQuery({
    queryKey: ['astroEventList', year, month],
    queryFn: () => fetchAstroEventList(year, month),
  });
};