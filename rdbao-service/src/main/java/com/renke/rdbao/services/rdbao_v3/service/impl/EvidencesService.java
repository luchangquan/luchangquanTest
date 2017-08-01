package com.renke.rdbao.services.rdbao_v3.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import tk.mybatis.mapper.entity.Example;

import com.google.common.collect.Lists;
import com.renke.rdbao.beans.common.enums.ResponseEnum;
import com.renke.rdbao.beans.common.enums.StatusEnum4User;
import com.renke.rdbao.beans.common.enums.UserTableEnum;
import com.renke.rdbao.beans.common.enums.UserTypeEnum;
import com.renke.rdbao.beans.common.exception.UserContextException;
import com.renke.rdbao.beans.common.pojo.base.BasePo;
import com.renke.rdbao.beans.common.pojo.enhanced.base.BaseEnhanced;
import com.renke.rdbao.beans.common.vo.Order;
import com.renke.rdbao.beans.common.vo.Pagination;
import com.renke.rdbao.beans.common.vo.context.UserContext;
import com.renke.rdbao.beans.rdbao_v3.enums.forenvidences.CategoryEnum4Evidences;
import com.renke.rdbao.beans.rdbao_v3.enums.forenvidences.DeletedEnum4Evidences;
import com.renke.rdbao.beans.rdbao_v3.enums.forenvidences.StateEnum4Evidences;
import com.renke.rdbao.beans.rdbao_v3.pojo.Evidences;
import com.renke.rdbao.beans.rdbao_v3.pojo.User189;
import com.renke.rdbao.beans.rdbao_v3.pojo.Users;
import com.renke.rdbao.beans.rdbao_v3.pojo.enhanced.EnhancedEvidences;
import com.renke.rdbao.beans.rdbao_v3.pojo.enhanced.EnhancedPNOes;
import com.renke.rdbao.beans.rdbao_v3.pojo.enhanced.EnhancedREvidencesFile;
import com.renke.rdbao.beans.rdbao_v3.query.EvidencesQuery;
import com.renke.rdbao.daos.rdbao_v3.dao.IEvidencesDao;
import com.renke.rdbao.daos.rdbao_v3.dao.IUser189Dao;
import com.renke.rdbao.daos.rdbao_v3.dao.IUsersDao;
import com.renke.rdbao.services.base.impl.BaseService;
import com.renke.rdbao.services.rdbao_v3.service.IEvidenceAppVideoService;
import com.renke.rdbao.services.rdbao_v3.service.IEvidenceAppVoiceService;
import com.renke.rdbao.services.rdbao_v3.service.IEvidenceFaxVoicesService;
import com.renke.rdbao.services.rdbao_v3.service.IEvidencePictureService;
import com.renke.rdbao.services.rdbao_v3.service.IEvidenceVideosService;
import com.renke.rdbao.services.rdbao_v3.service.IEvidencesService;
import com.renke.rdbao.services.rdbao_v3.service.IPNOesService;
import com.renke.rdbao.services.rdbao_v3.service.IREvidencesFileService;
import com.renke.rdbao.util.Detect;

/**
 * @author jgshun
 * @date 2016-11-11 上午11:09:11
 * @version 1.0
 */
public class EvidencesService extends BaseService<Evidences> implements IEvidencesService {
	private static final Logger _LOGGER = LoggerFactory.getLogger(EvidencesService.class);
	@Autowired
	private IEvidencesDao evidencesDao;
	@Autowired
	private IUser189Dao user189Dao;
	@Autowired
	private IPNOesService pnoesService;
	@Autowired
	private IUsersDao usersDao;
	@Autowired
	private IEvidenceFaxVoicesService evidenceFaxVoicesService;
//	@Autowired
	private IEvidenceVideosService evidenceVideosService;
//	@Autowired
	private IEvidenceAppVideoService evidenceAppVideoService;
	
	
//	@Autowired
	private IEvidenceAppVoiceService evidenceAppVoiceService;
//	@Autowired
	private IEvidencePictureService evidencePictureService;
//	@Autowired
	private IREvidencesFileService rEvidencesFileService;

