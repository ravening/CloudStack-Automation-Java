import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { ZoneComponent } from './zone/zone.component';
import { ListZonesComponent } from './list-zones/list-zones.component';
import {ListZonesApiService} from "./shared/list-zones-api.service";
import {ListVirtualMachineApiService} from "./shared/list-virtual-machine-api.service";
import {HttpClientModule} from "@angular/common/http";
import { ListVirtualMachinesComponent } from './list-virtual-machines/list-virtual-machines.component';
import {FormBuilder, FormsModule, ReactiveFormsModule} from "@angular/forms";
import {UserModule} from "./user/user.module";
import {AccountModule} from "./account/account.module";
import {UserApiService} from "./shared/user-api.service";
import { DomainComponent } from './domain/domain.component';
import {DomainApiService} from "./shared/domain-api.service";
import { GlobalSettingComponent } from './global-setting/global-setting.component';

@NgModule({
  declarations: [
    AppComponent,
    ZoneComponent,
    ListZonesComponent,
    ListVirtualMachinesComponent,
    DomainComponent,
    GlobalSettingComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    HttpClientModule,
    FormsModule,
    ReactiveFormsModule,
    UserModule,
    AccountModule
  ],
  providers: [ListZonesApiService, ListVirtualMachineApiService, UserApiService, DomainApiService],
  bootstrap: [AppComponent]
})
export class AppModule { }
