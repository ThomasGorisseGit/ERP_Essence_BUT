import { Component, ViewChild } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { Product } from 'src/app/_interfaces/product';
import { Provider } from 'src/app/_interfaces/provider';
import { Stock } from 'src/app/_interfaces/stock';
import { DisplayErrorComponent } from 'src/app/_popup/display-error/display-error.component';
import { DisplayPopupComponent } from 'src/app/_popup/display-popup/display-popup.component';
import { ProductService } from 'src/app/_services/product.service';
import { ProviderService } from 'src/app/_services/provider.service';

@Component({
  selector: 'app-add-reappro',
  templateUrl: './add-reappro.component.html',
  styleUrls: ['./add-reappro.component.css']
})
export class AddReapproComponent {
  formGroup:FormGroup = new FormGroup({
    product: new FormControl(),
    fournisseur: new FormControl(),
    quantity: new FormControl()
  });

  productProposedList: Product[] = [];
  providerList: Provider[] = [];

  @ViewChild(DisplayErrorComponent)
  displayError!: DisplayErrorComponent;
  @ViewChild(DisplayPopupComponent)
  displayPopup!: DisplayPopupComponent;


  constructor(private productService:ProductService,private providerService:ProviderService) {

    this.defaultValues();
    this.handleFormGroupChanges()

  }


  protected defaultValues(){

    // For Products :
    if(this.productService.listProduct === null){
      this.productService.getProductList().subscribe({
        next:(data)=>{
          this.productProposedList = data;
        }
      });
    }else{
      this.productProposedList = this.productService.listProduct;
    }


    // For Provider :
    if(this.providerService.listProviderProduct === null){
      this.providerService.getProvidersProductList().subscribe({
        next:(data:Provider[])=>{
          this.providerList = data;
        }
      });
    }else{
      this.providerList = this.providerService.listProviderProduct;
    }
  }


  private handleFormGroupChanges(){
    this.formGroup.valueChanges.subscribe({
      next:(value)=>{

        if(value["fournisseur"] != null){
          this.providerService.getProductList(value["fournisseur"]).subscribe({
            next : (value : Product[])=> {
              this.productProposedList = value;

            },
          })
        }
        if(value["product"] != null){
          this.productService.getProvider(value["product"]).subscribe({
            next: (data)=>{
              this.providerList = [data];

            }
          })
        }
      }
    })
  }
  getTotal(){
    let qte :number = 0;
    if(this.formGroup.value.quantity != null)
    {
      qte = this.formGroup.value.quantity;
    }
    return (qte * this.getProduct().price).toFixed(2);
  }
  private getProduct():Product{
    var p :Product = {price:0} as Product;
    this.productProposedList.forEach((data)=>{
      if(data.id == this.formGroup.value.product){
        p = data;
      }
    })
    return p;
  }
  submit(){
    if(this.getTotal()=="0.00"){
      console.log("invalid");
    }
    this.productService.addDelivery(this.formGroup.value.product, this.formGroup.value.quantity).subscribe({
      next:(data)=>{
        this.displayPopup.image = "./assets/done.png";
        this.displayPopup.reload = true;
        this.displayPopup.text = "Réapprovisionnement ajouté avec succès"
      },
      error:(err)=> {
        this.displayError.error="Impossible d'ajouter le réapprovisonnement: "
        this.displayError.message= "Une erreur est survenue lors de l'insertion"
      },
    });
  }
}
