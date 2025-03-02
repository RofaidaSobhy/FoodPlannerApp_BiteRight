package com.example.biteright.search.view.categories;

import com.example.biteright.data.models.POJO.Category;

import java.util.List;

public interface CategoriesView {
    public void showCategories(List<Category> categories);
    public void showErrMsg(String error);
}
