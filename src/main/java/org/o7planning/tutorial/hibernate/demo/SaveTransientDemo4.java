package org.o7planning.tutorial.hibernate.demo;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.o7planning.DataUtils;
import org.o7planning.tutorial.hibernate.HibernateUtils;
import org.o7planning.tutorial.hibernate.entities.Employee;
import org.o7planning.tutorial.hibernate.entities.Timekeeper;

public class SaveTransientDemo4 {
	
	private static DateFormat df = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
	private static Timekeeper persist_Transient(Session session, Employee emp) {
		// Hay chu y
		// timeekeeperId cau hinh tu dong duoc tao ra boi UUID
		// @GenericValue(generator="uuid")
		// @GenericGenerator(name="uuid", strategy="uuid2")
		// Tao doi tuong , no dang co tinh trang Transient
		
		Timekeeper tk2 = new Timekeeper();
		
		tk2.setEmployee(emp);
		tk2.setInOut(Timekeeper.IN);
		tk2.setDateTime(new Date());
		
		
		// luc nay 'tk2' dang co tinh trang Transient
		System.out.println("tk2 Persistent? " + session.contains(tk2));
		
		System.out.println("======= call save(tk) ========");
		
		
		// save() rat giong voi persist()
		// save() tra ve ID con persist() la void
		// Hibernate gan Id vao 'tk2' , se chua co insert gi ca
		// No tra ve ID cua 'tk2'
		
		Serializable id = session.save(tk2);
		
		System.out.println("id = " + id);
		
		
		// tk2 da duoc gan ID
		System.out.println("tk2 get TimekeeperId = " + tk2.getTimekeeperId());
		
		// luc nay tk2 da co trang thai Persistent
		// no da duoc quan ly trong Session
		// Nhung chua co hanh dong gi insert xuong DB
		// ===> true	
		System.out.println("tk2 persistent ?" + session.contains(tk2));
		
		System.out.println("Call Flush");
		
		// chu dong day du lieu xuong DB, goi flush()	
		// neu khong goi flush(), du lieu se duoc day xuong tai lenh commit(()
		// luc nay moi co insert
		session.flush();
		
		String timekeeperID = tk2.getTimekeeperId();
		System.out.println("timekeeperId = " + timekeeperID);
		System.out.println("inOUt = " + tk2.getInOut());
		System.out.println("date time = " + df.format(tk2.getDateTime()));
		System.out.println();
		
		return tk2;
	}
	
	public static void main(String[] args) {
		SessionFactory factory = HibernateUtils.getSessionFactory();
		
		Session session = factory.getCurrentSession();
		
		Employee emp = null;
		
		try {
			session.getTransaction().begin();
			
			emp = DataUtils.findEmployee(session, "E3");
			persist_Transient(session, emp);
			
			session.getTransaction().commit();
			
		} catch(Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
	}
}
