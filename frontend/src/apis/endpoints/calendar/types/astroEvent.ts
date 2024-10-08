export type AstroEvent = {
  seq: number;
  title: string;
  event: string;
  time: string;
}

export type EventListofTheDay = {
  date: string;
  eventListOfTheDay: AstroEvent[];
}

export type AstroEventResponse = {
  year: number;
  month: number;
  eventListOfTheMonth: EventListofTheDay[];
}