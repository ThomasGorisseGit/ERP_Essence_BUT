import { ClientService } from './../_services/client.service';
import { Incident } from './../_interfaces/incident';
import { Component, ViewChild } from '@angular/core';
import { Cart } from '../_interfaces/cart';
import { INCIDENTS } from '../_const/const';
import { Router } from '@angular/router';
import { ProductService } from '../_services/product.service';
import { Product } from '../_interfaces/product';
import { DisplayErrorComponent } from '../_popup/display-error/display-error.component';
import { DisplayPopupComponent } from '../_popup/display-popup/display-popup.component';
import { FormControl } from '@angular/forms';
import { Client } from '../_interfaces/client';

@Component({
  selector: 'app-caisse',
  templateUrl: './caisse.component.html',
  styleUrls: ['./caisse.component.css'],
})
export class CaisseComponent {

  listIncidents: Incident[] = [INCIDENTS[4],INCIDENTS[5],INCIDENTS[7],INCIDENTS[0]] ;
  IncidentToReport : Incident[] = INCIDENTS;

  clientList : Client[] = [];
  client_id : FormControl = new FormControl();
  currentClient :Client | null = null;

  total :number= 0;
  displayTotal:String = "0";
  discount : string | null='';

  listProduct : Product[] = [];
  inputValue: string = '';
  connect :boolean = false;
  cart:Cart ={
    listProduct: [],
    total: 0
  };

  loading: boolean = false;
  validate: boolean = false;

  @ViewChild(DisplayErrorComponent)
  displayError!: DisplayErrorComponent;
  @ViewChild(DisplayPopupComponent)
  displayPopup!: DisplayPopupComponent;



  constructor(private productService: ProductService,private router:Router,private ClientService:ClientService) {
    if(this.productService.listProductWithStocks === null) {
      this.productService.ProductContainsAll().subscribe({
        next:(data)=>{
          this.listProduct = data;

        }
      })
    }
    else{
      this.listProduct= this.productService.listProductWithStocks;
    }
    this.handleCli();
  }

  handleCli(){
    if(this.ClientService.clientList === null){
      this.ClientService.getClients().subscribe({
        next:(data)=>{
          this.clientList = data as Client[];
        }
      })
    }
    else {
      this.clientList = this.ClientService.clientList!;
    }
  }
  chooseClient(){
    this.connect = false;
    this.currentClient = this.clientList.find((cli)=>cli.id == this.client_id.value) as Client;
    this.discount = this.currentClient.subscription.discount.value;
    this.getTotal();
  }

  onButtonClicked(value: string) {
    if (value == '') {
      this.inputValue = this.inputValue.slice(0, -1);
    }
    if(value =='-/-')
    {
      this.inputValue = '';
      value = ''
    }
    this.inputValue += value;
  }


  validateProduit() {
    // check if the id product of input is present in the list of products
    if(parseInt(this.inputValue)>this.listProduct.length || parseInt(this.inputValue) == 0 || this.inputValue==''){
      console.log("ERROR");
      this.displayError.error = "Impossible d'ajouter le produit";
      this.displayError.message = "Le produit n'existe pas ou n'est pas disponible";

    }
    else{

      var item = {
        product:this.listProduct[parseInt(this.inputValue)-1],
        quantity : 1
      }
      let index = this.cart.listProduct.findIndex(elem=>elem.product.id === item.product.id)
      if(index != -1 && this.getProductQuantity(this.cart.listProduct[index].product.id) as number> this.cart.listProduct[index].quantity+1){
      this.cart.listProduct[index].quantity++;
      }else if(index != -1 && this.getProductQuantity(this.cart.listProduct[index].product.id) as number <= this.cart.listProduct[index].quantity+1)
      {
        this.displayError.error = "Impossible d'ajouter le produit";
        this.displayError.message = "Le stock de produit n'est pas suffisant";
      }
      else{
        this.cart!.listProduct.push(
          item
          )
        }


    }
    this.getTotal();
  }
  getTotal(){
    this.total = 0;
    this.displayTotal = '0';

    if(this.cart.listProduct.length==0){
      return;
    }
    this.cart.listProduct.forEach((product)=>{
      this.total += product.product.price * product.quantity;
    })
    this.getDiscount();


    this.displayTotal = this.total.toFixed(2);


  }
  getDiscount(){
    if(this.currentClient != null){
      this.total -=  (this.total*parseInt(this.currentClient.subscription.discount.value)) /100
      this.discount=this.currentClient.subscription.discount.value;
    }
  }

  getProductQuantity(productId:number):number{
    return this.listProduct.find(prod=>prod.id==productId)?.stock?.quantity as number;
  }

  deleteProduct(id: number) {
    let index = this.cart.listProduct.findIndex(elem=>elem.product.id === id)
    this.cart.listProduct.splice(index,1)
    this.getTotal();
  }
  validatePaiement() {

  }

  showPopup(): void {
    this.loading = true;
    // random time between 1 and 3 seconds
    setTimeout(() => {
      this.loading = false;
      this.validate = true;
      setTimeout(() => {
        this.validate = false;
      }, 4000);
    }, Math.floor(Math.random() * 2000) + 1000);
  }
  viderPanier() {
    this.cart!.listProduct = [];
    this.getTotal();
  }


  gotoIncident(incident:Incident){
    this.router.navigate(["/incidents"],{queryParams:{
      "incident_title":incident.title,
      "page":"FAQ des incidents"
    }})
  }
}
