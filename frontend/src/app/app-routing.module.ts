import { CaisseComponent } from './caisse/caisse.component';
import { IncidentPageComponent } from './incident-page/incident-page.component';
import { LandingPageComponent } from './landing-page/landing-page.component';
import { LoginComponent } from './login/login.component';
import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { StocksComponent } from './stocks/stocks.component';
import { AddFournisseurComponent } from './stocks/add-fournisseur/add-fournisseur.component';
import { ShowInventoryComponent } from './stocks/show-inventory/show-inventory.component';
import { AddReapproComponent } from './stocks/add-reappro/add-reappro.component';
import { HashLocationStrategy, LocationStrategy } from '@angular/common';

const routes: Routes = [{
  path: 'login',
  component: LoginComponent
},
{
  path: 'home',
  component: LandingPageComponent
},
{
  path: '',
  redirectTo: '/home',
  pathMatch: 'full'
},
{
  path: 'caisse',
  component: CaisseComponent
},
{
  path: 'incidents',
  component: IncidentPageComponent
},
{
  path: 'stocks',
  component: StocksComponent,
  children: [
    {
      path: '',
      redirectTo: 'addFournisseur',
      pathMatch: 'full'
    },
    {
      path: 'addFournisseur',
      component: AddFournisseurComponent
    },
    {
      path: 'inventory',
      component: ShowInventoryComponent
    },
    {
      path: 'addReappro',
      component: AddReapproComponent
    }
  ]

}

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
