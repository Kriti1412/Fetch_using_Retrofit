package com.example.task1;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class apiRepoList {
    @SerializedName("worldpopulation")
    private List<apiRepo> finalList;

    public List<apiRepo> getFinalList() {
        return finalList;
    }

    public void setFinalList(List<apiRepo> finalList) {
        this.finalList = finalList;
    }
}
