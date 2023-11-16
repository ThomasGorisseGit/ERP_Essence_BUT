import { Component, Input } from '@angular/core';

@Component({
  selector: 'app-display-popup',
  templateUrl: './display-popup.component.html',
  styleUrls: ['./display-popup.component.css']
})
export class DisplayPopupComponent {
  @Input()text!: string | null;
  @Input()image!: string | null;
  @Input()reload: boolean = false;
  constructor(){
  }


  forceErrorStop(){
    this.text=null;
    if(this.reload){
      location.reload();
    }
  }
  startTimer(){

    setTimeout(()=>{
      this.text=null
      if(this.reload){
        location.reload();
      }
    },2000)

    return true;

  }
}
