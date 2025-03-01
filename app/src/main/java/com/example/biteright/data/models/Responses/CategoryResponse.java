package com.example.biteright.data.models.Responses;

import com.example.biteright.data.models.POJO.Category;

import java.util.ArrayList;
import java.util.List;

public class CategoryResponse {
    public List<Category> categories;

    public List<Category> getCategories() {
        return categories != null ? categories : new ArrayList<>();
    }
}
