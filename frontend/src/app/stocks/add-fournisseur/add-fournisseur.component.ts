import { Provider } from './../../_interfaces/provider';
import { Component, ViewChild } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { Product } from 'src/app/_interfaces/product';
import { DisplayErrorComponent } from 'src/app/_popup/display-error/display-error.component';
import { DisplayPopupComponent } from 'src/app/_popup/display-popup/display-popup.component';
import { ProductService } from 'src/app/_services/product.service';
import { ProviderService } from 'src/app/_services/provider.service';

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

  @ViewChild(DisplayErrorComponent)
  displayError!: DisplayErrorComponent;
  @ViewChild(DisplayPopupComponent)
  displayPopup!: DisplayPopupComponent;


  constructor(private productService:ProductService,private providerService:ProviderService) {
    if(this.productService.listProductWithoutProvider != null){
      this.productProposedList = this.productService.listProductWithoutProvider;
    }else{

      this.productService.getProductAvailableForProvider().subscribe({
        next:(data:Product[])=>{
          this.productProposedList = data.reverse();
        }
      })
    }
   }

   submit(){
    var provider:Provider = {
      id: 0,
      firstName: this.formGroup.value.firstName,
      lastName: this.formGroup.value.lastName,
      siret: this.formGroup.value.siret,
      productList: this.formGroup.value.productProposed
    }
    if(provider.siret == null || provider.firstName == null || provider.lastName == null || provider.productList.length==0)
    {
      this.displayError.error="Veuillez remplir tous les champs"
    }else{
      this.providerService.addProvider(provider).subscribe({
        next:(data)=>{
          this.displayPopup.text="Fournisseur ajouté";
          this.displayPopup.image = "./assets/done.png"
          this.displayPopup.reload = true;
          this.formGroup.reset()
        },
        error:(err)=>{
          if(err.error.status == 409){
            //Conflict == invalid siret
            this.displayError.error="Impossible d'ajouter le fournisseur: "
            this.displayError.message= "Numéro de Siret invalide"
          }
          else{

            this.displayError.error="Impossible d'ajouter le fournisseur: "
            this.displayError.message= "Une erreur est survenue lors de l'insertion"
          }
        }
      })

    }
   }
}
