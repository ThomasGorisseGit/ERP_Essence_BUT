import { Component } from '@angular/core';
import { Produit } from 'src/app/_interfaces/produit';
import { ProductService } from 'src/app/service/product.service';

@Component({
  selector: 'app-show-inventory',
  templateUrl: './show-inventory.component.html',
  styleUrls: ['./show-inventory.component.css']
})
export class ShowInventoryComponent {
  listProduct: Produit[] = [];

  constructor(private productService: ProductService) {

    this.productService.getAllItems();

  }
}
