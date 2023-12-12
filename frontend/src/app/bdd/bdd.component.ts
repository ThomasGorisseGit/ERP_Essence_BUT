import { Component } from '@angular/core';

@Component({
  selector: 'app-bdd',
  templateUrl: './bdd.component.html',
  styleUrls: ['./bdd.component.css']
})
export class BddComponent {
  private selectedPage:string = "Stocks";

  getSelectedPage() :string{
    return this.selectedPage;
  }
  setSelectedPage(page:string){
    this.selectedPage = page;
  }
}
