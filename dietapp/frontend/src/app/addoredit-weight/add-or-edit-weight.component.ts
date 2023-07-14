import {Component, EventEmitter, Inject, Input, OnInit, Output} from '@angular/core';
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {Weight} from "../models/weight.model";
import {UserService} from "../services/user.service";
import {DateAdapter, MAT_DATE_FORMATS, MAT_DATE_LOCALE} from '@angular/material/core';
@Component({
  selector: 'app-add-weight',
  templateUrl: './add-or-edit-weight.component.html',
  styleUrls: ['./add-or-edit-weight.component.css'],
  providers: [
    {provide: MAT_DATE_LOCALE, useValue: 'DD/MM/YYYY'},
  ]
})
export class AddOrEditWeightComponent implements OnInit {
  @Input() weight:Weight|null=null;
  @Output() close = new EventEmitter();
  @Output() refresh = new EventEmitter();
  weightForm!:FormGroup;
  maxDate!:Date;
  constructor(
    private http:UserService,
    private _adapter: DateAdapter<any>,
    @Inject(MAT_DATE_LOCALE) private _locale: string,
  ) { }

  ngOnInit(): void {
    this._adapter.setLocale('en-GB')
    this.maxDate = new Date;
    if(this.weight==null){
      this.createFormToAdd();
    }else{
      this.createFormToEdit()
    }

  }

  createFormToAdd(){
    this.weightForm = new FormGroup({
      date: new FormControl(new Date, Validators.required),
      weight: new FormControl('', Validators.required),
    });
  }
  createFormToEdit(){
    this.weightForm= new FormGroup({
      id: new FormControl(this.weight!.id),
      date: new FormControl(this.weight!.date, Validators.required),
      weight: new FormControl(this.weight!.weight, Validators.required),
    })
  }

  onSubmit(){
    if(this.weightForm.value.id==undefined||this.weightForm.value.id==null){
      this.http.addWeight(new Weight(-1, this.weightForm.value.weight, this.weightForm.value.date)).subscribe(
        data => {
          console.log('parece que agregamos un registro de peso');
          this.refresh.emit();
          this.onCloseClick();
          },
        error => {console.log('error al intentar agregar un peso')})
    }else{
      this.http.updateWeight(new Weight(this.weightForm.value.id, this.weightForm.value.weight, this.weightForm.value.date)).subscribe(
        data => {
          console.log('editamos un peso');
          this.refresh.emit();
          this.onCloseClick();
        },
        error => {console.log('error al editar peso')}
      )
    }
  }

  onCloseClick(){
    this.close.emit();
  }

  deleteWeight(){
    this.http.deleteWeight(this.weightForm.value.id).subscribe(
      data => {
        console.log('borramos un peso');
        this.onCloseClick();
        this.refresh.emit();
      },
      error => {console.log('error al borrar peso')}
    )
  }

}
