import { Produit } from "./produit";

export interface Cart {
  listProduct: { produit: Produit, quantity: number }[];
  total: number;
}
