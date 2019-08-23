import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {DomainApiService} from "../shared/domain-api.service";

@Component({
  selector: 'app-domain',
  templateUrl: './domain.component.html',
  styleUrls: ['./domain.component.css']
})
export class DomainComponent implements OnInit {
  domainForm: FormGroup;
  output: any;
  isLoading = true;

  constructor(private domainService: DomainApiService,
              private formBuilder: FormBuilder) { }

  ngOnInit() {
    this.domainForm = this.formBuilder.group({
      name: ['', Validators.required]
    });
  }

  onSubmit() {
    console.log('coming here');
    let name = this.domainForm.get('name').value;
    this.domainService.getDomainByName(name)
      .subscribe(data => {
          //this.output = JSON.stringify(data);
          this.output = data;
          this.isLoading = false;
        },
        error1 => {
          console.log('error happened ' + error1)
        }
        ,
        () => {
          console.log("Search completed");
        }
      );
  }

}
