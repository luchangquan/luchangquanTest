package com.renke.rdbao.daos.rdbao_2017.dao.mapper;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

import com.renke.rdbao.beans.rdbao_2017.pojo.MREvidenceFile;
import com.renke.rdbao.daos.mapper.support.mapper.InsertListNotUseGeneratedKeyMapper;

/**
 * @author jgshun
 * @date 2016-11-10 下午6:27:21
 * @version 1.0
 */
public interface IMREvidenceFileMapper extends Mapper<MREvidenceFile>, MySqlMapper<MREvidenceFile>, InsertListNotUseGeneratedKeyMapper<MREvidenceFile> {

}
