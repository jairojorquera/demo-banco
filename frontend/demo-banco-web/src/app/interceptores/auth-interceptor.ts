import { HttpHandler, HttpInterceptor, HttpRequest } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { StorageService } from '../servicios/storage.service';


@Injectable()
export class AuthInterceptor implements HttpInterceptor {

  constructor(private storageService: StorageService) { }

  intercept(req: HttpRequest<any>, next: HttpHandler) {
    console.log("interceptando hacia " + req.url);

    const exclusionSesion = /sesiones/gi;
    const exclusionUsuario = /usuarios/gi;

    if (req.url.search(exclusionSesion) === -1
    && req.url.search(exclusionUsuario) === -1
    ) {
      this.storageService.loadSessionData();
      const authToken = this.storageService.getSesion().token;
      const authReq = req.clone({ setHeaders: { Authorization: "Bearer " + authToken } });
      return next.handle(authReq);
    } else {
      return next.handle(req);

    }
  }
}