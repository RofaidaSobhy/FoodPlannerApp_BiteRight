package com.example.biteright.search.view.area;

import com.example.biteright.data.models.POJO.Area;

import java.util.List;

public interface AreaView {
    public void showAreas(List<Area> areas);
    public void showErrMsg(String error);
}
