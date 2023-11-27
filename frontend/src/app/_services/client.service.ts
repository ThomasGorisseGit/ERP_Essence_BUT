import { Client } from './../_interfaces/client';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { ApiURL } from '../_const/const';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ClientService {
  clientList : Client[] | null = null;
  constructor(private http:HttpClient) {
    if(this.clientList === null){
      this.getClients();
    }
  }
  createClient(cli : Client) :Observable<Client>{
    return this.http.post(ApiURL+"/client/add",cli) as Observable<Client>
  }

  getClients(){
    return this.http.get(ApiURL+ "/client/getClients").pipe((req)=>{
      req.subscribe({
        next:(data)=>{
          this.clientList = data as Client[];
        }
      })
      return req;
    })
  }
  delete(client : Client){
    return this.http.delete(ApiURL+"/client/delete/"+client.id)
  }
}
