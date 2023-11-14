import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  constructor() { }

  public login(token:string){
    sessionStorage.setItem("token",token);
  }
  public logout(){
    sessionStorage.removeItem("token");
  }
  public isConnected(): boolean
  {
    return sessionStorage.getItem("token") !==null;
  }
}
