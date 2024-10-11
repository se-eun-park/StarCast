import { StarIconCal } from "@assets/svg";

type AstroEventDetail = {
  event: any[] | null;
  isDateSelected: boolean;
  day: string | null;
}

const AstroEventDetail = ({ event, isDateSelected, day }: AstroEventDetail) => {
  if (!isDateSelected) {
    return (
      <div className="w-full flex items-center gap-2 py-1.5 px-1.5 bg-bg-50/10 rounded-full">
        <div className="w-10 h-10 flex items-center justify-center rounded-full bg-bg-50/10">
          <StarIconCal className="fill-bg-50 w-6 h-6"/>
        </div>
        <p className="text-text-tertiary text-sm">날짜를 선택해보세요.</p>
      </div>
    )
  }
  
  if (!event || event.length === 0) {
    return (
      <div className="w-full flex items-center gap-2 py-1.5 px-1.5 bg-bg-50/10 rounded-full">
        <span className="bg-bg-50/10 rounded-full w-10 h-10 flex justify-center items-center text-sm font-semibold">{day ? `${parseInt(day.slice(6, 8))}일` : '날짜 없음'}</span>
        <p className="text-text-secondary text-sm">해당 일자에 예정된 천문 현상이 없어요.</p>
      </div>
    );
  }

  return (
    <div className="w-full flex flex-col gap-2">
      {event.map((e, index) => (
        <div key={index} className="w-full flex gap-2 py-1.5 px-1.5 items-center bg-bg-50/10 rounded-full">
          <span className="bg-bg-50/10 rounded-full w-10 h-10 flex justify-center items-center text-sm font-semibold">{parseInt(e.locdate.slice(6, 8))}일</span>
          <p className="text-sm font-medium">{e.astroEvent}</p>
          {e.astroTime && <p className="text-text-tertiary text-sm">{e.astroTime}</p>}
        </div>
      ))}
    </div>
  )
}

export default AstroEventDetail;