import { Provider } from './../_interfaces/provider';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { ApiURL } from '../_const/const';
import { Product } from '../_interfaces/product';

@Injectable({
  providedIn: 'root'
})
export class ProviderService {
  listProvider:Provider[] | null = null;

  constructor(private http:HttpClient) {
    this.getProviderList()
   }
   getProviderList(){
    return this.http.get<Provider[]>(ApiURL+"/provider/getProviders").pipe((val)=>{
      val.subscribe({
        next:(val)=>{

          if(this.listProvider===null){
            this.listProvider = val;
          }
        }
      });
      return val;
    })
   }

  addProvider(provider:Provider){
    return this.http.post(ApiURL+"/provider/add",provider);
  }
  getProductList(providerId:string){
    return this.http.get<Product[]>(ApiURL+"/provider/getProductList/"+providerId)
  }
}
