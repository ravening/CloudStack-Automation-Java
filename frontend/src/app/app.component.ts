import {Component, OnInit} from '@angular/core';
import {Validators} from "@angular/forms";
import {Router} from "@angular/router";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit{
  title = 'Cloudstack Automation';

  constructor(private route: Router) {}

  ngOnInit() {
  }

  listZones() {
    console.log('button clicked');
    this.route.navigate(['listzones']);
  }

  handleVirtualMachines() {
    this.route.navigate(['listvirtualmachines']);
  }

  handleUsers() {
    this.route.navigate(['searchusers']);
  }

  handleAccounts() {
    this.route.navigate(['searchaccount']);
  }

  handleDomains() {
    this.route.navigate(['searchdomain']);
  }

  handleSettings() {
    this.route.navigate(['searchsettings']);
  }
}
