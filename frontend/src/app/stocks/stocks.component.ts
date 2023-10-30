import { Component } from '@angular/core';
import { Reappro } from '../reappro';
import { Router } from '@angular/router';
import { ProductService } from '../service/product.service';

@Component({
  selector: 'app-stocks',
  templateUrl: './stocks.component.html',
  styleUrls: ['./stocks.component.css']
})
export class StocksComponent {
  listReappro: Reappro[] = [];
  constructor(public router: Router) {
    this.listReappro = [
      {
        date: '2020-01-01',
        fournisseur: 'Fournisseur',
        produit: 'Produit 1',
        etat: 'done'
      },
      {
        date: '2020-01-02',
        fournisseur: 'Fournisseur',
        produit: 'Produit 2',
        etat: 'inProgress'
      },
      {
        date: '2020-01-03',
        fournisseur: 'Fournisseur',
        produit: 'Produit 3',
        etat: 'canceled'
      },
      {
        date: '2020-01-04',
        fournisseur: 'Fournisseur',
        produit: 'Produit 4',
        etat: 'done'
      },
      {
        date: '2020-01-05',
        fournisseur: 'Fournisseur',
        produit: 'Produit 5',
        etat: 'done'
      },
      {
        date: '2020-01-05',
        fournisseur: 'Fournisseur',
        produit: 'Produit 5',
        etat: 'done'
      },
      {
        date: '2020-01-05',
        fournisseur: 'Fournisseur',
        produit: 'Produit 5',
        etat: 'done'
      }
    ];

  }

  route(): string {

    return this.router.url.slice(8);
  }
}
