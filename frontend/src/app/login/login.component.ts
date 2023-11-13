import { Component } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { ConnectionService } from '../_services/connection.service';
import { User } from '../_interfaces/user';
import { HttpErrorResponse } from '@angular/common/http';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {

  public ERROR!:string | null;
  formGroup :FormGroup = new FormGroup({
    login : new FormControl(""),
    password : new FormControl("")
  });

  constructor(private connectionService:ConnectionService){

  }

  public sendConnection() {
    var user :User = this.formGroup.value

    console.log(user);

    this.connectionService.checkUserInfos(undefined,undefined, user).subscribe({
      next: (data)=>{
        sessionStorage.setItem("token",JSON.stringify(data));
        this.ERROR=null;
      },
      error: (e:HttpErrorResponse)=>{
        this.ERROR = e.message;

      }
    })


  }
  getUsers() {
    console.log("in");

    this.connectionService.getUsers();
  }
}
