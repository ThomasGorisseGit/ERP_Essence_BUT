import { Component, ViewChild } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { ConnectionService } from '../_services/connection.service';
import { User } from '../_interfaces/user';
import { HttpErrorResponse } from '@angular/common/http';
import { DisplayErrorComponent } from '../_error/display-error/display-error.component';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {

  @ViewChild(DisplayErrorComponent) displayError!: DisplayErrorComponent;

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
        //display Error pop up
        this.displayError.error = "Mot de passe ou login incorrect"
      }


    })


  }
  getUsers() {
    console.log("in");

    this.connectionService.getUsers();
  }


}
