package com.example.biteright.search.model.categories;

import com.example.biteright.network.CategoriesNetworkCallback;
import com.example.biteright.network.NetworkCallback;

public interface CategoriesRepository {
    void getCategories(CategoriesNetworkCallback categoriesNetworkCallback);

}
