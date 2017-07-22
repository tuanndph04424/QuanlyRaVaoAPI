package com.nguyentuan.api.logicImpl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Repository;
import com.nguyentuan.api.entity.DepartmentEntity;
import com.nguyentuan.api.entity.UserEntity;
import com.nguyentuan.api.logic.UserLogic;
import com.nguyentuan.api.model.UserModel;

import util.HibernateUtil;

@Repository
public class UserLogicImpl implements UserLogic {

	public UserModel checkUser(String username, String pass) {

		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			SQLQuery query = session.createSQLQuery("SELECT * FROM `user` WHERE UserName ='" + username
					+ "' AND Password= '" + pass + "' AND IsLock = 0 ");

			query.addEntity(UserEntity.class);
			UserEntity employees = (UserEntity) query.uniqueResult();

			UserModel billingModel = new UserModel();

			BeanUtils.copyProperties(employees, billingModel);

			System.out.println(billingModel.getID() + billingModel.getFullName() + billingModel.getName()
					+ billingModel.getPassword());
			transaction.commit();

			return billingModel;
		} catch (RuntimeException var4) {
			throw var4;
		} finally {
			session.flush();
			session.close();
		}

	}

	public List<UserModel> checkUser2(String username, String pass) {

		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			SQLQuery query = session.createSQLQuery(
					"SELECT `ID`, `UserName`, `Password`, `Status`, `Actived`, `IsLock`, `Name`, `FullName` FROM `user` WHERE UserName ='"
							+ username + "' AND Password= '" + pass + "' AND IsLock = 0 ");

			List<Object[]> rows = query.list();
			List<UserModel> billingModel = new ArrayList<UserModel>();
			for (Object[] row : rows) {
				UserModel emp = new UserModel();
				emp.setFullName(row[7].toString());
				emp.setID(Integer.parseInt(row[0].toString()));
				emp.setActived(Boolean.parseBoolean(row[4].toString()));
				emp.setName(row[6].toString());

				emp.setUserName(row[1].toString());
				billingModel.add(emp);

			}

			transaction.commit();

			for (UserModel model : billingModel) {
				System.out.println(model.getID() + model.getFullName() + model.getPassword());

			}

			return billingModel;
		} catch (RuntimeException var4) {
			throw var4;
		} finally {
			session.flush();
			session.close();
		}

	}

	public List<UserModel> checkUser3(String username, String pass) {

		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			String sql = "SELECT *FROM user INNER JOIN department ON user.DepartmentID = department.ID where user.UserName='"
					+ username + "' AND Password='" + pass + "' ";
			SQLQuery query = session.createSQLQuery(sql).addEntity(UserEntity.class).addEntity(DepartmentEntity.class);

			List<Object[]> objs = (List<Object[]>) query.list();
			UserEntity userEntity = new UserEntity();
			DepartmentEntity departmentEntity = new DepartmentEntity();
			List<UserEntity> list2 = new ArrayList<UserEntity>();
			List<UserModel> list = new ArrayList<UserModel>();

			UserModel model22 = new UserModel();
			for (Object[] row : objs) {
				userEntity = (UserEntity) row[0];
				departmentEntity = (DepartmentEntity) row[1];
				list2.add(userEntity);
			}

			BeanUtils.copyProperties(list2, list);

			for (UserEntity model : list2) {
				BeanUtils.copyProperties(userEntity, model22);
				list.add(model22);

			}

			transaction.commit();

			return list;
		} catch (RuntimeException var4) {
			throw var4;
		} finally {
			session.flush();
			session.close();
		}

	}

	public static void main(String args[]) {

		UserLogicImpl impl = new UserLogicImpl();
		System.out.println(impl.checkUser3("4424", "abc"));

	}

}
