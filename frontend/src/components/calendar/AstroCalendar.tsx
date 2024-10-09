import { useState } from 'react'
// import { useAstroEventList } from "@apis/endpoints/calendar/hooks/useAstroEventList";
import { AstroEventsDummy } from '@dummy/astroEventsDummy'
import Calendar from "react-calendar";
import 'react-calendar/dist/Calendar.css';
import "./AstroCalendar.css";
import { StarIconCal } from "@assets/svg";

import AstroEventDetail from "./AstroEventDetail";

type ValuePiece = Date | null
type Value = ValuePiece | [ValuePiece, ValuePiece]

const AstroCalendar = () => {
  const [value, onChange] = useState<Value>(new Date());
  const [selectedEvent, setSelectedEvent] = useState<any | null>(null);
  const [monthlyTip, setMonthlyTip] = useState<any | null>(null);
  
  const handleClick = (value: Date) => {
    const localeString = value.toLocaleDateString('ko-KR');
    const parts = localeString.split('.').map(part => part.trim());
    const year = parts[0];
    const month = parts[1].padStart(2, '0'); 
    const day = parts[2].padStart(2, '0');   
    const formattedDate = `${year}${month}${day}`;

    const currentMonth = formattedDate.slice(0, 6);
    const events = AstroEventsDummy[currentMonth] || [];
    const eventForDate = events.find(event => event.locdate === formattedDate);

    setSelectedEvent(eventForDate || null);
  }

  const handleMonthChange = ({ activeStartDate }: { activeStartDate: Date | null }) => {
    if (!activeStartDate) return;

    const year = activeStartDate.getFullYear();
    const month = (activeStartDate.getMonth() + 1).toString().padStart(2, '0');
    const formattedMonth = `${year}${month}`;

    const events = AstroEventsDummy[formattedMonth] || [];
    const monthTip = events.find(event => event.locdate === formattedMonth);

    setMonthlyTip(monthTip || null);
  };

  const tileClassName = ({ date }: { date: Date }) => {
    const today = new Date()
    const in7Days = new Date()
    in7Days.setDate(today.getDate() + 6)

    today.setHours(0, 0, 0, 0)
    in7Days.setHours(0, 0, 0, 0)

    if (date.getTime() === today.getTime()) {
      return 'highlight-start'
    } else if (date.getTime() === in7Days.getTime()) {
      return 'highlight-end'
    } else if (date > today && date < in7Days) {
      return 'highlight'
    }
    return ''
  }

  const tileContent = ({ date }: { date: Date }) => {
    const kstOffset = 9 * 60
    const formattedDate = new Date(date.getTime() + kstOffset * 60 * 1000)
    formattedDate.setUTCHours(0, 0, 0, 0)

    const formattedString = formattedDate.toISOString().split('T')[0].replace(/-/g, '')
    const currentMonth = formattedString.slice(0, 6)
    const events = AstroEventsDummy[currentMonth] || []
    const eventForDate = events.find((event) => event.locdate === formattedString)

    if (eventForDate) {
      return (
        <div className='icon-container'>
          <StarIconCal className='star-icon-cal' />
        </div>
      )
    }
    return null
  }

  return (
    <div>
      <Calendar
        onChange={onChange}
        value={value}
        locale='ko'
        formatDay={(_, date) => date.toLocaleString('en', { day: 'numeric' })}
        calendarType='gregory'
        navigationLabel={({ date, locale }) =>
          `${date.getFullYear()}년 ${date.toLocaleString(locale, { month: 'long' })}`
        }
        tileClassName={tileClassName}
        tileContent={tileContent}
        minDetail="month"
        onClickDay={handleClick}
        onActiveStartDateChange={handleMonthChange} 
      />
      {monthlyTip && (
        <div className="monthly-tip">
          <h3>Monthly Tip</h3>
          <p>{monthlyTip.astroEvent}</p>
        </div>
      )}
      <AstroEventDetail event={selectedEvent} />
    </div>
  )
}

export default AstroCalendar
