import { AfterViewInit, Component } from '@angular/core';

@Component({
  selector: 'app-tank-states',
  templateUrl: './tank-states.component.html',
  styleUrls: ['./tank-states.component.css']
})
export class TankStatesComponent implements AfterViewInit {

  constructor() {


  }
  ngAfterViewInit(): void {
    var levelStates: any = document.getElementsByClassName('actualLevel');
    // for each level state, make the height random between 0 and 100%
    for (var i = 0; i < levelStates.length; i++) {
      var randomHeight = Math.floor(Math.random() * 100) + 1;
      levelStates[i].style.height = randomHeight + '%';
      console.log(levelStates[i]);
    }
  }

}
