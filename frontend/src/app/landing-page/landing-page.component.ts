import { Component } from '@angular/core';
import { Incident } from '../_interfaces/incident';
import { INCIDENTS } from '../_const/const';

@Component({
  selector: 'app-landing-page',
  templateUrl: './landing-page.component.html',
  styleUrls: ['./landing-page.component.css']
})
export class LandingPageComponent {
  listIncidents: Incident[] = INCIDENTS;
  constructor() { }



}
