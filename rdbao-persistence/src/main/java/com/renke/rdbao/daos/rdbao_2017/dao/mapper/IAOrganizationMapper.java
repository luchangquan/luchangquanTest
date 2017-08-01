package com.renke.rdbao.daos.rdbao_2017.dao.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.renke.rdbao.beans.rdbao_2017.pojo.AOrganization;
import com.renke.rdbao.daos.mapper.support.mapper.InsertListNotUseGeneratedKeyMapper;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

/**
 * @author jgshun
 * @date 2016-11-10 下午6:27:21
 * @version 1.0
 */
public interface IAOrganizationMapper extends Mapper<AOrganization>, MySqlMapper<AOrganization>, InsertListNotUseGeneratedKeyMapper<AOrganization> {

	void updateParentId(@Param("organizations") List<AOrganization> organizations);

	void updateAllName(@Param("organizations") List<AOrganization> organizations);

}
