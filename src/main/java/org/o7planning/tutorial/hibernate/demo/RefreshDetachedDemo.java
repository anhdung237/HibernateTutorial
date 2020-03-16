package org.o7planning.tutorial.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.o7planning.DataUtils;
import org.o7planning.tutorial.hibernate.HibernateUtils;
import org.o7planning.tutorial.hibernate.entities.Employee;

public class RefreshDetachedDemo {
	
	public static void main(String[] args) {
		Employee emp = getEmployee_Detached();
		
		System.out.println("get emp " + emp.getEmpId());
		
		refresh_test(emp);
		
	}
	
	// ham tra ve mot doi tuong employee da
		// nam ngoai su quan ly cua Hibernate(Detached)
		private static Employee getEmployee_Detached() {
			SessionFactory factory = HibernateUtils.getSessionFactory();
			Session session1 = factory.getCurrentSession();
			Employee emp = null;
			
			try {
				session1.getTransaction().begin();
				
				Long maxEmpId = DataUtils.getMaxEmpId(session1);
				System.out.println("Max Emp Id =" + maxEmpId);
				
				Employee emp2 = DataUtils.findEmployee(session1, "E5");
				
				Long empId = maxEmpId + 1;
				emp = new Employee();
				emp.setEmpId(empId);
				emp.setEmpNo("E" + empId);
				
				emp.setDepartment(emp2.getDepartment());
				emp.setEmpName(emp2.getEmpName());
				
				emp.setHideDate(emp2.getHideDate());
				emp.setJob("Test");
				emp.setSalary(1000F);
				
				// emp da duoc quan ly boi Hibernate
				session1.persist(emp);
				
				// session1 da bi dong lai sau commit duoc goi
				// mot ban ghi employee da duoc insert vao db
				session1.getTransaction().commit();
				
			} catch(Exception e) {
				e.printStackTrace();
				session1.getTransaction().rollback();
			}
			
			// session1 da bi dong , emp da tro ve trang thai detached
			return emp;
		}
		
	private static void refresh_test(Employee emp) {
		SessionFactory factory = HibernateUtils.getSessionFactory();
		
		// mo mot session khac
		Session session2 = factory.getCurrentSession();
		
		try {
			session2.getTransaction().begin();
			
			// thuc te emp dang co trang thai detached
			// no khong duoc quan ly boi Hibernate
			// kiem tra trang thai cua emp
			// ===> false
			System.out.println("emp persisten " + session2.contains(emp));
			
			System.out.println("emp salary before update "+ emp.getSalary());
			
			// set salary moi cho doi tuong detached emp
			emp.setSalary(emp.getSalary() + 100);
			
			System.out.println("emp after add more salary" + emp.getSalary());
			
			// refresh tao ra cau query
			// va dua doi tuong detached ve persistent
			// cac thay doi cua emp khong duoc luu lai
			session2.refresh(emp);
			
			// ===> true
			System.out.println("emp persistent " + session2.contains(emp));
			
			System.out.println("emp salary after refresh " + emp.getSalary());
			
			session2.getTransaction().commit();
			
		} catch(Exception e) {
			e.printStackTrace();
			session2.getTransaction().rollback();
		}
		
	}

}
