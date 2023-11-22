import { IncidentService } from './../_services/incident.service';
import { Component } from '@angular/core';
import { Incident } from '../_interfaces/incident';
import { INCIDENTS } from '../_const/const';
import { HttpErrorResponse } from '@angular/common/http';
import { Router } from '@angular/router';
import { ProviderService } from '../_services/provider.service';
import { Provider } from '../_interfaces/provider';

@Component({
  selector: 'app-landing-page',
  templateUrl: './landing-page.component.html',
  styleUrls: ['./landing-page.component.css']
})
export class LandingPageComponent {
  listIncidents: Incident[] = [];
  protected listProviderFuel:Provider[] = [];
  constructor(private incidentService:IncidentService,private router:Router,private providerService:ProviderService) {

    if(this.listIncidents.length===0){
      this.fetchIncidents();
    }
    if(this.providerService.listProviderFuel === null){
      this.providerService.getProviderFuel().subscribe({
        next:(data:Provider[])=>{
          this.listProviderFuel = data as Provider[];
        }
      })
    }
    else{
      this.listProviderFuel = this.providerService.listProviderFuel;
    }



  }
  gotoIncident(incident:Incident | null){
    if(incident!= null){

      this.router.navigate(["/incidents"],{ queryParams:
        {
          "incident_id":incident.id,
        }
      })
    }
    else{
      this.router.navigate(["/incidents"],{queryParams:{
        "page":"Reporter un incident"
      }})
    }
  }
  private fetchIncidents(){
    this.incidentService.findByDate(new Date().toISOString().split('T')[0]).subscribe({
      next: (data:Incident[])=>{
        this.listIncidents = data;
      },
      error: (e:HttpErrorResponse)=> {
        console.log(e);
      }
    })
  }


}
