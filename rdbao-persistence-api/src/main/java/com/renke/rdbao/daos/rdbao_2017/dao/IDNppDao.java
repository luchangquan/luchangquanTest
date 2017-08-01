package com.renke.rdbao.daos.rdbao_2017.dao;

import java.util.List;

import com.renke.rdbao.beans.rdbao_2017.pojo.DNpp;
import com.renke.rdbao.daos.base.IBaseDao;

/**
 * 
 * @author 作者 jgshun
 * @date 创建时间 2016年07月22日 01时02分09秒
 */
public interface IDNppDao extends IBaseDao<DNpp> {

	DNpp getByCode(String code);

	List<DNpp> getByCodes(List<String> codes);

}
