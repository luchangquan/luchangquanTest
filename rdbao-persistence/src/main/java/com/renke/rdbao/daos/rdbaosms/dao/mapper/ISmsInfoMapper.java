package com.renke.rdbao.daos.rdbaosms.dao.mapper;

import com.renke.rdbao.beans.rdbaosms.pojo.SmsInfo;
import com.renke.rdbao.daos.mapper.support.mapper.InsertListNotUseGeneratedKeyMapper;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

/**
 * @author jgshun
 * @date 2016-11-10 下午6:27:21
 * @version 1.0
 */
public interface ISmsInfoMapper extends Mapper<SmsInfo>, MySqlMapper<SmsInfo>, InsertListNotUseGeneratedKeyMapper<SmsInfo> {

}
