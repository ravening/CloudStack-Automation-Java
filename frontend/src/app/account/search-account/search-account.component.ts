import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {AccountApiService} from "../../shared/account-api.service";

@Component({
  selector: 'app-search-account',
  templateUrl: './search-account.component.html',
  styleUrls: ['./search-account.component.css']
})
export class SearchAccountComponent implements OnInit {
  accountForm: FormGroup;
  output: any;
  isLoading = true;

  constructor(private formBuilder: FormBuilder,
              private accountService: AccountApiService) { }

  ngOnInit() {
    this.accountForm = this.formBuilder.group({
      title: ['', Validators.required]
    });
  }

  onSubmit() {
    let name = this.accountForm.get('title').value;
    this.accountService.getAccountDetails(name)
      .subscribe((data) => {
          this.output = data;
          this.isLoading = false;
        },
        error1 => {this.output = error1},
        () => {}
      );
  }
}
