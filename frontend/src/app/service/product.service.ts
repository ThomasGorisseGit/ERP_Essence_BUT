import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Produit } from '../produit';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ProductService {
  listProduct: Produit[] = [];


  constructor(private http: HttpClient) {
    this.http.get('http://64.225.109.223/backend/api/items.php').subscribe((data: any) => {
      this.listProduct = data;
    });
  }

  getAllItems() {
    return this.http.get('http://64.225.109.223/backend/api/items.php');

  }
}
