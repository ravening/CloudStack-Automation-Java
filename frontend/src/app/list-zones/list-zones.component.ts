import { Component, OnInit } from '@angular/core';
import {ListZonesApiService} from "../shared/list-zones-api.service";
import {Location} from "@angular/common";

@Component({
  selector: 'app-list-zones',
  templateUrl: './list-zones.component.html',
  styleUrls: ['./list-zones.component.css']
})
export class ListZonesComponent implements OnInit {
  listZonesOutput: string;
  isLoading = true;

  constructor(private zoneapiService: ListZonesApiService,
              private location: Location) { }

  ngOnInit() {
    this.listZones();
  }

  listZones() {
    this.listZonesOutput = 'fetching the zones from cloudstack';
    console.log('coming here');
    this.zoneapiService.listZonesApi()
      .subscribe(data => {
        this.listZonesOutput = data;
        this.isLoading = false;
        console.log(data);
      },
        error1 => {this.listZonesOutput = error1;
      console.log(this.listZonesOutput)});
    console.log('fetching the zones from cloudstack');
  }

  goBack() {
    this.location.back();
  }
}
