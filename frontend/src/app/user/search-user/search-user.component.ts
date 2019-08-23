import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {UserApiService} from "../../shared/user-api.service";

@Component({
  selector: 'app-search-user',
  templateUrl: './search-user.component.html',
  styleUrls: ['./search-user.component.css']
})
export class SearchUserComponent implements OnInit {
  userForm: FormGroup;
  output: any;
  isLoading = true;

  constructor(private formBuilder: FormBuilder,
              private userService: UserApiService) { }

  ngOnInit() {
    this.userForm = this.formBuilder.group({
      title: ['', Validators.required]
    });
  }

  onSubmit() {
    let name = this.userForm.get('title').value;
    this.userService.searchUser(name)
      .subscribe((data) => {
        this.output = data;
        this.isLoading = false;
      },
        error1 => {this.output = error1},
        () => {}
        );
  }

  logForm(value) {
    console.log(value.username);
  }
}
