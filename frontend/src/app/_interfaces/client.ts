import { Sub } from "./sub";

export interface Client {
  id:number;
  firstName:string;
  lastName:string;
  phoneNumber:{
    phoneNumber:string;
  }
  emailAddress:{
    email:string;
  }
  subscription : Sub
}
