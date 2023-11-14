export interface Incident {
  id: number;
  title: string;
  description?: string;
  date: string |Date;
  image:string;
  solution?:string;
}
