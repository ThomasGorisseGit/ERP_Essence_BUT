import { HttpClient } from '@angular/common/http';
import { Injectable, ViewChild } from '@angular/core';
import { Incident } from '../_interfaces/incident';
import { ApiURL } from '../_const/const';
import { DisplayErrorComponent } from '../_popup/display-error/display-error.component';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class IncidentService {


  constructor(private http:HttpClient) { }

  addIncident(incident:Incident){
    return this.http.post(ApiURL+"/incident/add",incident)
  }
  findByDate(date:string) : Observable<Incident[]>{
    return this.http.get(ApiURL+"/incident/findByDate?",{params:{"date":date}}) as Observable<Incident[]>;
  }
}
