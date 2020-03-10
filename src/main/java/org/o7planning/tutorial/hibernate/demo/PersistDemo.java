package org.o7planning.tutorial.hibernate.demo;

import java.util.Date;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.o7planning.DataUtils;
import org.o7planning.tutorial.hibernate.HibernateUtils;
import org.o7planning.tutorial.hibernate.entities.Department;
import org.o7planning.tutorial.hibernate.entities.Employee;

public class PersistDemo {
	
	public static void main(String[] args) {
		
		SessionFactory factory = HibernateUtils.getSessionFactory();
		
		Session session = factory.getCurrentSession();
		Department department = null;
		Employee emp = null;
		
		try{
			
			session.getTransaction().begin();
			
			Long maxEmpId = DataUtils.getMaxEmpId(session);
			Long empId = maxEmpId + 1;
			
			// Phong ban voi ma so 10
			// No la doi tuong chiu su quan ly cua session
			// va duoc goi la doi tuong persistent
			department = DataUtils.findDepartment(session, "D10");

			// tao doi tuong Employee
			// doi tuong nay chua chiu su quan ly cua session
			// no duoc coi la doi tuong transient
			
			emp = new Employee();
			emp.setEmpId(empId);
			emp.setEmpNo("E" + empId);
			emp.setEmpName("Name" + empId);
			emp.setJob("coder");
			emp.setSalary(1000f);
			emp.setManager(null);
			emp.setHideDate(new Date());
			emp.setDepartment(department);
			
			// su dung persist (...)
			// luc nay emp da chiu su quan ly cua session
			// no co trang thai persistent
			// chua co hanh dong gi voi DB tai day
			session.persist(emp);
			
			// tai buoc nay du lieu moi duoc day xuong DB
			// Cau lenh insert duoc tao ra
			session.getTransaction().commit();
			
			
		} catch(Exception e){
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		
		// sau khi session bi dong lai (commit, rollback, close)
		// doi tuong emp, dept tro thanh doi tuong detached
		// no khong con trong su quan ly cua session nua
		
		System.out.println("Emp No: " + emp.getEmpNo());
		
	}

}
