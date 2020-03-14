package net.work100.training.stage2.iot.cloud.commons.dto;

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
public abstract class AbstractBaseDomain implements Serializable {
    private Long id;
    private Date created;
    private Date updated;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getUpdated() {
        return updated;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }
}
