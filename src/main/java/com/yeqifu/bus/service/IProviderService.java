package com.yeqifu.bus.service;

import com.yeqifu.bus.entity.Provider;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * InnoDB free: 9216 kB 服务类
 * </p>
 *
 * @author ho
 * @since 2022-11-10
 */
public interface IProviderService extends IService<Provider> {

    /**
     * 根据供应商ID删除供应商
     * @param id
     */
    void deleteProviderById(Integer id);
}
