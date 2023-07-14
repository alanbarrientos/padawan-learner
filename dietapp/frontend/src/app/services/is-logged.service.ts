import {BehaviorSubject} from 'rxjs';
import {Injectable} from "@angular/core";

@Injectable({providedIn: 'root'})
export class IsLoggedService {
  itemValue = new BehaviorSubject(this.theItem);

  set theItem(value) {
    this.itemValue.next(value); // this will make sure to tell every subscriber about the change.
    if(value!=null){
      localStorage.setItem('username', value);
    }else{
      localStorage.clear()
    }
  }

  get theItem() {
    return localStorage.getItem('username');
  }
}
