import { Produit } from "./_interfaces/produit";

export interface Cart {
  listProduct: { produit: Produit, quantity: number }[];
  total: number;
}
