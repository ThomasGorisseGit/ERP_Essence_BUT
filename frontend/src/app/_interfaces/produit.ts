import { Stock } from "./stock";

export interface Produit {

  id: number;
  name: string;
  description?: string;
  price: number;
  stock:Stock;
}
