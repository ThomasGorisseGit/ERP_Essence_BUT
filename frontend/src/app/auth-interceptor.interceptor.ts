import { AuthService } from './_services/auth.service';
import { Injectable } from '@angular/core';
import {
  HttpRequest,
  HttpHandler,
  HttpEvent,
  HttpInterceptor,
  HttpHeaders,
  HttpErrorResponse,
  HttpResponse
} from '@angular/common/http';
import { Observable, catchError, throwError } from 'rxjs';
import { Router } from '@angular/router';

@Injectable()
export class AuthInterceptorInterceptor implements HttpInterceptor {

  constructor(private authService : AuthService,private router:Router) {}

  intercept(request: HttpRequest<unknown>, next: HttpHandler): Observable<HttpEvent<unknown>> {

    var token : {bearer:string} = JSON.parse(sessionStorage.getItem("token") as string) ;

    if(token != null){

      const modifiedReq = request.clone({
        setHeaders: {
          Authorization: `Bearer ${token.bearer}`
        }
      });
      return next.handle(modifiedReq)
    }else{
      return next.handle(request);
    }

  }
}
