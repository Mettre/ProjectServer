package com.example.myproject.base;

import com.example.myproject.constant.CommonConstant;
import com.example.myproject.utils.SnowFlakeUtil;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.EntityListeners;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.util.Date;

/**
 * @author Exrickx
 */
@Data
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler", "fieldHandler"})
public abstract class MettreBaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @ApiModelProperty(value = "唯一标识", hidden = true)
    public Long id = SnowFlakeUtil.getFlowIdInstance().nextId();

    @ApiModelProperty(value = "创建者", hidden = true)
    public String createBy;

    @CreatedDate
    @ApiModelProperty(value = "创建时间", hidden = true)
    public Date createTime = new Date();

    @ApiModelProperty(value = "更新者", hidden = true)
    public String updateBy;

    @LastModifiedDate
    @ApiModelProperty(value = "更新时间", hidden = true)
    public Date updateTime = new Date();

    @ApiModelProperty(value = "删除标志 默认0", hidden = true)
    public Integer delFlag = CommonConstant.STATUS_NORMAL;
}
