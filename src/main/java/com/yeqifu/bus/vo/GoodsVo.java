package com.yeqifu.bus.vo;

import com.yeqifu.bus.entity.Goods;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @Author: ho
 * @Date: 2022/11/10 22:30
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class GoodsVo extends Goods {

    private Integer page=1;
    private Integer limit=10;

}
