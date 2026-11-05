package com.yeqifu.sys.vo;

import com.yeqifu.sys.entity.User;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @Author: ho
 * @Date: 2022/11/10 8:21
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class UserVo extends User {

    private Integer page=1;
    private Integer limit=10;

    /**
     * 验证码
     */
    private String code;
}
