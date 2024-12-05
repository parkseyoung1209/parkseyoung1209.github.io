import { createSlice, createAsyncThunk } from "@reduxjs/toolkit";
import { postCategory, putCategory, deleteCategory, getbychild, getOneCategory } from "../api/category";

export const sliceByPostCategory = createAsyncThunk(
    "category/sliceByPostCategory",
    async(category) => {
        await postCategory(category);
    }
);

export const sliceByPutCategory = createAsyncThunk(
    "category/silceByPutCategory",
    async(category) => {
        await putCategory(category);
    }
);

export const sliceByDeleteCategory = createAsyncThunk(
    "category/sliceByDeleteCategory",
    async(categoryId) => {
        await deleteCategory(categoryId);
    }
);

export const sliceByReadChildren = createAsyncThunk(
    "category/sliceByReadChildren",
    async(parentId) => {
        const children = await getbychild(parentId);
        return children.data;
    }
);

export const sliceByReadOneCategory = createAsyncThunk(
    "category/sliceByReadOneCategory",
    async(categoryId) => {
        const category = await getOneCategory(categoryId);
        return category.data;
    }
);

const categorySlice = createSlice({
    name : "categorySlice",
    initialState: {
        childrenCategory : [],
        oneCategory : {}
    },
    extraReducers : (builder) => {
        builder
        .addCase(sliceByReadChildren.fulfilled, (state, action) => {
            return {
                ...state,
                childrenCategory : action.payload.childrenCategory
            }
    })
        .addCase(sliceByReadOneCategory.fulfilled, (state, action) => {
            return {
                ...state,
                oneCategory : action.payload.oneCategory
            }
        })
    }
});

export default categorySlice;