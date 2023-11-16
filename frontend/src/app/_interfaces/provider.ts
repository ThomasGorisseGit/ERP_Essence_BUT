import { Product } from "./product";

export interface Provider {
  id:number;
  firstName:string;
  lastName:string;
  siret:string;
  productList:Product[];
}
