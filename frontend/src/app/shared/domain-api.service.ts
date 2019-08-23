import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class DomainApiService {
  url = "http://localhost:8080/domain/";
  constructor(private http: HttpClient) { }

  getDomainByName(name: string): Observable<string> {
    console.log('coming here');
    return this.http.get<string>(this.url + name);
  }
}
