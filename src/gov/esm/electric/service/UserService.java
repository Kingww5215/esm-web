package gov.esm.electric.service;

import gov.esm.electric.dao.UserDao;
import gov.esm.electric.dao.UserRoleRelationDao;
import gov.esm.electric.domain.User;
import gov.esm.electric.domain.UserRoleRelation;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * 
 * @author XueLiang
 * @date 2014年11月26日
 */
@Service
public class UserService {
	@Resource
	private UserDao userDao;

	@Resource
	private UserRoleRelationDao userRoleRelationDao;

	@Transactional(propagation = Propagation.SUPPORTS)
	public void insert(User entity) {
		userDao.insert(entity);
	}

	@Transactional
	public void insert(User user, UserRoleRelation relation) {
		this.insert(user);
		relation.setUserId(user.getId());
		userRoleRelationDao.insert(relation);
	}

	@Transactional(propagation = Propagation.SUPPORTS)
	public User getUser(String name, String password) {
		return userDao.getUser(name, password);
	}

	@Transactional(propagation = Propagation.SUPPORTS)
	public List<User> getUsers(int index, int size) {
		return userDao.getUsers(index, size);
	}
}
