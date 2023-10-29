import { Component, Input } from '@angular/core';
import { Reappro } from 'src/app/reappro';

@Component({
  selector: 'app-reappro',
  templateUrl: './reappro.component.html',
  styleUrls: ['./reappro.component.css']
})
export class ReapproComponent {
  @Input()
  reappro!: Reappro;

  constructor() { }
}
