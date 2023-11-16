import { Component } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { Product } from 'src/app/_interfaces/product';
import { ProductService } from 'src/app/_services/product.service';

@Component({
  selector: 'app-add-fournisseur',
  templateUrl: './add-fournisseur.component.html',
  styleUrls: ['./add-fournisseur.component.css']
})
export class AddFournisseurComponent {
  formGroup= new FormGroup({
    firstName:new FormControl(),
    lastName : new FormControl(),
    siret : new FormControl(),
    productProposed: new FormControl()
  })
  productProposedList: Product[] = [];


  constructor(private productService:ProductService) {
    this.productService.getProductAvailableForProvider().subscribe({
      next:(data:Product[])=>{
        this.productProposedList = data.reverse();
      }
    })
   }

   submit(){
    console.log(this.formGroup.value);

   }
}
