import { Component } from '@angular/core';
import { FormControl } from '@angular/forms';

@Component({
  selector: 'app-add-fournisseur',
  templateUrl: './add-fournisseur.component.html',
  styleUrls: ['./add-fournisseur.component.css']
})
export class AddFournisseurComponent {
  productProposedList: string[] = ['Produit 1', 'Produit 2', 'Produit 3'];
  productProposed: FormControl = new FormControl('');
  constructor() { }
}
