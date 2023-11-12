import { HttpClient, HttpHeaders } from '@angular/common/http';
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

    this.sendConnection(userToAdd);

  }
  private sendConnection(user:User){

    this.http.post(ApiURL+"/user/auth",user).subscribe({
      next: (data)=>{
        console.log(data);
        sessionStorage.setItem("token",JSON.stringify(data));
      },
      error: (e)=>{
        console.log(e);
      }
    })

  }

  public getUsers(){
    const header = {
      "Authorization" : "eyJhbGciOiJIUzI1NiJ9.eyJzYWxhcnkiOiIxNDAwLjAiLCJzdGF0dXMiOiJFbXBsb3llZSIsImxhc3ROYW1lIjoiR29yaXNzZSIsInN1YiI6IlRob21hc0BHb3Jpc3NlIiwiZmlyc3ROYW1lIjoiVGhvbWFzIiwibG9naW4iOiJUaG9tYXNAR29yaXNzZSIsImV4cCI6MTY5OTgzNjAwNH0.0LE4sxdP83IagoNEgEN_FDJI6Lp32AXDZhtWwek7DKY"
    }
    this.http.get(ApiURL+"/user/getUsers").subscribe({
      next: (data)=>console.log(data)
    })
  }

}
