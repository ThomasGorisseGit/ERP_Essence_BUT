import { Component, Input, Provider } from '@angular/core';
import { Client } from 'src/app/_interfaces/client';
import { Fuel } from 'src/app/_interfaces/fuel';
import { Product } from 'src/app/_interfaces/product';

@Component({
  selector: 'app-content',
  templateUrl: './content.component.html',
  styleUrls: ['./content.component.css']
})
export class ContentComponent {
  @Input()
   productList :Product[] = [];
  @Input()
  fuelList :Fuel[] = [];
  @Input()
  providerList :Provider[] = [];
  @Input()
  clientList :Client[] = [];

}
