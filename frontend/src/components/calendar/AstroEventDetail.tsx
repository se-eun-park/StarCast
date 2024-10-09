type AstroEventDetail = {
  event: any | null;
  isDateSelected: boolean;
}

const AstroEventDetail = ({ event, isDateSelected }: AstroEventDetail) => {
  if (!isDateSelected) {
    return (
      <div className="w-full flex gap-2 py-2 px-4 bg-bg-50/10 rounded-full">
        <p>날짜를 선택하여 이벤트를 확인하세요.</p>
      </div>
    )
  }
  
  if (!event) {
    return (
      <div className="w-full flex gap-2 py-2 px-4 bg-bg-50/10 rounded-full">
        <p>해당 일자에 예정된 이벤트가 없어요.</p>
      </div>
    );
  }

  return (
    <div className="w-full flex gap-2 py-1.5 px-1.5 items-center bg-bg-50/10 rounded-full">
      <span className="bg-bg-50/10 rounded-full w-11 h-11 flex justify-center items-center">{parseInt(event.locdate.slice(6, 8))}일</span>
      <p>{event.astroEvent}</p>
      {event.astroTime && <p className="text-text-tertiary">{event.astroTime}</p>}
    </div>
  )
}

export default AstroEventDetail;