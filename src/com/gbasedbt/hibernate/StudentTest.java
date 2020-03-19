package com.gbasedbt.hibernate;

import java.util.Iterator;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class StudentTest {
	
	private static SessionFactory sf; 

	public static void main(String[] args) {
		
		Configuration cfg = new Configuration();
		sf = cfg.configure().buildSessionFactory();
		
		//C 增加学生
		for (int i = 1; i < 11; i++) {
			addStudent("test" + i, "text info " + i, ("photo byte " + 1 ).getBytes());
		}
		
		//R 查询所有学生
		listStudents();
		
		//R 分页查询学生
		listStudentsByPage(3, 2);
		
		//U 修改学生
		updateStudent(2, "Modify text info");
		listStudents();
		
		//D 删除学生
		deleteStudent(3);
		listStudents();
		
	}
	
	/**
	 * 增加学生，返回序号
	 * @param username
	 * @param usertext
	 * @param userphoto
	 * @return
	 */
	public static int addStudent(String username, String usertext, byte[] userphoto) {
		int studentID = 0;
		Student student = new Student();
		student.setUsername(username);
		student.setUsertext(usertext);
		student.setUserphoto(userphoto);
		
		Session session = sf.openSession();
		try {
			session.beginTransaction();
			studentID = (int) session.save(student);
			session.getTransaction().commit();
		} catch (HibernateException e) {
			if (session.getTransaction() != null) {
				session.getTransaction().rollback();
			}
			e.printStackTrace();
		} finally {
			session.close();
		}
		return studentID;
	}
	
	/**
	 * 显示所有学生
	 */
	@SuppressWarnings("rawtypes")
	public static void listStudents() {
		Session session = sf.openSession();
		try {
			List students = session.createQuery("FROM Student").list();
			for (Iterator iterator = students.iterator(); iterator.hasNext();) {
				Student student = (Student) iterator.next();
				System.out.println(student.toString());
			}
		} catch (HibernateException e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
	}
	
	/**
	 * 分页显示学生：页大小，第几页
	 * @param pageSize
	 * @param numPage
	 */
	@SuppressWarnings("rawtypes")
	public static void listStudentsByPage(int pageSize, int numPage) {
		int firstRow = pageSize * (numPage - 1);
		System.out.println("显示第  " + numPage + "  页(按每  " + pageSize + "  记录分页)");
		Session session = sf.openSession();
		try {		
			List students = session.createQuery("FROM Student").setFirstResult(firstRow).setMaxResults(pageSize).list();
			for (Iterator iterator = students.iterator(); iterator.hasNext();) {
				Student student = (Student) iterator.next();
				System.out.println(student.toString());
			}
		} catch (HibernateException e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
	}
	
	/**
	 * 按序号更新userText信息
	 * @param userId
	 * @param userText
	 */
	public static void updateStudent(int userId, String userText) {
		Session session = sf.openSession();
		try {
			session.beginTransaction();
			Student student = (Student) session.get(Student.class, userId);
			student.setUsertext(userText);
			session.update(student);
			session.getTransaction().commit();
		} catch (HibernateException e) {
			if (session.getTransaction() != null) {
				session.getTransaction().rollback();
			}
			e.printStackTrace();
		} finally {
			session.close();
		}
	}
	
	/**
	 * 按序号删除学生
	 * @param userId
	 */
	public static void deleteStudent(int userId) {
		Session session = sf.openSession();
		try {
			session.beginTransaction();
			Student student = (Student) session.get(Student.class, userId);
			session.delete(student);
			session.getTransaction().commit();
		} catch (HibernateException e) {
			if (session.getTransaction() != null) {
				session.getTransaction().rollback();
			}
			e.printStackTrace();
		} finally {
			session.close();
		}
	}
	
}
