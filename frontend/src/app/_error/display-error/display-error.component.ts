import { Component, Input, OnChanges, OnInit } from '@angular/core';

@Component({
  selector: 'app-display-error',
  templateUrl: './display-error.component.html',
  styleUrls: ['./display-error.component.css']
})
export class DisplayErrorComponent{

  @Input()error!: string | null;

  constructor(){
  }


  forceErrorStop(){
    this.error=null;
  }
  startTimer(){

    setTimeout(()=>{
      this.error=null
    },2000)
    return true;

  }
}
