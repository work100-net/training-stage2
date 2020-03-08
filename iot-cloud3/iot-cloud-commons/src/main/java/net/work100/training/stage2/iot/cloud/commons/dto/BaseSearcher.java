package net.work100.training.stage2.iot.cloud.commons.dto;

import java.io.Serializable;

/**
 * <p>Title: Searcher</p>
 * <p>Description: </p>
 *
 * @author liuxiaojun
 * @date 2020-03-08 16:45
 * ------------------- History -------------------
 * <date>      <author>       <desc>
 * 2020-03-08   liuxiaojun     初始创建
 * -----------------------------------------------
 */
public abstract class BaseSearcher implements Serializable {
    private String keyword;
    private boolean advanced;

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public boolean isAdvanced() {
        return advanced;
    }

    public void setAdvanced(boolean advanced) {
        this.advanced = advanced;
    }
}
