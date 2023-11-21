import { Delivery } from "./delivery";
import { Product } from "./product";

export interface Stock {
  id:number;
  quantity:number;
  date:string;
  product:Product
  delivery:Delivery[]
}
