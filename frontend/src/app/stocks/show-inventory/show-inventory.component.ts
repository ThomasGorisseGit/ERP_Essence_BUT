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
  listProduct: Product[] = [];
  public listStocks:Stock[] = [];
  constructor(private productService: ProductService) {
    console.log("here");

    if(productService.listStock === null){
      this.productService.getStocks().subscribe({
        next : (data)=>{
          console.log(data);

          this.listStocks = data;
        }
      })
    }else{
      this.listStocks = this.productService.listStock as Stock[];
    }
  }



}
