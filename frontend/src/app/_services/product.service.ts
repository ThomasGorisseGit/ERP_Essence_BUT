import { catchError, throwError, Observable } from 'rxjs';
import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { ApiURL } from '../_const/const';
import { Produit } from '../_interfaces/produit';
import { Product } from '../_interfaces/product';

@Injectable({
  providedIn: 'root'
})
export class ProductService {
  listProduct: Product[] = [];
  constructor(private http:HttpClient) { }

  getProductAvailableForProvider() {
    return this.http.get(ApiURL+"/product/findByProviderIsNull") as Observable<Product[]>;
  }

}
