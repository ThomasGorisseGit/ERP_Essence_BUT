import { HttpErrorResponse } from '@angular/common/http';
import { Form, FormControl, FormsModule } from '@angular/forms';
import { ClientService } from 'src/app/_services/client.service';
import { Component, OnChanges, ViewChild } from '@angular/core';
import { Client } from 'src/app/_interfaces/client';
import { DisplayErrorComponent } from 'src/app/_popup/display-error/display-error.component';
import { DisplayPopupComponent } from 'src/app/_popup/display-popup/display-popup.component';
import {
  MatDialog,
  MatDialogRef,
  MatDialogActions,
  MatDialogClose,
  MatDialogTitle,
  MatDialogContent,
} from '@angular/material/dialog';
import { MatButtonModule } from '@angular/material/button';
import { MatInputModule } from '@angular/material/input';
import { MatFormFieldModule } from '@angular/material/form-field';

@Component({
  selector: 'app-connect-client',
  templateUrl: './connect-client.component.html',
  styleUrls: ['./connect-client.component.css'],

})
export class ConnectClientComponent {
  clientList : Client[] = [];
  client_id : FormControl = new FormControl();
  currentClient :Client | null = null;

  selectPlan:boolean = false;
  @ViewChild(DisplayErrorComponent)
  displayError!:DisplayErrorComponent


  @ViewChild(DisplayPopupComponent)
  displayPopup!:DisplayPopupComponent

  constructor(private ClientService: ClientService){
    if(ClientService.clientList === null){
      this.ClientService.getClients().subscribe({
        next:(data)=>{
          this.clientList = data as Client[];
        }
      })
    }
    else {
      this.clientList = this.ClientService.clientList!;
    }


    this.client_id.valueChanges.subscribe({
      next: (data)=>{
        this.currentClient = this.clientList.find((cli)=>cli.id == data) as Client;
        console.log(this.currentClient);

      }
    })
  }
  deleteClient(){


    if(this.currentClient == null){
      //display error
      this.displayError.error = " Impossible de supprimer le client ";
      this.displayError.message = "Veuillez selectionner un client";
      return;
    }
    this.ClientService.delete(this.currentClient!).subscribe({
      next: (data)=>{
        this.displayPopup.image = "./assets/done.png";
        this.displayPopup.reload = true;
        this.displayPopup.text = "Le client a été supprimé";

      },
      error:(err) =>{
        this.displayError.error =" Une erreur est survenue lors de la suppression"

      },
    })
  }

}
