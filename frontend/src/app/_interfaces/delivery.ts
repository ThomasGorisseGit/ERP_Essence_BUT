import { Stock } from "./stock";

export interface Delivery {
  id: number;
  quantity:number;
  date:string;
  state:string;
  stock:Stock;

}
