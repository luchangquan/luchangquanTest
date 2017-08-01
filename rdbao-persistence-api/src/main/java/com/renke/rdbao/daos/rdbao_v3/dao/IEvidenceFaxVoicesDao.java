package com.renke.rdbao.daos.rdbao_v3.dao;

import java.util.List;

import com.renke.rdbao.beans.rdbao_v3.pojo.EvidenceFaxVoices;
import com.renke.rdbao.daos.base.IBaseDao;

/**
 * 
 * @author 作者 jgshun
 * @date 创建时间 2016年07月22日 01时02分09秒
 */
public interface IEvidenceFaxVoicesDao extends IBaseDao<EvidenceFaxVoices> {

	List<EvidenceFaxVoices> getListByIdsOrderByCreateTimeDesc(List<String> ids);

}
