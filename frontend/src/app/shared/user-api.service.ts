import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable, of} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class UserApiService {
  url = "http://localhost:8080/users";
  constructor(private  http: HttpClient) { }

  searchUser(name: string): Observable<string> {
    return this.http.get<string>(this.url + '/username/' + name);
  }
}
