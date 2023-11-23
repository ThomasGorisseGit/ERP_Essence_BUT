import { Product } from "./product";

export interface Cart {
  listProduct: { product: Product, quantity: number }[];

  total: number;
}