	@Override
	public Pagination<EnhancedEvidences> getPagination(Date startTime, Date endTime, String searchPhoneNo, List<CategoryEnum4Evidences> categories, List<DeletedEnum4Evidences> deleteds,
			List<StateEnum4Evidences> states, Pagination<EnhancedEvidences> pagination, UserContext userContext) throws UserContextException {
		if (userContext == null) {
			throw new UserContextException(ResponseEnum.USER_CONTEXT_CANNOT_BE_EMPTY);
		}
		EvidencesQuery evidencesQuery = new EvidencesQuery();
		if (startTime != null) {
			evidencesQuery.setEqualAndBeforCreateTime(startTime);
		}
		if (endTime != null) {
			evidencesQuery.setEqualAndAfterCreateTime(endTime);
		}
		evidencesQuery.setCategories(categories);
		evidencesQuery.setDeleteds(deleteds);
		evidencesQuery.setStates(states);

		List<String> searchUserIds = new ArrayList<String>(userContext.getContainUserIds());

		if (Detect.notEmpty(searchPhoneNo) && userContext.getUser().getType() == UserTypeEnum.MANAGER.getCode()) {// 搜索手机号不为空
																													// 且为管理员
			// TODO 暂时按照普通用户与管理员分
			List<String> userIds = this.getListByPhoneNo4Company(searchPhoneNo, userContext.getUser().getCompanyId(), Lists.newArrayList(UserTypeEnum.PERSONAL),
					Lists.newArrayList(StatusEnum4User.BUSINESS_OPENED, StatusEnum4User.BUSINESS_CLOSED), userContext);

			if (!Detect.notEmpty(userIds)) {// 公司下没有这个手机用户
				return pagination;
			}
			searchUserIds = userIds;

		}

		evidencesQuery.setUserIds(searchUserIds);
		if (!Detect.notEmpty(pagination.getOrders())) {
			pagination.addOrder(new Order(Evidences.COLUMN_CREATETIME, Order.ORDER_DESC));
		}
		return this.getPagination(evidencesQuery, pagination, userContext);
	}

	private List<String> getListByPhoneNo4Company(String searchPhoneNo, String companyId, List<UserTypeEnum> types, List<StatusEnum4User> statuses, UserContext userContext) {
		List<String> userIds = new ArrayList<String>();
		if (userContext.getUserTable() == UserTableEnum.E_189_USER) {
			// TODO 现在只做了公正录音
			List<User189> user189s = user189Dao.getListByPhoneNo4Company(searchPhoneNo, companyId, types, statuses);
			for (User189 _User189 : user189s) {
				userIds.add(_User189.getId());
			}
		} else if (userContext.getUserTable() == UserTableEnum.USERS) {
			// TODO 实时保 待办
		}

		return userIds;
	}

	@Override
	public Pagination<EnhancedEvidences> getPagination4FaxVoiceUser189NotInCompany(Date startTime, Date endTime, String searchPhoneNo, List<DeletedEnum4Evidences> deleteds,
			List<StateEnum4Evidences> states, Pagination<EnhancedEvidences> pagination, UserContext userContext) {
		Pagination<Evidences> evidencesPagination = evidencesDao.getPagination4FaxVoiceUser189NotInCompany(startTime, endTime, searchPhoneNo, deleteds, states, userContext.getContainUserIds(),
				pagination.copy());
		pagination.setCount(evidencesPagination.getCount());
		if (!Detect.notEmpty(evidencesPagination.getItems())) {
			return pagination;
		}
		pagination.setItems(this.convent2Enhanceds(evidencesPagination.getItems()));
		return pagination;
	}

	@Override
	public List<EnhancedEvidences> getEnhanceds(List ids, UserContext userContext) {
		List<Evidences> evidences = evidencesDao.getListByKeyValues(Evidences.FIELD_ID, ids, Evidences.class);
		if (!Detect.notEmpty(evidences)) {
			return null;
		}

		return this.convent2Enhanceds(evidences);
	}

	@Override
	public Pagination<EnhancedEvidences> getPagination(EvidencesQuery evidencesQuery, Pagination<EnhancedEvidences> pagination, UserContext userContext) {
		@SuppressWarnings("unchecked")
		Pagination<Evidences> evidencesPagination = evidencesDao.getPagination(evidencesQuery, pagination.copy());
		pagination.setCount(evidencesPagination.getCount());
		if (!Detect.notEmpty(evidencesPagination.getItems())) {
			return pagination;
		}
		pagination.setItems(this.convent2Enhanceds(evidencesPagination.getItems()));
		return pagination;
	}

