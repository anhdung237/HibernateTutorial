package org.o7planning.tutorial.hibernate.demo;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.o7planning.DataUtils;
import org.o7planning.tutorial.hibernate.HibernateUtils;
import org.o7planning.tutorial.hibernate.entities.Employee;
import org.o7planning.tutorial.hibernate.entities.Timekeeper;

public class SaveOrUpdateTransientDemo5 {
	private static DateFormat df = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
	
	private static Timekeeper saveOrUpdate_Transient(Session session, Employee emp) {
		
		// Hay chu y
		// timekeeperId cau hinh tu dong duoc tao ra boi uuid
		// @GeneratedValue(generator="uuid")
		// @GenericGenerator(name="uuid", strategy="uuid2")
		// Tao mot doi tuong, no dang co tinh trang Transient
		
		Timekeeper tk3 = new Timekeeper();
		tk3.setEmployee(emp);
		tk3.setInOut(Timekeeper.IN);
		tk3.setDateTime(new Date());
		
		// Luc nay tk3 dang co tinh trang Transient
		System.out.println("tk3 Persistent?" + session.contains(tk3));
		
		System.out.println("======== Call saveOrUpdate(tk) ===========");
		
		// Tai day Hibernate se kiem tra , tk3 co ID chua (timekeeperId)
		// Neu chua co no tu gan ID vao
		
		session.saveOrUpdate(tk3);
		
		System.out.println("tk3.getTimekeeperId() = " + tk3.getTimekeeperId());
		
		System.out.println("Call flush");
		// chu dong day du lieu xuong DB, goi flush()
		// neu khong goi flush(), du lieu se duoc day xuong tai lenh commit()
		// Luc nay co the co Insert hoac Update xuong DB
		// Tuy thuoc vao id cua tk3 co tren db hay chua
		session.flush();
		
		String timekeeperId = tk3.getTimekeeperId();
		System.out.println("timekeeperId = " + timekeeperId);
		System.out.println("inout =" + tk3.getInOut());
		System.out.println("date time = " + df.format(tk3.getDateTime()));
		System.out.println();
		return tk3;
		
	}
	
	public static void main(String[] args) {
		
		SessionFactory factory = HibernateUtils.getSessionFactory();
		Session session = factory.getCurrentSession();
		
		Employee emp = null;
		
		try {
			session.getTransaction().begin();
			
			emp = DataUtils.findEmployee(session, "E3");
			
			saveOrUpdate_Transient(session, emp);
			
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
	}
}
