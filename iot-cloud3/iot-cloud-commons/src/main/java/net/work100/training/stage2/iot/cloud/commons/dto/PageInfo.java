package net.work100.training.stage2.iot.cloud.commons.dto;

import lombok.Data;

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
@Data
public class PageInfo<T extends AbstractBaseDomain> implements Serializable {
    private int draw;
    private int recordsTotal;
    private int recordsFiltered;
    private List<T> data;
    private String error;
}
