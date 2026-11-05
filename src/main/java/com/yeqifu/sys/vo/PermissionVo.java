package com.yeqifu.sys.vo;

import com.yeqifu.sys.entity.Permission;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @Author: ho
 * @Date: 2022/11/10 15:30
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class PermissionVo extends Permission {

    private Integer page=1;
    private Integer limit=10;
}
