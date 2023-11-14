import { Injectable } from '@angular/core';
import {
  HttpRequest,
  HttpHandler,
  HttpEvent,
  HttpInterceptor,
  HttpHeaders
} from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable()
export class AuthInterceptorInterceptor implements HttpInterceptor {

  constructor() {}

  intercept(request: HttpRequest<unknown>, next: HttpHandler): Observable<HttpEvent<unknown>> {

    var token : {bearer:string} = JSON.parse(sessionStorage.getItem("token") as string) ;
    console.log(token);

    if(token != null){

      const modifiedReq = request.clone({
        setHeaders: {
          Authorization: `Bearer ${token.bearer}`
        }
      });

      return next.handle(modifiedReq);
    }else{
      return next.handle(request);
    }

  }
}
