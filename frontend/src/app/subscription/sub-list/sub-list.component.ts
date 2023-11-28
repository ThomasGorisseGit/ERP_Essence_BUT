import { ClientService } from './../../_services/client.service';
import { Component, Input } from '@angular/core';
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
      console.log("in");

      this.current_plan = undefined;
    }else{
      this.current_plan = sub;
    }

  }



}
