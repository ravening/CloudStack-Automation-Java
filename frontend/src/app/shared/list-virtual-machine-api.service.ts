import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class ListVirtualMachineApiService {
  url = "http://localhost:8080/virtualmachines/";
  constructor(private http: HttpClient) { }

  getVirtualMachineByName(name: string): Observable<string> {
    return this.http.get<string>(this.url + name);
  }
}
