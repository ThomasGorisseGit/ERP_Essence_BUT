import { Component } from '@angular/core';
import { Incident } from '../_interfaces/incident';
import { Cart } from '../cart';
import { ProductService } from '../service/product.service';
import { INCIDENTS } from '../_const/const';
import { Router } from '@angular/router';

@Component({
  selector: 'app-caisse',
  templateUrl: './caisse.component.html',
  styleUrls: ['./caisse.component.css'],
})
export class CaisseComponent {

  listIncidents: Incident[] = [INCIDENTS[4],INCIDENTS[5],INCIDENTS[7],INCIDENTS[0]] ;
  inputValue: string = '';
  cart: Cart = { listProduct: [], total: 0 };

  loading: boolean = false;
  validate: boolean = false;

  constructor(private productService: ProductService,private router:Router) { }
  ngOnInit(): void {

    // shuffle the list of incidents
    for (var i = this.listIncidents.length - 1; i > 0; i--) {
      var j = Math.floor(Math.random() * (i + 1));
      var temp = this.listIncidents[i];
      this.listIncidents[i] = this.listIncidents[j];
      this.listIncidents[j] = temp;
    }
  }

  onButtonClicked(value: string) {
    if (value == '') {
      this.inputValue = this.inputValue.slice(0, -1);
    }
    this.inputValue += value;
  }
  checkboxClicked(event: any) {
    var checkboxes = document.getElementsByName('checkbox');
    var checked = false;
    for (var i = 0; i < checkboxes.length; i++) {
      if ((checkboxes[i] as HTMLInputElement).checked) {
        if (
          (checkboxes[i] as HTMLInputElement) ==
          event.target.previousElementSibling
        ) {
          event.target.previousElementSibling.checked = false;
          return;
        }
        checked = true;
        event.preventDefault();
        return;
      }
    }
    if (!checked) {
      event.target.previousElementSibling.checked = true;
      event.preventDefault();
    } else {
      event.preventDefault();
    }
  }

  validateProduit() {
    // check if the id product of input is present in the list of products
    var trouve = null;
    for (var i = 0; i < this.productService.listProduct.length; i++) {
      if (
        this.productService.listProduct[i].idProduit ==
        parseInt(this.inputValue)
      ) {
        trouve = this.productService.listProduct[i];
        break;
      }
    }
    if (trouve) {
      // add the product to the cart if it is not already present
      var trouve2 = -1;
      for (var i = 0; i < this.cart.listProduct.length; i++) {
        if (this.cart.listProduct[i].produit.idProduit == trouve.idProduit) {
          trouve2 = i;
          console.log('produit déjà présent');

          break;
        }
      }
      console.log(trouve2);

      if (trouve2 == -1) {
        this.cart.listProduct.push({ produit: trouve, quantity: 1 });
      } else {
        console.log('donc on incrémente');

        this.cart.listProduct[trouve2].quantity++;
      }
      // update total
      this.cart.total = this.cart.listProduct.reduce(
        (total, item) => total + item.produit.prix * item.quantity,
        0
      );
      this.cart.total = Math.round(this.cart.total * 100) / 100;
    }
    this.inputValue = '';
  }

  deleteProduct(id: number) {
    var trouve = -1;
    for (var i = 0; i < this.cart.listProduct.length; i++) {
      if (this.cart.listProduct[i].produit.idProduit == id) {
        trouve = i;
        break;
      }
    }
    if (trouve != -1) {
      console.log('produit trouvé');
      if (this.cart.listProduct[trouve].quantity > 1) {
        console.log('donc on décrémente');
        this.cart.listProduct[trouve].quantity--;
      } else {

        this.cart.listProduct.splice(trouve, 1);
      }
      this.cart.total = this.cart.listProduct.reduce(
        (total, item) => total + item.produit.prix * item.quantity,
        0
      );
      this.cart.total = Math.round(this.cart.total * 100) / 100;
    }
  }
  validatePaiement() {
    console.log('paiement validé');
    if (this.cart.total == 0) {
      return;
    }
    this.showPopup();
    this.cart.listProduct = [];
    this.cart.total = 0;
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
    this.cart.listProduct = [];
    this.cart.total = 0;
  }

  essenceInCart(): boolean {
    var cpt = 0;
    var trouve = false;
    for (var i = 0; i < this.cart.listProduct.length; i++) {
      if (this.cart.listProduct[i].produit.nom == "Essence" || this.cart.listProduct[i].produit.nom == "Hydrocarbure" || this.cart.listProduct[i].produit.nom == "Gasoil" || this.cart.listProduct[i].produit.nom == "Batterie") {
        cpt++;
        trouve = true;
      }
    }
    if (trouve) {
      return cpt == this.cart.listProduct.length;
    }
    return false;
  }
  gotoIncident(incident:Incident){
    this.router.navigate(["/incidents"],{queryParams:{
      "incident_title":incident.title,
      "page":"FAQ des incidents"
    }})
  }
}
