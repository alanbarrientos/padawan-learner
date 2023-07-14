export class User{
  userName: string;
  email: string;
  password: string;
  roles: string[];
  isMale:boolean
  constructor(userName: string, email: string, password: string, isMale: boolean) {
    this.userName = userName;
    this.email = email;
    this.password = password;
    this.roles  = ['user'];
    this.isMale = isMale;
  }
}
