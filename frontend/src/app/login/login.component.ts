import { AuthInterceptorInterceptor } from './../auth-interceptor.interceptor';
import { AuthService } from './../_services/auth.service';
import { Component, ViewChild } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { ConnectionService } from '../_services/connection.service';
import { User } from '../_interfaces/user';
import { HttpErrorResponse } from '@angular/common/http';
import { DisplayErrorComponent } from '../_popup/display-error/display-error.component';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {
  createAccount : boolean = false;

  @ViewChild(DisplayErrorComponent) displayError!: DisplayErrorComponent;

  public ERROR!:string | null;
  formGroup :FormGroup = new FormGroup({
    login : new FormControl(""),
    password : new FormControl("")
  });


  constructor(private connectionService:ConnectionService,private authService: AuthService,private router:Router){

  }


  public sendConnection() {
    console.log(this.formGroup.value)
    var user :User = this.formGroup.value


    this.connectionService.checkUserInfos(undefined,undefined, user).subscribe({
      next: (data)=>{
        this.authService.login(JSON.stringify(data));
        this.router.navigateByUrl("/home");
      },
      error: (e:HttpErrorResponse)=>{
        //display Error pop up
        this.displayError.error = "Mot de passe ou login incorrect"
      }


    })


  }



}
