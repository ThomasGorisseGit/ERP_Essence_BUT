import { AuthInterceptorInterceptor } from './../auth-interceptor.interceptor';
import { AuthService } from './../_services/auth.service';
import { Component } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent {
  constructor(private router: Router,private authService:AuthService){}



  isNotLoginPage() {
    return this.router.url !== '/login';
  }
  disconnect(){
    this.authService.logout();
    this.router.navigateByUrl("/login");

  }
}
