import { ProductService } from 'src/app/_services/product.service';
import { Component } from '@angular/core';
import { Product } from '../_interfaces/product';
import { ClientService } from '../_services/client.service';
import { Client } from '../_interfaces/client';

@Component({
  selector: 'app-bdd',
  templateUrl: './bdd.component.html',
  styleUrls: ['./bdd.component.css']
})
export class BddComponent {
  private selectedPage:string = "Stocks";
  listProductWithStocks: Product[] = []
  listClient : Client[] = []

  getSelectedPage() :string{
    return this.selectedPage;
  }
  setSelectedPage(page:string){
    this.selectedPage = page;
  }


  constructor(
    private productService:ProductService,
    private clientService:ClientService,
    ) {
    this.fetchStocks();
    this.fetchClient();
  }

  fetchStocks(){
    if(this.productService.listProductWithStocks === null ){
      this.productService.ProductContainsAll().subscribe({
        next:(data)=>{
          this.listProductWithStocks = data;
        }
      })
    }else{
      this.listProductWithStocks = this.productService.listProductWithStocks as Product[];
    }
  }

  fetchClient(){
    if(this.clientService.clientList === null){
      this.clientService.getClients().subscribe({next:(data : Client[])=>{this.listClient = data}})
    }else{
      this.listClient = this.clientService.clientList;
    }
  }
}
