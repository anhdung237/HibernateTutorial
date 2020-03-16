package org.o7planning.tutorial.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.o7planning.DataUtils;
import org.o7planning.tutorial.hibernate.HibernateUtils;
import org.o7planning.tutorial.hibernate.entities.Employee;

public class UpdateDetachedDemo {
	
	public static void main(String[] args) {
		SessionFactory factory = HibernateUtils.getSessionFactory();
		
		Session session1 = factory.getCurrentSession();
		
		Employee emp = null;
		
		try {
			session1.getTransaction().begin();
			
			// day la doi tuong co trang thai Persistent
			emp = DataUtils.findEmployee(session1, "E2");
			
			// session1 da bi dong lai sau commit duoc goi
			session1.getTransaction().commit();
			
		} catch(Exception e) {
			e.printStackTrace();
			session1.getTransaction().rollback();
		}
		
		// mo mot session khac
		Session session2 = factory.getCurrentSession();
		
		try {
			session2.getTransaction().begin();
			
			// kiem tra trang thai cua emp
			// ====> false
			System.out.println("emp persistent?" + session2.contains(emp));
			
			System.out.println("emp salary = " + emp.getSalary());
			
			emp.setSalary(emp.getSalary() + 100);
			
			// update() chi ap dung cho doi tuong detached
			// khong dung duoc voi doi tuong transient
			// su dung update(emp) de dua emp tro lai trang thai persistent
			session2.update(emp);
			
			System.out.println("emp persistent after running session2.update ?" + session2.contains(emp));
			
			// chu dong day du lieu xuong db
			// cau lenh flush se duoc goi
			session2.flush();
			
			System.out.println("emp salary after update" + emp.getSalary());
			
			// session 2 da bi dong lai sau commit duoc goi
			session2.getTransaction().commit();
			
			
		} catch(Exception e) {
			e.printStackTrace();
			session2.getTransaction().rollback();
		}
		
	}

}
