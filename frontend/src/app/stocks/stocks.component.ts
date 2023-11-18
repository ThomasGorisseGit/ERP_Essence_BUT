import { Component } from '@angular/core';
import { Reappro } from '../reappro';
import { Router } from '@angular/router';
import { Delivery } from '../_interfaces/delivery';
import { ProductService } from '../_services/product.service';

@Component({
  selector: 'app-stocks',
  templateUrl: './stocks.component.html',
  styleUrls: ['./stocks.component.css']
})
export class StocksComponent {
  listDelivery: Delivery[] = [];
  constructor(public router: Router,private productService:ProductService) {
    if(this.productService.listDelivery===null){
      this.productService.getDelivries().subscribe({
        next : (data)=>{
          this.listDelivery = data;
        }
      })
    }
    else{
      this.listDelivery = this.productService.listDelivery;
    }

  }

  route(): string {
    return this.router.url.slice(8);
  }
}
