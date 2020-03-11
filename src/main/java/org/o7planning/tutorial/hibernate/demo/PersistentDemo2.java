package org.o7planning.tutorial.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.o7planning.DataUtils;
import org.o7planning.tutorial.hibernate.HibernateUtils;
import org.o7planning.tutorial.hibernate.entities.Department;

public class PersistentDemo2 {
	public static void main(String[] args) {
		SessionFactory factory = HibernateUtils.getSessionFactory();
		Session session = factory.getCurrentSession();
		
		Department department = null;
		
		try {
			session.getTransaction().begin();
			
			System.out.println("Finding department deptNo = D10");
			
			// day la mot doi tuong co trang thai Persistent
			department = DataUtils.findDepartment(session, "D10");
			
			System.out.println("first change location");
			// thay doi gi do tren doi tuong Persistent
			department.setLocation("Chicago" + System.currentTimeMillis());
			
			System.out.println("Location " + department.getLocation());
			
			System.out.println("calling flush");
			// su dung session.flush de chu dong day cac thay doi xuong db
			// co tac dung tren tat ca cac doi tuong Persistent co thay doi
			
			session.flush();
			System.out.println("Flush ok");
			
			System.out.println("Second change");
			// thay doi gi do tren doi tuong Persistent
			// hinh thanh cau lenh update, se duoc thuc thi
			// sau khi session dong lai (commit)
			
			department.setLocation("Location 2 "+ System.currentTimeMillis());
			// in ra location
			System.out.println("Location = " + department.getLocation());
			
			System.out.println("calling commit");
			
			// lenh commit se lam tat ca nhung su thay doi duoc day xuong DB
			session.getTransaction().commit();
			
			System.out.println("commit OK");
			
		} catch(Exception e	) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		
		// tao lai session sau khi no da bi dong truoc do
		// do commit hoac rollback
		
		session = factory.getCurrentSession();
		
		try {
			session.getTransaction().begin();
			System.out.println("finding department deptno = D10");
			
			// query lai department D10
			department = DataUtils.findDepartment(session, "D10");
			
			// in ra thong tin location
			System.out.println("D10 location = " + department.getLocation());
			
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
				
	}
}
