import { Component } from '@angular/core';
import { Product } from 'src/app/_interfaces/product';
import { Produit } from 'src/app/_interfaces/produit';
import { ProductService } from 'src/app/_services/product.service';

@Component({
  selector: 'app-show-inventory',
  templateUrl: './show-inventory.component.html',
  styleUrls: ['./show-inventory.component.css']
})
export class ShowInventoryComponent {
  listProduct: Product[] = [];

  constructor(private productService: ProductService) {

  }
}
