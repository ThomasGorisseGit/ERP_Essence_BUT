import { FormControl, FormGroup } from '@angular/forms';
import { Component } from '@angular/core';

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

  constructor(){

  }
  createClient(){
    console.log(this.formGroup.value);

  }
}
