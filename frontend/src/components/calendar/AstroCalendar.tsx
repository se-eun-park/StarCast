import { useState } from "react";
// import { useAstroEventList } from "@apis/endpoints/calendar/hooks/useAstroEventList";
import { AstroEventsDummy } from '@dummy/astroEventsDummy'
import Calendar from "react-calendar";
import 'react-calendar/dist/Calendar.css';
import "./AstroCalendar.css";
import { StarIconCal } from "@assets/svg";

type ValuePiece = Date | null;
type Value = ValuePiece | [ValuePiece, ValuePiece];

const AstroCalendar = () => {
  const [value, onChange] = useState<Value>(new Date());

  const tileClassName = ({ date }: { date: Date }) => {
    const today = new Date();
    const in7Days = new Date();
    in7Days.setDate(today.getDate() + 6);
  
    today.setHours(0, 0, 0, 0);
    in7Days.setHours(0, 0, 0, 0);
  
    if (date.getTime() === today.getTime()) {
      return 'highlight-start';
    } else if (date.getTime() === in7Days.getTime()) {
      return 'highlight-end';
    } else if (date > today && date < in7Days) {
      return 'highlight';
    }
    return '';
  };
  
  const tileContent = ({ date }: { date: Date }) => {
    const kstOffset = 9 * 60;
    const formattedDate = new Date(date.getTime() + kstOffset * 60 * 1000);
    formattedDate.setUTCHours(0, 0, 0, 0);

    const formattedString = formattedDate.toISOString().split('T')[0].replace(/-/g, '');
    const currentMonth = formattedString.slice(0, 6);
    const events = AstroEventsDummy[currentMonth] || [];
    const eventForDate = events.find(event => event.locdate === formattedString);

    if (eventForDate) {
        return (
          <div className="icon-container">
          <StarIconCal className="star-icon-cal" />
        </div>
        );
      } 
    return null;
  };

  return (
    <div>
      <Calendar 
        onChange={onChange} 
        value={value}
        locale="ko"
        formatDay={(_, date) => date.toLocaleString('en', { day: 'numeric' })}
        calendarType="gregory"
        navigationLabel={({ date, locale }) => 
          `${date.getFullYear()}년 ${date.toLocaleString(locale, { month: 'long' })}`
        }
        tileClassName={tileClassName}
        tileContent={tileContent}
        minDetail="month"
      />
    </div>
  );
};

export default AstroCalendar;
