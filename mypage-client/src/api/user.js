import axios, { Axios } from "axios";

const permit = axios.create({
    baseURL : "http://localhost:8080/myblog/public/"
})

export const adminLogin = async(userId, userPassword) => {
   return await permit.get("user", {
    params : {
        userId,
        userPassword
    }
   });
}