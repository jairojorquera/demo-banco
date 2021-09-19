import { HttpHandler, HttpInterceptor, HttpRequest } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { StorageService } from './storage.service';


@Injectable()
export class AuthInterceptor implements HttpInterceptor {

  constructor(private storageService: StorageService) { }

  intercept(req: HttpRequest<any>, next: HttpHandler) {

    const exclusiones = /sesiones/gi;

    if (req.url.search(exclusiones) === -1) {
      this.storageService.loadSessionData();
      const authToken = this.storageService.getSesion().token;
      const authReq = req.clone({ setHeaders: { Authorization: "Bearer " + authToken } });
      return next.handle(authReq);
    } else {
      return next.handle(req);

    }
  }
}