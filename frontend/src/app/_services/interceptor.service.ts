import { HttpErrorResponse, HttpEvent, HttpHandler, HttpInterceptor, HttpRequest } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, catchError, throwError } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class InterceptorService implements HttpInterceptor{

  constructor() { }
  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    let token = sessionStorage.getItem("token");
    console.log( "aaaaaaaaaaaaaaaaaaaaaaaaaaaa");

    if(token!=null){
      var cloned = req.clone({
        setHeaders: {
          Authorization: token
        }
      })
    return next.handle(cloned).pipe(
      catchError((err:HttpErrorResponse)=>{
        console.log(err);
        return throwError(()=>err)
      })
    )
    }else{
      return next.handle(req);
    }

  }
}
