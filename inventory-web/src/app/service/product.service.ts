import { Injectable } from '@angular/core';
import {HttpService} from './http.service';
import {Observable} from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ProductService {

  constructor(private httpService: HttpService) { }

  findAllProducts(): Observable<any> {
    return this.httpService.get('products');
  }

  calculatePrice(params: { product_id: any; units: any }): Observable<any> {
    return this.httpService.get('prices', params);
  }

  loadUnitPrices(params: any): Observable<any> {
    return this.httpService.get('prices/unit-prices', params);
  }

}
