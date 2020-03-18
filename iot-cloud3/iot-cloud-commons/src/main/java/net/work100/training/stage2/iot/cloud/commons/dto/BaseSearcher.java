package net.work100.training.stage2.iot.cloud.commons.dto;

import lombok.Data;

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
@Data
public abstract class BaseSearcher implements Serializable {
    private boolean advanced;
    private String keyword;

    private int start;
    private int length;
}
