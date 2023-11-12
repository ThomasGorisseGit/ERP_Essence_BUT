import { Component } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { ConnectionService } from '../_services/connection.service';
import { User } from '../_interfaces/user';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {


  formGroup :FormGroup = new FormGroup({
    login : new FormControl(""),
    password : new FormControl("")
  });

  constructor(private connectionService:ConnectionService){

  }

  public sendConnection() {
    var user :User = this.formGroup.value

    console.log(user);

    this.connectionService.checkUserInfos(undefined,undefined, user);

  }
  getUsers() {
    console.log("in");

    this.connectionService.getUsers();
  }
}
