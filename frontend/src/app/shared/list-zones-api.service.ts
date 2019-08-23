import { Injectable } from '@angular/core';
import {Observable} from "rxjs";
import {HttpClient} from "@angular/common/http";

@Injectable({
  providedIn: 'root'
})
export class ListZonesApiService {
  url = "http://localhost:8080/zones";
  constructor(private http: HttpClient) { }

  listZonesApi(): Observable<string> {
    console.log('coming here');
    return this.http.get<string>(this.url);
  }
}
