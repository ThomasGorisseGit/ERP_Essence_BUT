import { IncidentService } from './../_services/incident.service';
import { Component } from '@angular/core';
import { Incident } from '../_interfaces/incident';
import { INCIDENTS } from '../_const/const';
import { HttpErrorResponse } from '@angular/common/http';
import { Router } from '@angular/router';

@Component({
  selector: 'app-landing-page',
  templateUrl: './landing-page.component.html',
  styleUrls: ['./landing-page.component.css']
})
export class LandingPageComponent {
  listIncidents: Incident[] = INCIDENTS;
  constructor(private incidentService:IncidentService,private router:Router) {
    this.incidentService.findByDate(new Date().toISOString().split('T')[0]).subscribe({
      next: (data:Incident[])=>{
        this.listIncidents = data;
      },
      error: (e:HttpErrorResponse)=> {
        console.log(e);
      }
    })
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


}
