import { Provider } from './../../_interfaces/provider';
import { Component } from '@angular/core';
import { Product } from 'src/app/_interfaces/product';
import { Produit } from 'src/app/_interfaces/produit';
import { Stock } from 'src/app/_interfaces/stock';
import { ProductService } from 'src/app/_services/product.service';

@Component({
  selector: 'app-show-inventory',
  templateUrl: './show-inventory.component.html',
  styleUrls: ['./show-inventory.component.css']
})
export class ShowInventoryComponent {
  listProductWithStocks:Product[] = []
  constructor(private productService: ProductService) {
    if(productService.listProductWithStocks === null ){
      this.productService.ProductContainsAll().subscribe({
        next:(data)=>{
          this.listProductWithStocks = data;
        }
      })
    }else{
      this.listProductWithStocks = this.productService.listProductWithStocks as Product[];
    }
  }



}
