package net.work100.training.stage2.iot.cloud.commons.dto;

import java.io.Serializable;
import java.util.List;

/**
 * <p>Title: PageInfo</p>
 * <p>Description: </p>
 *
 * @author liuxiaojun
 * @date 2020-03-14 14:37
 * ------------------- History -------------------
 * <date>      <author>       <desc>
 * 2020-03-14   liuxiaojun     初始创建
 * -----------------------------------------------
 */
public class PageInfo<T extends AbstractBaseDomain> implements Serializable {
    private int draw;
    private int recordsTotal;
    private int recordsFiltered;
    private List<T> data;
    private String error;

    public int getDraw() {
        return draw;
    }

    public void setDraw(int draw) {
        this.draw = draw;
    }

    public int getRecordsTotal() {
        return recordsTotal;
    }

    public void setRecordsTotal(int recordsTotal) {
        this.recordsTotal = recordsTotal;
    }

    public int getRecordsFiltered() {
        return recordsFiltered;
    }

    public void setRecordsFiltered(int recordsFiltered) {
        this.recordsFiltered = recordsFiltered;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
