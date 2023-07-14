export default class User{
    userName;
    email;
    password;
    roles;
    isMale
    constructor(userName, email, password, isMale) {
        this.userName = userName;
        this.email = email;
        this.password = password;
        this.roles  = ['user'];
        this.isMale = isMale;
    }
}
