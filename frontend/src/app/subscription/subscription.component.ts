import { Component } from '@angular/core';

@Component({
  selector: 'app-subscription',
  templateUrl: './subscription.component.html',
  styleUrls: ['./subscription.component.css']
})
export class SubscriptionComponent {

  option:string = 'create';

  constructor(){

  }
  changeOption(option:string){
    this.option = option;
  }
}
