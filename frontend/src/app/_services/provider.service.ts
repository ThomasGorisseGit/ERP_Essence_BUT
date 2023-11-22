import { Provider } from './../_interfaces/provider';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { ApiURL } from '../_const/const';
import { Product } from '../_interfaces/product';
import { Observable } from 'rxjs';
import { Fuel } from '../_interfaces/fuel';

@Injectable({
  providedIn: 'root'
})
export class ProviderService {
  listProviderProduct:Provider[] | null = null;
  listProviderFuel:Provider[] | null = null;

  constructor(private http:HttpClient) {
    this.getProvidersProductList()
    this.getProviderFuel();
   }

   getProviderFuel():Observable<Provider[]>{
    return this.http.get<Provider[]>(ApiURL + "/provider/getProvidersFuelList").pipe((request) => {
      request.subscribe({
        next: (data: Provider[]) => {
          this.listProviderFuel = data as Provider[];
        }
      });
      return request;
    }) as Observable<Provider[]>;
  }


  getProvidersProductList(){
    return this.http.get<Provider[]>(ApiURL+"/provider/getProvidersProductList").pipe((val)=>{
      val.subscribe({
        next:(val)=>{

          if(this.listProviderProduct===null){
            this.listProviderProduct = val;
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

  getFuelList(){
    return this.http.get<Fuel[]>(ApiURL+"/fuel/getFuels");
  }
  addFuelQte(id:number,quantity:number){
    return this.http.post<Fuel>(ApiURL+"/fuel/addQte/"+id,quantity)
  }
}
