export interface Incident {
  id: number;
  title: string;
  description?: string;
  date: Date;
  heure: string;
  type: string;
}
