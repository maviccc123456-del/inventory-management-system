package com.yeqifu.sys.vo;

import com.yeqifu.sys.entity.Role;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @Author: ho
 * @Date: 2022/11/10 20:42
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class RoleVo extends Role {

    private Integer page=1;
    private Integer limit=10;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date startTime;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date endTime;

}
