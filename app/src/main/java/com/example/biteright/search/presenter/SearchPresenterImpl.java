//package com.example.biteright.search.presenter;
//
//
//import com.example.biteright.data.repo.MealRepository;
//import com.example.biteright.search.view.categories.CategoriesView;
//import io.reactivex.rxjava3.core.Observable;
//import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
//import io.reactivex.rxjava3.schedulers.Schedulers;
//import io.reactivex.rxjava3.subjects.PublishSubject;
//import io.reactivex.rxjava3.disposables.CompositeDisposable;
//import java.util.List;
//import java.util.concurrent.TimeUnit;
//
//public class SearchPresenterImpl implements SearchPresenter {
//
//    private CategoriesView categoriesView;
//    private MealRepository mealRepository;
//    private CompositeDisposable disposables;
//    private PublishSubject<String> searchSubject;
//
//    public SearchPresenterImpl(CategoriesView categoriesView, MealRepository mealRepository) {
//        this.categoriesView = categoriesView;
//        this.mealRepository = mealRepository;
//        this.disposables = new CompositeDisposable();
//        this.searchSubject = PublishSubject.create();
//    }
//
//    @Override
//    public void onSearchQueryChanged(String query) {
//        searchSubject.onNext(query);
//    }
//
//    @Override
//    public void getCategories() {
//        disposables.add(
//                searchSubject
//                        .debounce(300, TimeUnit.MILLISECONDS)
//                        .distinctUntilChanged()
//                        .flatMap(query -> mealRepository.getCategories(query))
//                        .subscribeOn(Schedulers.io())
//                        .observeOn(AndroidSchedulers.mainThread())
//                        .subscribe(
//                                categories -> categoriesView.showCategories(categories),
//                                throwable -> categoriesView.showErrMsg(throwable.getMessage())
//                        )
//        );
//    }
//
//    public void clear() {
//        disposables.clear();
//    }
//}
