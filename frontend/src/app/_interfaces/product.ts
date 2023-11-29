import { Provider } from "./provider";
import { Stock } from "./stock";

export interface Product {
  id: number;
  name: string;
  description?: string;
  price: number;
  stock?:Stock;
  provider?:Provider
}
