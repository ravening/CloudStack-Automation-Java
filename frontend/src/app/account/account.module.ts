import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { SearchAccountComponent } from './search-account/search-account.component';
import {HttpClientModule} from "@angular/common/http";
import {FormsModule, ReactiveFormsModule} from "@angular/forms";

@NgModule({
  declarations: [SearchAccountComponent],
  imports: [
    CommonModule,
    HttpClientModule,
    FormsModule,
    ReactiveFormsModule
  ]
})
export class AccountModule { }
