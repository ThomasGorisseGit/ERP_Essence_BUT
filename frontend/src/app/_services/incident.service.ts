import { HttpClient } from '@angular/common/http';
import { Injectable, ViewChild } from '@angular/core';
import { Incident } from '../_interfaces/incident';
import { ApiURL } from '../_const/const';
import { DisplayErrorComponent } from '../_error/display-error/display-error.component';

@Injectable({
  providedIn: 'root'
})
export class IncidentService {


  constructor(private http:HttpClient) { }

  addIncident(incident:Incident){
    return this.http.post(ApiURL+"/incident/add",incident)
  }
  findByDate(date:string){
    return this.http.get(ApiURL+"/incident/findByDate?"+date);
  }
}
