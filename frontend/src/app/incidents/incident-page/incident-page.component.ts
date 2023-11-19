import { IncidentService } from './../../_services/incident.service';
import { Incident } from './../../_interfaces/incident';
import { FormControl, FormGroup } from '@angular/forms';
import { Component, Input, ViewChild } from '@angular/core';
import { INCIDENTS } from 'src/app/_const/const';
import { DisplayErrorComponent } from 'src/app/_popup/display-error/display-error.component';
import { ActivatedRoute } from '@angular/router';
import { DisplayPopupComponent } from 'src/app/_popup/display-popup/display-popup.component';
import { KeyValue } from '@angular/common';

@Component({
  selector: 'app-incident-page',
  templateUrl: './incident-page.component.html',
  styleUrls: ['./incident-page.component.css']
})
export class IncidentPageComponent {

  listIncidents: Incident[] = INCIDENTS;
  incidentSelected: Incident = INCIDENTS[0];
  dailyIncident: Incident[] = [];
  dailyIncidentSelected: Incident = this.setDefaultIncident()
  selectedPage ="FAQ des incidents";

  formGroup = new FormGroup({
    title:new FormControl(),
    description:new FormControl(),
  })

  @ViewChild(DisplayErrorComponent)
  displayError!: DisplayErrorComponent;
  @ViewChild(DisplayPopupComponent)
  displayPopup!: DisplayPopupComponent;


  constructor(private incidentService:IncidentService,private route:ActivatedRoute) {
    this.findByDate();

    this.route.queryParams.subscribe((data)=>{
      if(data["page"] !== undefined){
        this.selectedPage = data["page"]
      }
      if(data["incident_title"] !== undefined){
        this.incidentSelected = this.searchByAttribute(data["incident_title"],this.listIncidents,"title");
      }
    })

  }

  getSelectedPage(page:string){
    return this.selectedPage==page;
  }
  setSelectedPage(page:string){
    this.selectedPage=page;
  }

  declarerIncident(){
    var incident:Incident = {
      title: '',
      date: '',
      image: '',
      description: this.formGroup.value.description,
      id: 0
    }
    this.listIncidents.forEach(element => {
      if(element.title === this.formGroup.value.title){
        incident.title = this.formGroup.value.title
        incident.date = new Date().toISOString().split('T')[0];
        incident.image = element.image;
      }
    });
    if(incident.title==null || incident.title == ""){
      this.displayError.error="Impossible de déclarer cet incident"
      throw Error("");
    }
    this.incidentService.addIncident(incident).subscribe({
      complete:()=>{
        this.displayPopup.text="Incident ajouté";
        this.displayPopup.reload = true;
        this.displayPopup.image = "./assets/done.png"
        
      },
      error:(err)=>{
        this.displayError.error="Impossible d'ajouter l'incident, une erreur est survenue lors de l'insertion"
      }
    });
  }
  findByDate(){
    this.incidentService.findByDate(new Date().toISOString().split("T")[0]).subscribe({
      next: (data: Incident[])=>{
        this.dailyIncident=data;
        this.getRouteIncident();
      }
    });
  }
  private setDefaultIncident():Incident{
    return {
      id: 0,
      title: '',
      date: '',
      image: ''
    }
  }
  selectDailyIncident(incident:Incident){
    if(incident.id == this.dailyIncidentSelected.id){
      this.dailyIncidentSelected = this.setDefaultIncident();
    }else{
      this.dailyIncidentSelected = incident;
    }

  }
  private getRouteIncident(){
    if(this.route.snapshot.queryParams["incident_id"])
        {
          this.route.queryParams.subscribe((params)=>{
            this.dailyIncidentSelected = this.searchByAttribute(params["incident_id"],this.dailyIncident,"id")
            this.selectedPage = "Incidents journaliers";
          })
        }
  }
  private searchByAttribute(value:string,list:Incident[],attribute:keyof Incident) : Incident{
    var incident = this.setDefaultIncident();
    list.forEach(element => {
      if(element[attribute] == value) {
        incident = element;
      }
    })
    return incident;
  }
}
