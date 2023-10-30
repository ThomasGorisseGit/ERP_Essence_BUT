import { Component } from '@angular/core';
import { Pump } from '../pump';

@Component({
  selector: 'app-pump-states',
  templateUrl: './pump-states.component.html',
  styleUrls: ['./pump-states.component.css']
})
export class PumpStatesComponent {
  listPumps: Pump[] = [];
  constructor() {
    this.listPumps.push({
      id: 1,
      state: 'OOS', // Out of service
      durationOccupied: 10
    });
    this.listPumps.push({
      id: 2,
      state: 'Free',
      durationOccupied: 0
    });
    this.listPumps.push({
      id: 3,
      state: 'Filling',
      durationOccupied: 20
    });
    this.listPumps.push({
      id: 4,
      state: 'Filling',
      durationOccupied: 20
    });
    this.listPumps.push({
      id: 5,
      state: 'Free',
      durationOccupied: 20
    });
    this.listPumps.push({
      id: 6,
      state: 'OOS',
      durationOccupied: 20
    });
  }
}
