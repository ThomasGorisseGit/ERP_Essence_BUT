import { Component } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'erpApp';
  constructor(private router: Router) { }


  ngOnInit() {
  }
  isLoginPage() {
    return this.router.url !== '/login';
  }
}