	@Override
	public List<EnhancedEvidences> appendEnhancedCommons(List<? extends BaseEnhanced> enhancedItems, UserContext userContext) {
		@SuppressWarnings("unchecked")
		List<EnhancedEvidences> enhancedEvidences = (List<EnhancedEvidences>) enhancedItems;
		enhancedEvidences = this.appendEnhancedPNOes(enhancedEvidences, userContext);
		// TODO
		return enhancedEvidences;
	}

	@Override
	public List<EnhancedEvidences> appendEnhancedPNOes(List<EnhancedEvidences> enhancedEvidences, UserContext userContext) {
		Set<Object> pnoIds = new HashSet<Object>();
		for (EnhancedEvidences _EnhancedEvidences : enhancedEvidences) {
			if (_EnhancedEvidences.getEnhancedPNOes() != null) {
				pnoIds.add(_EnhancedEvidences.getEnhancedPNOes().getId());
			}
		}
		if (!Detect.notEmpty(pnoIds)) {
			return enhancedEvidences;
		}

		@SuppressWarnings("unchecked")
		List<EnhancedPNOes> enhancedPNOes = (List<EnhancedPNOes>) pnoesService.getEnhanceds(new ArrayList<Object>(pnoIds), userContext);
		if (!Detect.notEmpty(enhancedPNOes)) {
			return enhancedEvidences;
		}

		this.appendEnhancedPNOes(enhancedEvidences, enhancedPNOes, userContext);
		return enhancedEvidences;
	}

	private void appendEnhancedPNOes(List<EnhancedEvidences> enhancedEvidences, List<EnhancedPNOes> enhancedPNOes, UserContext userContext) {
		for (EnhancedEvidences _EnhancedEvidences : enhancedEvidences) {
			this.appendEnhancedPNOes(_EnhancedEvidences, enhancedPNOes, userContext);
		}

	}

	private void appendEnhancedPNOes(EnhancedEvidences enhancedEvidences, List<EnhancedPNOes> enhancedPNOes, UserContext userContext) {
		if (enhancedEvidences.getEnhancedPNOes() == null) {
			return;
		}
		for (EnhancedPNOes _EnhancedPNOes : enhancedPNOes) {
			if (_EnhancedPNOes.getId().equals(enhancedEvidences.getEnhancedPNOes().getId())) {
				enhancedEvidences.setEnhancedPNOes(_EnhancedPNOes);
				break;
			}
		}

	}

	@Override
	public List<EnhancedEvidences> convent2Enhanceds(List<? extends BasePo> pos) {
		@SuppressWarnings("unchecked")
		List<Evidences> evidences = (List<Evidences>) pos;
		List<EnhancedEvidences> enhancedEvidences = new ArrayList<EnhancedEvidences>();
		for (Evidences _Evidences : evidences) {
			enhancedEvidences.add(new EnhancedEvidences(_Evidences));
		}
		return enhancedEvidences;
	}

	@Override
	public void updateById(String id, DeletedEnum4Evidences deleted, UserContext userContext) throws UserContextException {
		Evidences evidences = evidencesDao.getById(id);
		if (evidences == null
				|| (evidences.getDeleted() != null && (evidences.getDeleted() == DeletedEnum4Evidences.INTO_THE_RECYCLE_BIN.getCode() || evidences.getDeleted() == DeletedEnum4Evidences.REMOVE_COMPLETELY
						.getCode()))) {// 证据不存在或已被删除
			throw new UserContextException(ResponseEnum.EVIDENCE_DOES_NOT_EXIST);
		}
		// TODO 暂时如果是公司用户必须管理员才能删除 如果是个人个人可以直接删除自己的
		if ((Detect.notEmpty(userContext.getUser().getCompanyId()) && userContext.getUser().getType() == UserTypeEnum.MANAGER.getCode()) || !Detect.notEmpty(userContext.getUser().getCompanyId())) {
			if (this.belongTo(evidences, userContext.getContainUserIds())) {
				evidences.setDeleted(DeletedEnum4Evidences.INTO_THE_RECYCLE_BIN.getCode());
				evidencesDao.updateByPrimaryKey(evidences);
			} else {
				throw new UserContextException(ResponseEnum.UNAUTHORIZED_OPERATION);
			}
		} else {
			throw new UserContextException(ResponseEnum.UNAUTHORIZED_OPERATION);
		}
	}

