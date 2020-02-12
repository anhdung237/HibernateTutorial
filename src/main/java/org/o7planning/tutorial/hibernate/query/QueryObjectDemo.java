package org.o7planning.tutorial.hibernate.query;

import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.o7planning.tutorial.hibernate.entities.Employee;
import org.o7planning.tutorial.hibernate.entities.HibernateUtils;

public class QueryObjectDemo {
	public static void main(String[] args){
		
		SessionFactory factory = HibernateUtils.getSessionFactory();
		
		Session session = factory.getCurrentSession();
		
		try{
			// Tat ca cac lenh hanh dong voi DB thong qua Hibernate
			// deu phai nam trong 1 giao dich Transaction
			// Bat dau giao dich
			session.getTransaction().begin();
			
			//Tao mot cau lenh HQL query object
			// tuong duong voi native Sql
			// select * from employee e order by e.emp_name, e.emp_no
			String sql = "select e from " + Employee.class.getName() + "e " + "order by e.empName, e.empNo";
			
			// Tao doi tuong Query
			Query<Employee> query = session.createQuery(sql);
			
			// Thuc hien truy van
			List<Employee> employees = query.getResultList();
			
			for(Employee emp: employees){
				System.out.print("Emp: " + emp.getEmpNo() + " : " + emp.getEmpName());
			}
			
			// commit du lieu
			session.getTransaction().commit();
			
		} catch( Exception e) {
			e.printStackTrace();
			
			// Rollback trong truong hop co loi xay ra
			session.getTransaction().rollback();
		}
		
	
	}
}
