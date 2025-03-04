package com.example.biteright.search.presenter;

import com.example.biteright.data.repo.MealRepository;
import com.example.biteright.search.view.SearchingView;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class SearchPresenterImpl implements SearchPresenter{

    private SearchingView _view;
    private MealRepository _repo;

    public SearchPresenterImpl(SearchingView _view, MealRepository _repo) {
        this._view = _view;
        this._repo = _repo;
    }


    @Override
    public void onSearchQueryChanged(String query) {
        if(query.trim().isEmpty()){
            return;
        }
        _repo.getMeals(query)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(result ->{
                        if(result !=null && !result.isEmpty()){
                            _view.showData(result);
                        }else {
                            _view.showErrMsg("No meals had founded.");
                        }
                    }, error ->{
                        _view.showErrMsg(error.getLocalizedMessage());
                });
    }
}
