package com.ssm.blog.service;

import com.ssm.blog.entity.Options;

/**
 * @author: xuzhi
 * @date: 2020/11/3 15:05
 */
public interface OptionsService {
    /**
     * 获得基本信息
     *
     * @return 系统设置
     */
    Options getOptions();

    /**
     * 新建基本信息
     *
     * @param options 系统设置
     */
    void insertOptions(Options options);

    /**
     * 更新基本信息
     *
     * @param options 系统设置
     */
    void updateOptions(Options options);
}
