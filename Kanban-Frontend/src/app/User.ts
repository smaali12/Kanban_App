export class User{
    email!:string;
    firstName!:string;
    lastName!:string;
    projectlist:number[]=[];

    constructor(email:string,firstName:string,lastName:string){
        this.email=email;
        this.firstName=firstName;
        this.lastName=lastName;
    }
}