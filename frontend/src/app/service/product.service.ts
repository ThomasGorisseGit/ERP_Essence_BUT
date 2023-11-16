import { Produit } from '../_interfaces/produit';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ProductService {
  listProduct: Produit[] = [];


  constructor(private http: HttpClient) {
    this.listProduct = [];
  }

  getAllItems() {
    return []

  }
}
