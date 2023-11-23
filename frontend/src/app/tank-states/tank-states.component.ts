import { AfterViewInit, Component } from '@angular/core';
import { ProviderService } from '../_services/provider.service';
import { Fuel } from '../_interfaces/fuel';

@Component({
  selector: 'app-tank-states',
  templateUrl: './tank-states.component.html',
  styleUrls: ['./tank-states.component.css']
})
export class TankStatesComponent  {
  protected listFuel :Fuel[] = []
  showTooltip:string | null = null;

  constructor(private providerService:ProviderService) {
    if(providerService.listFuel === null){
      this.providerService.getFuelList().subscribe({
        next:(data)=>this.listFuel = data
      })
    }else{
      this.listFuel = this.providerService.listFuel as Fuel[];
    }
  }

  calculateHeight(level: number): number {
    // Par exemple, supposons que 500L correspond à 100% de hauteur
    const maxHeight = 100; // en pourcentage
    const maxLevel = 500; // en litres
    // Calculer la hauteur en pourcentage
    const heightPercentage = (level / maxLevel) * maxHeight;
    return heightPercentage;
  }

}
