package com.application.teleshopnative;


import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.AbsListView;

import java.util.List;
import java.util.regex.Pattern;

/**
 * Created by yqritc on 2015/03/20.
 */
public class SampleEnumMapAdapter extends EnumMapBindAdapter<SampleEnumMapAdapter.SampleViewType> {


    private int[] order;

    public void setOrder(int[] order) {
        this.order = order;
    }



    enum SampleViewType {
        SAMPLE1, SAMPLE2, SAMPLE3, SAMPLE6, SAMPLE5, SAMPLE7, SAMPLE4
    }

    int flag = 0;

    public SampleEnumMapAdapter() {
        putBinder(SampleViewType.SAMPLE1, new Sample1Binder(this));
        putBinder(SampleViewType.SAMPLE2, new Sample2Binder(this));
        putBinder(SampleViewType.SAMPLE3, new Sample3Binder(this));
        putBinder(SampleViewType.SAMPLE4, new Sample4Binder(this));
        putBinder(SampleViewType.SAMPLE5, new Sample5Binder(this));
        putBinder(SampleViewType.SAMPLE6, new Sample6Binder(this));
        putBinder(SampleViewType.SAMPLE7, new Sample7Binder(this));


    }

    public void setSample4Data(List<SampleData4> dataSet4) {
        ((Sample4Binder) getDataBinder(SampleViewType.SAMPLE4)).addAll(dataSet4);
    }

    public void setSample2Data(List<List<SampleData2>> dataSet2) {
        ((Sample2Binder) getDataBinder(SampleViewType.SAMPLE2)).addAll(dataSet2);
    }

    public void setSample1Data(List<SampleData> dataSet) {
        ((Sample1Binder) getDataBinder(SampleViewType.SAMPLE1)).addAll(dataSet);
    }

    public void setSample3Data(List<SampleData3> dataSet3) {
        ((Sample3Binder) getDataBinder(SampleViewType.SAMPLE3)).addAll(dataSet3);
    }

    public void setSample5Data(List<SampleData5> dataSet5) {
        ((Sample5Binder) getDataBinder(SampleViewType.SAMPLE5)).addAll(dataSet5);
    }

    public void setSample6Data(List<List<SampleData6>> dataSet6) {
        ((Sample6Binder) getDataBinder(SampleViewType.SAMPLE6)).addAll(dataSet6);
    }

    public void setSample7Data(List<SampleData7> dataSet7) {
        ((Sample7Binder) getDataBinder(SampleViewType.SAMPLE7)).addAll(dataSet7);
    }


    @Override
    public SampleViewType getEnumFromPosition(int position) {


        int abc = order[position];

        if (abc == 2) {
            return SampleViewType.SAMPLE2;
        } else if (abc == 3) {
            return SampleViewType.SAMPLE3;
        } else if (abc == 5) {
            return SampleViewType.SAMPLE5;
        } else if (abc == 6) {
            return SampleViewType.SAMPLE6;
        } else if (abc == 1) {
            return SampleViewType.SAMPLE1;
        } else if (abc == 7) {
            return SampleViewType.SAMPLE7;
        } else {
            return SampleViewType.SAMPLE4;
        }
    }

    @Override
    public SampleViewType getEnumFromOrdinal(int ordinal) {
        return SampleViewType.values()[ordinal];
    }
}
