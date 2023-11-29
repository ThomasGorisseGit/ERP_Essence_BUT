import { FormControl, FormGroup } from '@angular/forms';
import { Component, ViewChild } from '@angular/core';
import { Client } from 'src/app/_interfaces/client';
import { ClientService } from 'src/app/_services/client.service';
import { DisplayPopupComponent } from 'src/app/_popup/display-popup/display-popup.component';
import { DisplayErrorComponent } from 'src/app/_popup/display-error/display-error.component';
import { HttpErrorResponse } from '@angular/common/http';

@Component({
  selector: 'app-create-client',
  templateUrl: './create-client.component.html',
  styleUrls: ['./create-client.component.css']
})
export class CreateClientComponent {
  formGroup : FormGroup = new FormGroup({
    lastName:new FormControl(),
    firstName:new FormControl(),
    emailAddress:new FormControl(),
    phoneNumber:new FormControl(),
  })

  @ViewChild(DisplayErrorComponent)
  displayError!: DisplayErrorComponent;
  @ViewChild(DisplayPopupComponent)
  displayPopup!: DisplayPopupComponent;

  constructor(private clientService: ClientService){

  }
  createClient(){
    var cli :Client = this.formGroup.value as Client;
    if(cli.firstName != null && cli.lastName != null && cli.emailAddress != null && cli.phoneNumber != null){

      this.clientService.createClient(cli).subscribe({
        next : (data:Client) =>{
          this.displayPopup.text=data.firstName+" a été ajouté";
          this.displayPopup.reload = true;
          this.displayPopup.image = "./assets/add-user.png"
        },
        error:(err:HttpErrorResponse)=>{
          if(err.status == 409 ){

            this.displayError.error = "Impossible d'ajouter cette personne";
            this.displayError.message = "L'email ou le telephone sont invalides";
          }else{

            this.displayError.error = "Impossible d'ajouter cette personne";
            this.displayError.message = "Erreur lors de l'insertion";
          }
        }
      });
    }else{
      this.displayError.error = "Impossible d'ajouter cette personne";
      this.displayError.message = "Les champs sont vides";
    }

  }
}
