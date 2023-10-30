import { Component } from '@angular/core';
import { FormControl } from '@angular/forms';

@Component({
  selector: 'app-add-reappro',
  templateUrl: './add-reappro.component.html',
  styleUrls: ['./add-reappro.component.css']
})
export class AddReapproComponent {
  productProposedList: string[] = ['Produit 1', 'Produit 2', 'Produit 3'];
  fournisseurList: string[] = ['Fournisseur 1', 'Fournisseur 2', 'Fournisseur 3'];
  productProposed: FormControl = new FormControl('');
  fournisseur: FormControl = new FormControl('');
  constructor() { }

}
