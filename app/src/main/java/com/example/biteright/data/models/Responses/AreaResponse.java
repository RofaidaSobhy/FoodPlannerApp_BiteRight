package com.example.biteright.data.models.Responses;

import com.example.biteright.data.models.POJO.Area;
import java.util.ArrayList;
import java.util.List;

public class AreaResponse {
    public List<Area> meals;

    public List<Area> getMeals() {
        return meals != null ? meals : new ArrayList<>();
    }
}
