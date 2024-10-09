type AstroEventDetail = {
  event: any | null;
}

const AstroEventDetail = ({ event }: AstroEventDetail) => {
  if (!event) {
    return <p>이벤트 없어용</p>;
  }

  return (
    <div>
      <h2>{event.astroTitle || "Astro Event"}</h2>
      <p>{event.astroEvent}</p>
      {event.astroTime && <p>Time: {event.astroTime}</p>}
    </div>
  )
}

export default AstroEventDetail;