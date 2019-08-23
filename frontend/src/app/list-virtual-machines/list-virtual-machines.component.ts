import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {ListVirtualMachineApiService} from "../shared/list-virtual-machine-api.service";

@Component({
  selector: 'app-list-virtual-machines',
  templateUrl: './list-virtual-machines.component.html',
  styleUrls: ['./list-virtual-machines.component.css']
})
export class ListVirtualMachinesComponent implements OnInit {
  virtualmachineForm: FormGroup;
  output: any;
  isLoading = true;

  constructor(private virtualmachine: ListVirtualMachineApiService,
              private formBuilder: FormBuilder) { }

  ngOnInit() {
    this.virtualmachineForm = this.formBuilder.group({
      title: ['', Validators.required]
    });
  }

  onSubmit() {
    let name = this.virtualmachineForm.get('title').value;
    this.virtualmachine.getVirtualMachineByName(name)
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
        console.log("Searching for the virtual machine");
    }
      );
  }
}
