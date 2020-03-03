package org.o7planning.tutorial.hibernate.query;

import java.util.List;

import org.hibernate.query.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.o7planning.tutorial.hibernate.HibernateUtils;
import org.o7planning.tutorial.hibernate.entities.Employee;


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
//			String sql = "select e from " + Employee.class.getName() + " e "	
//	                  + " order by e.empName, e.empNo ";
			
			String sql = "select e from " + "Employee" + " e " + "order by e.empName, e.empNo";
			
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


//package org.o7planning.tutorial.hibernate.query;
// 
//import java.util.List;
// 
//import org.hibernate.query.Query;
//import org.hibernate.Session;
//import org.hibernate.SessionFactory;
//import org.o7planning.tutorial.hibernate.HibernateUtils;
//import org.o7planning.tutorial.hibernate.entities.Employee;
// 
//public class QueryObjectDemo {
// 
//  public static void main(String[] args) {
//      SessionFactory factory = HibernateUtils.getSessionFactory();
// 
//      Session session = factory.getCurrentSession();
// 
//      try {
//          // Tất cả các lệnh hành động với DB thông qua Hibernate
//          // đều phải nằm trong 1 giao dịch (Transaction)
//          // Bắt đầu giao dịch
//          session.getTransaction().begin();
// 
//          // Tạo một câu lệnh HQL query object.
//          // Tương đương với Native SQL:
//          // Select e.* from EMPLOYEE e order by e.EMP_NAME, e.EMP_NO
// 
//          String sql = "Select e from " + Employee.class.getName() + " e "
//                  + " order by e.empName, e.empNo ";
// 
//          // Tạo đối tượng Query.
//          Query<Employee> query = session.createQuery(sql);
// 
//          // Thực hiện truy vấn.
//          List<Employee> employees = query.getResultList();
// 
//          for (Employee emp : employees) {
//              System.out.println("Emp: " + emp.getEmpNo() + " : "
//                      + emp.getEmpName());
//          }
// 
//          // Commit dữ liệu
//          session.getTransaction().commit();
//      } catch (Exception e) {
//          e.printStackTrace();
//          // Rollback trong trường hợp có lỗi xẩy ra.
//          session.getTransaction().rollback();
//      }
//  }
//  
//}