	private boolean belongTo(Evidences evidences, List<String> userIds) {
		for (String _UserId : userIds) {
			if (_UserId.equals(evidences.getUserId())) {
				return true;
			}
		}
		return false;
	}

	@Override
	public Pagination<EnhancedEvidences> getPagination(EvidencesQuery evidencesQuery, String searchKey, Pagination<EnhancedEvidences> pagination, UserContext userContext) {

		List<String> searchUserIds = new ArrayList<String>(userContext.getContainUserIds());

		if (Detect.notEmpty(searchKey) && userContext.getUser().getType() == UserTypeEnum.MANAGER.getCode()) {// 关键字不为空搜索账户
																												// 且为管理员
			// TODO 暂时按照普通用户与管理员分
			List<String> userIds = this.getListByUserAccount4Company(searchKey, userContext.getUser().getCompanyId(), userContext);

			searchUserIds = userIds;
		}

		evidencesQuery.setUserIds(searchUserIds);
		evidencesQuery.setLike_filename(searchKey);
		if (!Detect.notEmpty(pagination.getOrders())) {
			pagination.addOrder(new Order(Evidences.COLUMN_CREATETIME, Order.ORDER_DESC));
		}
		return this.getPagination(evidencesQuery, pagination, userContext);
	}

	private List<String> getListByUserAccount4Company(String searchKey, String companyId, UserContext userContext) {
		List<String> userIds = new ArrayList<String>();
		if (userContext.getUserTable() == UserTableEnum.E_189_USER) {
			// TODO 公正录音189待办
		} else if (userContext.getUserTable() == UserTableEnum.USERS) {
			// 实时保
			Example example = new Example(Users.class);
			example.createCriteria().andLike(Users.FIELD_ACCOUNT, "%" + searchKey + "%").andEqualTo(Users.FIELD_COMPANYID, companyId);
			List<Users> users = usersDao.getListByExample(example);
			if (Detect.notEmpty(users)) {
				for (Users _Users : users) {
					userIds.add(_Users.getId());
				}
			}
		}
		return userIds;
	}

	@Override
	public List<EnhancedEvidences> appendEnhancedREvidencesFiles(List<EnhancedEvidences> enhancedEvidences, UserContext userContext) {
		List<String> evidenceIds = this.getEnhancedIds(enhancedEvidences);
		List<EnhancedREvidencesFile> enhancedREvidencesFiles = (List<EnhancedREvidencesFile>) rEvidencesFileService.getEnhanceds(evidenceIds, userContext);
		this.appendEnhancedREvidencesFiles(enhancedEvidences, enhancedREvidencesFiles);
		return enhancedEvidences;
	}

	private void appendEnhancedREvidencesFiles(List<EnhancedEvidences> enhancedEvidences, List<EnhancedREvidencesFile> enhancedREvidencesFiles) {
		List<String> pnoeIds = this.getPnoeIds(enhancedEvidences);
		List<EnhancedPNOes> enhancedPnoes = (List<EnhancedPNOes>) pnoesService.getEnhanceds(pnoeIds, null);

		if (!Detect.notEmpty(enhancedREvidencesFiles)) {
			for (EnhancedEvidences _EnhancedEvidences : enhancedEvidences) {
				EnhancedREvidencesFile curEnhancedREvidencesFile = new EnhancedREvidencesFile();
				curEnhancedREvidencesFile.setEnhancedPnoes(this.getEnhancedPnoe(_EnhancedEvidences.getEnhancedPNOes().getId(), enhancedPnoes));
				// curEnhancedREvidencesFile.set
				// TODO
			}

		}
		for (EnhancedEvidences _EnhancedEvidences : enhancedEvidences) {
			List<EnhancedREvidencesFile> curEnhancedREvidencesFiles = new ArrayList<EnhancedREvidencesFile>();

			for (EnhancedREvidencesFile _EnhancedREvidencesFile : enhancedREvidencesFiles) {

			}
		}
	}

