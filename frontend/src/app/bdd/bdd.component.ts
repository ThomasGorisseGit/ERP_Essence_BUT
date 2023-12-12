import { ProductService } from 'src/app/_services/product.service';
import { Component } from '@angular/core';
import { Product } from '../_interfaces/product';
import { ClientService } from '../_services/client.service';
import { Client } from '../_interfaces/client';
import { ProviderService } from '../_services/provider.service';
import { Fuel } from '../_interfaces/fuel';
import { Provider } from '../_interfaces/provider';

@Component({
  selector: 'app-bdd',
  templateUrl: './bdd.component.html',
  styleUrls: ['./bdd.component.css']
})
export class BddComponent {
  private selectedPage:string = "Stocks";

  listProductWithStocks: Product[] = []
  listClient : Client[] = []
  listFuel : Fuel[] = []
  listProvider:Provider[] = []
  getSelectedPage() :string{
    return this.selectedPage;
  }
  setSelectedPage(page:string){
    this.selectedPage = page;
  }


  constructor(
    private productService:ProductService,
    private clientService:ClientService,
    private providerService:ProviderService
    ) {
    this.fetchStocks();
    this.fetchClient();
    this.fetchFuel();
    this.fetchProvider();
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

  fetchFuel(){
    if(this.providerService.listFuel === null){
      this.providerService.getFuelList().subscribe({
        next:(data)=>this.listFuel = data
      })
    }else{
      this.listFuel = this.providerService.listFuel as Fuel[];
    }
  }

  fetchProvider(){
    if(this.providerService.listProvider === null){
      this.providerService.getProviderList().subscribe({
        next :(data)=>this.listProvider = data
      })
    }
    else{
      this.listProvider = this.providerService.listProvider
    }
  }
}
