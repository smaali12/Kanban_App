import { User } from "./User";

export class UserWithPassword{
    user!:User;
    password!:string;
    
    constructor(user:User,password:string){
        this.user=user;
        this.password=password;
    }
}