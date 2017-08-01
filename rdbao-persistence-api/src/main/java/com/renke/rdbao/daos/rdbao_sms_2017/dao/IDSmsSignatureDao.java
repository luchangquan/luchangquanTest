package com.renke.rdbao.daos.rdbao_sms_2017.dao;

import com.renke.rdbao.beans.rdbao_sms_2017.pojo.DSmsSignature;
import com.renke.rdbao.daos.base.IBaseDao;

/**
 * 
 * @author 作者 jgshun
 * @date 创建时间 2016年07月22日 01时02分09秒
 */
public interface IDSmsSignatureDao extends IBaseDao<DSmsSignature> {

	DSmsSignature getByCode(String code);

}
