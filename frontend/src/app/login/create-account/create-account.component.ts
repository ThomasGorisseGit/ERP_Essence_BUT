import { User } from 'src/app/_interfaces/user';
import { ConnectionService } from './../../_services/connection.service';
import { LoginComponent } from './../login.component';
import { Component, ViewChild } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { DisplayPopupComponent } from 'src/app/_popup/display-popup/display-popup.component';
import { DisplayErrorComponent } from 'src/app/_popup/display-error/display-error.component';
import { HttpErrorResponse } from '@angular/common/http';

@Component({
  selector: 'app-create-account',
  templateUrl: './create-account.component.html',
  styleUrls: ['./create-account.component.css']
})
export class CreateAccountComponent {
  @ViewChild(LoginComponent)
  login!: LoginComponent;

  @ViewChild(DisplayPopupComponent)
  display !: DisplayPopupComponent
  @ViewChild(DisplayErrorComponent)
  error !: DisplayErrorComponent

  formGroup= new FormGroup({
    password : new FormControl(),
    last_name : new FormControl(),
    first_name : new FormControl()
  });
  constructor(
    private connectionService:ConnectionService
  ) { }
  submit(){
    var user :User = {
      id: 0,
      firstName: this.formGroup.value.first_name,
      lastName: this.formGroup.value.last_name,
      login: '',
      password: this.formGroup.value.password,
      status: '',
      salary: 0,
      username: ''
    }

    this.connectionService.createUser(user).subscribe({
      next:(data)=> {
        this.display.reload = true;
        this.display.text = "Utilisateur ajouté, Veuillez vous connecter"
        this.display.image = "./assets/done.png"
      },
      error:(err:HttpErrorResponse)=>{
        console.log(err);

        if(err.status == 500){
          this.error.message = "L'utilisateur existe déjà"
        }
        this.error.error = "Une erreur est survenue"
      }

    })
  }

}
