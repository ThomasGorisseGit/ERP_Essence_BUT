import { Component, Input } from '@angular/core';
import { Incident } from '../_interfaces/incident';

@Component({
  selector: 'app-incidents',
  templateUrl: './incidents.component.html',
  styleUrls: ['./incidents.component.css']
})
export class IncidentsComponent {
  @Input() incident: Incident = { id: 1, title: 'Incident 1', description: 'Description de l\'incident 1', date: new Date().toISOString().split("T")[0],image:"" };
  constructor() {

  }

}
