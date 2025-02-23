package com.example.biteright.search.network.categories;

import com.example.biteright.network.CategoriesNetworkCallback;
import com.example.biteright.network.NetworkCallback;

public interface CategoriesRemoteDataSource {
    void makeNetworkCall(CategoriesNetworkCallback networkCallback);

}
