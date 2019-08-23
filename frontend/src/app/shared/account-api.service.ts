import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class AccountApiService {
  url = "http://localhost:8080/account/";
  constructor(private http: HttpClient) { }

  getAccountDetails(name: string): Observable<string> {
    return this.http.get<string>(this.url + name);
  }
}
