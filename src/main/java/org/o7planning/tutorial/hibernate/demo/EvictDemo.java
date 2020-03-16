package org.o7planning.tutorial.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.o7planning.DataUtils;
import org.o7planning.tutorial.hibernate.HibernateUtils;
import org.o7planning.tutorial.hibernate.entities.Employee;

public class EvictDemo {
	
	public static void main (String[] args) {
		SessionFactory factory = HibernateUtils.getSessionFactory();
		
		Session session = factory.getCurrentSession();
		
		Employee emp = null;
		
		try {
			session.getTransaction().begin();
			
			// day la mot doi tuong co tinh trang Persistent
			emp = DataUtils.findEmployee(session, "E3");
			
			// ===> true
			System.out.println("emp persistent ? " + session.contains(emp));
			
			// su dung evict(Object) de duoi doi tuong Persistent
			// ra khoi quan ly cua Hibernate
			session.evict(emp);
			
			// luc nay emp dang co trang thai detached
			// ====> false
			System.out.println("emp persistent ? " + session.contains(emp));
			
			// tat ca ca thay doi tren emp se khong duoc update 
			// neu khong dua emp tro lai trang tha persistent
			emp.setEmpNo("NEW");
			
			session.getTransaction().commit();
			
					
		} catch(Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
	}

}
