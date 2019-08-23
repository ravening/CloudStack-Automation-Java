import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {ListZonesApiService} from "../shared/list-zones-api.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-zone',
  templateUrl: './zone.component.html',
  styleUrls: ['./zone.component.css']
})
export class ZoneComponent implements OnInit {
  validMessage = "";
  zoneForm: FormGroup;
  title: string = "List zones";

  constructor(private zoneapiService: ListZonesApiService,
              private route: Router,
              private formBuilder: FormBuilder) { }

  ngOnInit() {
  }

  onSubmit() {

  }
}
