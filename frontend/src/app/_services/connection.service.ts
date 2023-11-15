import { Observable } from 'rxjs';
import { HttpClient, HttpHeaders, HttpRequest, HttpResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { ApiURL } from '../_const/const';
import { User } from '../_interfaces/user';

@Injectable({
  providedIn: 'root'
})
export class ConnectionService {
  constructor(private http:HttpClient) { }

  public checkUserInfos(inputLogin?:string, inputPassword?:string, inputUser?:User){

    //Create user to add
    var userToAdd : User;

    if(inputUser != null && inputUser !== undefined ){

      //if input is a user
      userToAdd = inputUser

    }else if(inputLogin != null && inputPassword != null  && inputLogin !== undefined && inputPassword !== undefined ){

      //if input is login & password
      userToAdd = {
        id: 0,
        firstName: '',
        lastName: '',
        login: inputLogin,
        password: inputPassword,
        status: '',
        salary: 0,
        username: ''
      }
    }
    else {

      //Invalid input
      throw new Error("Invalid User or inputLogin, inputPassword");

    }

    return this.sendConnection(userToAdd);

  }
  private sendConnection(user:User) :Observable<Object>
  {

    return this.http.post(ApiURL+"/auth/connect",user)

  }

  public getUsers(){

   this.http.get(ApiURL+"/user/getUsers").subscribe({
      next: (data)=>console.log(data)
    })
  }

}
