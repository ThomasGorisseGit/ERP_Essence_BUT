import { Component } from '@angular/core';
import { Incident } from '../incident';

@Component({
  selector: 'app-landing-page',
  templateUrl: './landing-page.component.html',
  styleUrls: ['./landing-page.component.css']
})
export class LandingPageComponent {
  listIncidents: Incident[] = [];
  constructor() { }

  ngOnInit(): void {
    this.listIncidents.push({
      id: 1,
      title: 'Incident 1',
      description: 'Description de l\'incident 1',
      date: new Date(),
      heure: '10:00',
      type: 'Feu'
    });
    this.listIncidents.push({
      id: 2,
      title: 'Incident 2',
      description: 'Description de l\'incident 2',
      date: new Date(),
      heure: '10:00',
      type: 'Basic'
    });
    this.listIncidents.push({
      id: 3,
      title: 'Incident 3',
      description: 'Description de l\'incident 3',
      date: new Date(),
      heure: '10:00',
      type: 'Important'
    });
    this.listIncidents.push({
      id: 4,
      title: 'Incident 4',
      description: 'Description de l\'incident 4',
      date: new Date(),
      heure: '10:00',
      type: 'Feu'
    });
    this.listIncidents.push({
      id: 5,
      title: 'Incident 5',
      description: 'Description de l\'incident 5',
      date: new Date(),
      heure: '10:00',
      type: 'Basic'
    });
    this.listIncidents.push({
      id: 6,
      title: 'Incident 6',
      description: 'Description de l\'incident 6',
      date: new Date(),
      heure: '10:00',
      type: 'Feu'
    });
  }
}
