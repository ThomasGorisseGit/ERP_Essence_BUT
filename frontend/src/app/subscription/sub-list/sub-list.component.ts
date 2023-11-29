import { DisplayPopupComponent } from './../../_popup/display-popup/display-popup.component';
import { DisplayErrorComponent } from './../../_popup/display-error/display-error.component';
import { ClientService } from './../../_services/client.service';
import { Component, Input, ViewChild } from '@angular/core';
import { Client } from 'src/app/_interfaces/client';
import { Sub } from 'src/app/_interfaces/sub';

@Component({
  selector: 'app-sub-list',
  templateUrl: './sub-list.component.html',
  styleUrls: ['./sub-list.component.css']
})
export class SubListComponent {

  @Input() client!:Client

  subList : Sub[] = []

  current_plan : Sub | undefined = undefined;

  @ViewChild(DisplayErrorComponent)
  displayError!:DisplayErrorComponent;
  @ViewChild(DisplayPopupComponent)
  displayPopup!:DisplayPopupComponent;


  constructor(private clientService : ClientService){

    if(this.clientService.subscriptionList === null ){
      this.clientService.getSubscriptionList().subscribe({
        next:(data)=>{
          this.subList =data as Sub[];
          console.log(this.subList);

        }
      })
    }else{
      this.subList = this.clientService.subscriptionList;
    }

  }

  selectSub(sub:Sub){
    if(this.current_plan == sub){
      this.current_plan = undefined;
    }else{
      this.current_plan = sub;
    }
  }
  changeSubscription(){
    if(this.current_plan === undefined){

      this.displayError.error = "Impossible de modifier l'abonnement";
      this.displayError.message = "Aucuns abonnement a été sélectionné";
      //throw error
      return;
    }

    this.clientService.changeSubscription(this.current_plan,this.client.id).subscribe({
      next:(data)=>{
        this.displayPopup.image = "./assets/done.png";
        this.displayPopup.reload=true;
        this.displayPopup.text = "L'abonnement du client a été modifié";
      },
      error : (err)=>{
        this.displayError.error = "Impossible de modifier l'abonnement";
        this.displayError.message = "une erreur est survenue lors de la modification";
      }
    })

  }



}
