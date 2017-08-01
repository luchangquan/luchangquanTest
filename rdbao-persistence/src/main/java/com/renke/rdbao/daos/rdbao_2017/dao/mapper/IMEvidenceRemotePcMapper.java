package com.renke.rdbao.daos.rdbao_2017.dao.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

import com.renke.rdbao.beans.rdbao_2017.enums.formevidence.StatusEnum4MEvidence;
import com.renke.rdbao.beans.rdbao_2017.pojo.MEvidenceRemotePc;

/**
 * @author jgshun
 * @date 2016-11-10 下午6:27:21
 * @version 1.0
 */
public interface IMEvidenceRemotePcMapper extends Mapper<MEvidenceRemotePc>, MySqlMapper<MEvidenceRemotePc> {

	long countTime4User(@Param("statuses") List<StatusEnum4MEvidence> statuses, @Param("nppCodes") List<String> nppCodes, @Param("userIds") List<String> userIds, @Param("startTime") String startTime,
			@Param("endTime") String endTime);
}
