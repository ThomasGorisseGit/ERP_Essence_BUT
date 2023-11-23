import { IncidentService } from './../../_services/incident.service';
import { Component, Input, ViewChild } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { Incident } from 'src/app/_interfaces/incident';
import { DisplayErrorComponent } from 'src/app/_popup/display-error/display-error.component';
import { DisplayPopupComponent } from 'src/app/_popup/display-popup/display-popup.component';

@Component({
  selector: 'app-report',
  templateUrl: './report.component.html',
  styleUrls: ['./report.component.css']
})
export class ReportComponent {

  @Input()
  listIncidents:Incident[] = [];
  formGroup = new FormGroup({
    title:new FormControl(),
    description:new FormControl(),
  })



  @ViewChild(DisplayErrorComponent)
  displayError!: DisplayErrorComponent;
  @ViewChild(DisplayPopupComponent)
  displayPopup!: DisplayPopupComponent;

  constructor(private incidentService:IncidentService){}

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

}
