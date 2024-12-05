import axios from "axios";

const auth = axios.create({
    baseURL : "http://localhost:8080/myblog/private/",
});

const allPermit = axios.create({
    baseURL : "http://localhost:8080/myblog/public/"
});

export const postCategory = async (category) => {
    return await auth.post("category", category);
};

export const putCategory = async (category) => {
    return await auth.put("category", category);
};

export const deleteCategory = async (categoryId) => {
    return await auth.delete("category", {
        params : {
            categoryId
        }
    });
};

export const getbychild = async (parentId) => {
    return await allPermit.get("category", {
        params : {
            parentId
        }
    });
};

export const getOneCategory = async (categoryId) => {
    return await allPermit.get(`category/${categoryId}`);
};