import { Component, Input } from '@angular/core';

@Component({
  selector: 'app-display-popup',
  templateUrl: './display-popup.component.html',
  styleUrls: ['./display-popup.component.css']
})
export class DisplayPopupComponent {
  @Input()text!: string | null;
  @Input()image!: string | null;

  constructor(){
  }


  forceErrorStop(){
    this.text=null;
  }
  startTimer(){

    setTimeout(()=>{
      this.text=null
    },2000)
    return true;

  }
}
