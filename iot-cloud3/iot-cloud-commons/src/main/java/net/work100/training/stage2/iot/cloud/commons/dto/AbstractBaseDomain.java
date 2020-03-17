package net.work100.training.stage2.iot.cloud.commons.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>Title: AbstractBaseDomain</p>
 * <p>Description: </p>
 *
 * @author liuxiaojun
 * @date 2020-03-14 14:44
 * ------------------- History -------------------
 * <date>      <author>       <desc>
 * 2020-03-14   liuxiaojun     初始创建
 * -----------------------------------------------
 */
@Data
public abstract class AbstractBaseDomain implements Serializable {
    private Long id;
    private Date created;
    private Date updated;
}
