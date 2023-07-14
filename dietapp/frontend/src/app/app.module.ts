import {ErrorHandler, NgModule} from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginComponent } from './login/login.component';
import { LandingComponent } from './landing/landing.component';
import { SingupComponent } from './singup/singup.component';
import { NotFoundComponent } from './not-found/not-found.component';
import {ReactiveFormsModule} from "@angular/forms";
import {HTTP_INTERCEPTORS, HttpClientModule} from "@angular/common/http";
import { RoleUserComponent } from './role-user/role-user.component';
import { RoleAdminComponent } from './role-admin/role-admin.component';
import { DashboardComponent } from './dashboard/dashboard.component';
import { WeightHistoryComponent } from './weight-history/weight-history.component';
import { AddOrEditWeightComponent } from './addoredit-weight/add-or-edit-weight.component';
import { ChartWeightComponent } from './chart-weight/chart-weight.component';
import {NgxEchartsModule} from "ngx-echarts";
import {MaterialModule} from "./MaterialModule";
import {HttpInterceptorService} from "./services/http-interceptor.service";
import {ToastrModule} from "ngx-toastr";


@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    LandingComponent,
    SingupComponent,
    NotFoundComponent,
    RoleUserComponent,
    RoleAdminComponent,
    DashboardComponent,
    WeightHistoryComponent,
    AddOrEditWeightComponent,
    ChartWeightComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    ReactiveFormsModule,
    HttpClientModule,
    NgxEchartsModule.forRoot({
      echarts: () => import('echarts')
    }),
    MaterialModule,
    ToastrModule.forRoot()

  ],
  providers: [
    {
      provide: HTTP_INTERCEPTORS,
      useClass: HttpInterceptorService,
      multi:true
    }
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
