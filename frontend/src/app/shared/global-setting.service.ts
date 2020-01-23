import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class GlobalSettingService {
  url = "http://localhost:8080/setting/";
  constructor(private http: HttpClient) { }

  getSettingByName(name: string): Observable<string> {
    console.log('url is ' + this.url);
    return this.http.get<string>(this.url + name);
  }
}
