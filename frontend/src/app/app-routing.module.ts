import { CaisseComponent } from './caisse/caisse.component';
import { IncidentPageComponent } from './incident-page/incident-page.component';
import { LandingPageComponent } from './landing-page/landing-page.component';
import { LoginComponent } from './login/login.component';
import { NgModule, Injectable, inject } from '@angular/core';
import { Router, RouterModule, Routes } from '@angular/router';
import { StocksComponent } from './stocks/stocks.component';
import { AddFournisseurComponent } from './stocks/add-fournisseur/add-fournisseur.component';
import { ShowInventoryComponent } from './stocks/show-inventory/show-inventory.component';
import { AddReapproComponent } from './stocks/add-reappro/add-reappro.component';
import { HashLocationStrategy, LocationStrategy } from '@angular/common';
import { AuthService } from './_services/auth.service';

const guard = ()=>{

  const auth = inject(AuthService);
  const router = inject(Router);

  if(!auth.isConnected()){
    router.navigateByUrl("/login");
    return false;
  }
  return true;
};
const routes: Routes = [{
  path: 'login',
  component: LoginComponent
},
{
  path: 'home',
  component: LandingPageComponent,
  canActivate:[guard]
},
{
  path: '',
  redirectTo: '/home',
  pathMatch: 'full'
},
{
  path: 'caisse',
  component: CaisseComponent,
  canActivate:[guard]
},
{
  path: 'incidents',
  component: IncidentPageComponent,
  canActivate:[guard]
},
{
  path: 'stocks',
  component: StocksComponent,
  canActivate:[guard],
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
