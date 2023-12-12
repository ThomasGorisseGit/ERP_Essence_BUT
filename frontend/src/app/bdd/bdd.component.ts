import { ProductService } from 'src/app/_services/product.service';
import { Component } from '@angular/core';
import { Product } from '../_interfaces/product';

@Component({
  selector: 'app-bdd',
  templateUrl: './bdd.component.html',
  styleUrls: ['./bdd.component.css']
})
export class BddComponent {
  private selectedPage:string = "Stocks";
  listProductWithStocks: Product[] = []
  getSelectedPage() :string{
    return this.selectedPage;
  }
  setSelectedPage(page:string){
    this.selectedPage = page;
  }


  constructor(private productService:ProductService) {
    this.fetchStocks();
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
}
