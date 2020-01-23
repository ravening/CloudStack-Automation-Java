import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {GlobalSettingService} from "../shared/global-setting.service";

@Component({
  selector: 'app-global-setting',
  templateUrl: './global-setting.component.html',
  styleUrls: ['./global-setting.component.css']
})
export class GlobalSettingComponent implements OnInit {

  settingsForm: FormGroup;
  output: any;
  isLoading = true;
  constructor(private settingsService: GlobalSettingService,
              private formBuilder: FormBuilder) { }

  ngOnInit() {
    this.settingsForm = this.formBuilder.group({
      name: ['', Validators.required]
    });
  }

  onSubmit() {
    let name = this.settingsForm.get('name').value;
    this.settingsService.getSettingByName(name)
      .subscribe(data => {
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
