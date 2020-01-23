import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {ListZonesComponent} from "./list-zones/list-zones.component";
import {ListVirtualMachinesComponent} from "./list-virtual-machines/list-virtual-machines.component";
import {SearchUserComponent} from "./user/search-user/search-user.component";
import {SearchAccountComponent} from "./account/search-account/search-account.component";
import {DomainComponent} from "./domain/domain.component";
import {GlobalSettingComponent} from "./global-setting/global-setting.component";

const routes: Routes = [
  {
    path: 'listzones',
    component: ListZonesComponent
  },
  {
    path: 'listvirtualmachines',
    component: ListVirtualMachinesComponent
  },
  {
    path: 'searchusers',
    component: SearchUserComponent
  }, {
    path: 'searchaccount',
    component: SearchAccountComponent
  }, {
    path: 'searchdomain',
    component: DomainComponent
  }, {
    path: 'searchsettings',
    component: GlobalSettingComponent
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
