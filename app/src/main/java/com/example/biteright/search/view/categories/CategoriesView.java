package com.example.biteright.search.view.categories;

import com.example.biteright.model.Category;

public interface CategoriesView {
    public void showCategories(Category[] categories);
    public void showErrMsg(String error);
}