	private List<String> getPnoeIds(List<EnhancedEvidences> enhancedEvidences) {
		List<String> pnoeIds = new ArrayList<String>();
		for (EnhancedEvidences _EnhancedEvidences : enhancedEvidences) {
			pnoeIds.add(_EnhancedEvidences.getEnhancedPNOes().getId());
		}
		return pnoeIds;
	}

	private EnhancedPNOes getEnhancedPnoe(String id, List<EnhancedPNOes> enhancedPnoes) {
		for (EnhancedPNOes _EnhancedPNOes : enhancedPnoes) {
			if (_EnhancedPNOes.getId().equals(id)) {
				return _EnhancedPNOes;
			}
		}
		return null;
	}

	@Override
	public List<EnhancedEvidences> appendEnhancedItem(List<EnhancedEvidences> enhancedEvidences, UserContext userContext) {
		Map<CategoryEnum4Evidences, List<EnhancedEvidences>> categoryEnhancedEvidencesMap = new HashMap<CategoryEnum4Evidences, List<EnhancedEvidences>>();
		for (EnhancedEvidences _EnhancedEvidences : enhancedEvidences) {
			List<EnhancedEvidences> _CategoryEnhancedEvidences = categoryEnhancedEvidencesMap.get(_EnhancedEvidences.getCategory());
			if (_CategoryEnhancedEvidences == null) {
				categoryEnhancedEvidencesMap.put(_EnhancedEvidences.getCategory(), new ArrayList<EnhancedEvidences>());
			}
			categoryEnhancedEvidencesMap.get(_EnhancedEvidences.getCategory()).add(_EnhancedEvidences);
		}
		Iterator<Entry<CategoryEnum4Evidences, List<EnhancedEvidences>>> categoryEnhancedEvidencesMapIterator = categoryEnhancedEvidencesMap.entrySet().iterator();
		while (categoryEnhancedEvidencesMapIterator.hasNext()) {
			Entry<CategoryEnum4Evidences, List<EnhancedEvidences>> entry = categoryEnhancedEvidencesMapIterator.next();
			List<String> _EvidenceIds = this.getEnhancedIds(entry.getValue());
			List<BaseEnhanced> enhancedItems = null;
			if (entry.getKey() == CategoryEnum4Evidences.FAX) {
				enhancedItems = (List<BaseEnhanced>) evidenceFaxVoicesService.getEnhanceds(_EvidenceIds, userContext);
			} else if (entry.getKey() == CategoryEnum4Evidences.VIDEO) {
				enhancedItems = (List<BaseEnhanced>) evidenceVideosService.getEnhanceds(_EvidenceIds, userContext);
			} else if (entry.getKey() == CategoryEnum4Evidences.APPVIDEO) {
				enhancedItems = (List<BaseEnhanced>) evidenceAppVideoService.getEnhanceds(_EvidenceIds, userContext);
			} else if (entry.getKey() == CategoryEnum4Evidences.APPVOICE) {
				enhancedItems = (List<BaseEnhanced>) evidenceAppVoiceService.getEnhanceds(_EvidenceIds, userContext);
			} else if (entry.getKey() == CategoryEnum4Evidences.APPPICTURE) {
				enhancedItems = (List<BaseEnhanced>) evidencePictureService.getEnhanceds(_EvidenceIds, userContext);
			} else {
				// throw new RdbaoException("[暂不支持]");
			}
			if (Detect.notEmpty(enhancedItems)) {
				this.appendEnhancedItem(entry.getValue(), enhancedItems);
			}
		}
		return enhancedEvidences;
	}

	private List<String> getEnhancedIds(List<EnhancedEvidences> enhancedEvidences) {
		List<String> ids = new ArrayList<String>();
		for (EnhancedEvidences _EnhancedEvidences : enhancedEvidences) {
			ids.add(_EnhancedEvidences.getId());
		}
		return ids;
	}

	private void appendEnhancedItem(List<EnhancedEvidences> enhancedEvidences, List<BaseEnhanced> enhancedItems) {
		String getEvidenceId = "getEvidenceId";
		for (EnhancedEvidences _EnhancedEvidences : enhancedEvidences) {
			for (BaseEnhanced _EnhancedItem : enhancedItems) {
				if (_EnhancedEvidences.getId().equals(_EnhancedItem.obtainString(getEvidenceId))) {
					_EnhancedEvidences.setEnhancedItem(_EnhancedItem);
					break;
				}
			}
		}
	}
}
