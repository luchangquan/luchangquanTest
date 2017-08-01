package com.renke.rdbao.daos.rdbao_2017.dao.mapper;

import com.renke.rdbao.beans.rdbao_2017.pojo.AROrganizationRole;
import com.renke.rdbao.daos.mapper.support.mapper.InsertListNotUseGeneratedKeyMapper;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

/**
 * @author jgshun
 * @date 2016-11-10 下午6:27:21
 * @version 1.0
 */
public interface IAROrganizationRoleMapper extends Mapper<AROrganizationRole>, MySqlMapper<AROrganizationRole>, InsertListNotUseGeneratedKeyMapper<AROrganizationRole> {

}
