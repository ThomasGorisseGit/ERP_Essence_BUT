import { DisplayPopupComponent } from './../_popup/display-popup/display-popup.component';
import { IncidentService } from './../_services/incident.service';
import { Component, ViewChild } from '@angular/core';
import { Incident } from '../_interfaces/incident';
import { INCIDENTS } from '../_const/const';
import { HttpErrorResponse } from '@angular/common/http';
import { Router } from '@angular/router';
import { ProviderService } from '../_services/provider.service';
import { Provider } from '../_interfaces/provider';
import { FormControl, FormGroup } from '@angular/forms';
import { Fuel } from '../_interfaces/fuel';
import { DisplayErrorComponent } from '../_popup/display-error/display-error.component';

@Component({
  selector: 'app-landing-page',
  templateUrl: './landing-page.component.html',
  styleUrls: ['./landing-page.component.css']
})
export class LandingPageComponent {

  listIncidents: Incident[] = [];
  protected listProviderFuel:Provider[] = [];
  protected listFuel :Fuel[] = [];

  protected formGroup :FormGroup= new FormGroup({
    provider:new FormControl(),
    fuel:new FormControl() ,
    quantity: new FormControl()
  })


  @ViewChild(DisplayPopupComponent)
  displayPopup !: DisplayPopupComponent;
  @ViewChild(DisplayErrorComponent)
  displayError !: DisplayErrorComponent;

  constructor(private incidentService:IncidentService,private router:Router,private providerService:ProviderService) {
    this.formGroup.get("fuel")?.disable({emitEvent:false});
    if(this.listIncidents.length===0){
      this.fetchIncidents();
    }
    if(this.providerService.listProviderFuel === null){
      this.providerService.getProviderFuel().subscribe({
        next:(data:Provider[])=>{
          this.listProviderFuel = data as Provider[];
        }
      })
    }
    else{
      this.listProviderFuel = this.providerService.listProviderFuel;
    }


    this.handleChanges();


  }
  gotoIncident(incident:Incident | null){
    if(incident!= null){

      this.router.navigate(["/incidents"],{ queryParams:
        {
          "incident_id":incident.id,
        }
      })
    }
    else{
      this.router.navigate(["/incidents"],{queryParams:{
        "page":"Reporter un incident"
      }})
    }
  }
  private fetchIncidents(){
    this.incidentService.findByDate(new Date().toISOString().split('T')[0]).subscribe({
      next: (data:Incident[])=>{
        this.listIncidents = data;
      },
      error: (e:HttpErrorResponse)=> {
        console.log(e);
      }
    })
  }
  private handleChanges(){
    this.formGroup.valueChanges.subscribe((data)=>{
      console.log(data);

      if(data["provider"] != null){
        var p:Provider = this.listProviderFuel.find(prov=>prov.id==data["provider"]) as Provider;
        this.listFuel = p.fuelList;
        this.formGroup.get("fuel")?.enable({emitEvent:false});
      }

    })
  }
  private getFuel(){
    var fuel : Fuel = {id:0,price : 0} as Fuel;
    this.listFuel.forEach((f)=>{
      if(f.id == this.formGroup.value.fuel){
        fuel = f;
      }
    })
    return fuel;
  }
  getTotal(){
    let qte :number = 0;
    if(this.formGroup.value.quantity != null)
    {
      qte = this.formGroup.value.quantity;
    }
    return (qte * this.getFuel().price).toFixed(2);
  }
  submit(){

    this.providerService.addFuelQte(this.formGroup.value.fuel,this.formGroup.value.quantity).subscribe({
      next:(data:Fuel)=>{
        this.displayPopup.text="Approvisionnement des stocks";
        this.displayPopup.image = "./assets/done.png"
      },
      error:(err:HttpErrorResponse)=> {
        this.displayError.error="Une erreur est survenue lors de l'insertion"

        if(err.status===409){
          this.displayError.message="les stocks sont déjà pleins"

        }else{
          this.displayError.message="Impossible de remplir les stocks"
        }
      },


    })
  }
}
