import { Component } from '@angular/core';
import { Incident } from '../../_interfaces/incident';
import { INCIDENTS } from 'src/app/_const/const';

@Component({
  selector: 'app-incident-page',
  templateUrl: './incident-page.component.html',
  styleUrls: ['./incident-page.component.css']
})
export class IncidentPageComponent {
  listIncidents: Incident[] = INCIDENTS;
  incidentSelected: Incident = INCIDENTS[0];
  constructor() {  }

}
