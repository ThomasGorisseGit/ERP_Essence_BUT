import { Product } from './../_interfaces/product';
import { catchError, throwError, Observable, lastValueFrom } from 'rxjs';
import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { ApiURL } from '../_const/const';
import { Produit } from '../_interfaces/produit';
import { Provider } from '../_interfaces/provider';
import { Delivery } from '../_interfaces/delivery';
import { Stock } from '../_interfaces/stock';

@Injectable({
  providedIn: 'root'
})
export class ProductService {
  listProduct: Product[] | null = null;
  listDelivery:Delivery[] | null = null;
  listProductWithoutProvider: Product[] | null = null;
  listStock : Stock[] | null = null;
  listProductWithStocks: Product[] | null = null;

  constructor(private http:HttpClient) {

    this.getProductList();
    this.getDelivries();
    this.getProductAvailableForProvider();
    this.ProductContainsAll();

  }
  getProvider(product:number):Observable<Provider>{
    return this.http.get<Provider>(ApiURL+"/product/getProvider/"+product) as Observable<Provider>;
  }

  getProductList(){
      return this.http.get<Product[]>(ApiURL+"/product/getProducts").pipe((val)=>{
        val.subscribe({
          next:(val)=>{
            if(this.listProduct===null){
              this.listProduct = val;
            }
          }
        });
        return val;
      })
  }
  getProductAvailableForProvider() {
    return this.http.get<Product[]>(ApiURL+"/product/findByProviderIsNull").pipe((req)=>{
      req.subscribe({
        next:(data)=> this.listProductWithoutProvider = data.reverse()

      })
      return req;
    })as Observable<Product[]>;
  }
  addDelivery(productId:number, quantity:number){
    return this.http.post(ApiURL+"/stock/addDelivery/"+productId+"/"+quantity,null);
  }
  getDelivries(){
    return this.http.get<Delivery[]>(ApiURL+"/stock/getDeliveries").pipe((request)=>{
      request.subscribe({
        next: (data)=>{
          this.listDelivery = data;
        }
      })
      return request;
    });
  }
  getStocks(){
    return this.http.get<Stock[]>(ApiURL+"/stock/getStocks").pipe((req)=>{
      req.subscribe({
        next:(data)=>{
          if(this.listStock===null){
            this.listStock = data;
          }
        }
      })
      return req;
    })
  }
  ProductContainsAll(){
    return this.http.get<Product[]>(ApiURL+"/product/ProductWithStocks").pipe((req)=>{
      req.subscribe({
        next:(val:Product[])=>{
            this.listProductWithStocks = val;
        }
      })
      return req;
    })
  }
}
