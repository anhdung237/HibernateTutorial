package org.o7planning.tutorial.hibernate.demo;

import java.util.Random;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.o7planning.DataUtils;
import org.o7planning.tutorial.hibernate.HibernateUtils;
import org.o7planning.tutorial.hibernate.entities.Employee;

public class MergeDetachedDemp {
	
	public static void main(String[] args) {
		// mot doi tuong co trang thai detached
		Employee emp = getEmployee_Detached();
		
		System.out.println("get emp " + emp.getEmpId());
		
		// ngau nhien xoa hoac khong xoa employee ung voi id
		boolean delete = deleteOrNotDelete(emp.getEmpId());
		
		// saveOrUpdate doi tuong detached
		saveOrUpdate_test(emp);
		
		// sau khi goi saveOrUpdate()
		// co the ID cua Entity se khac di trong truong hop
		// entity co ID tu tang va saveOrUpdate tao ra cau insert
		System.out.println("emp id " + emp.getEmpId());
				
		
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
	
	// Xoa employee theo ID cho boi tham so
		// ngau nhien xoa hoac khong xoa
		private static boolean deleteOrNotDelete(Long empId) {
			// mot so ngau nhien tu 0-9	
			int random = new Random().nextInt(10);
			
			System.out.println("Random number = " + random);
			if (random < 5) {
				return false;
			}
			
			SessionFactory factory = HibernateUtils.getSessionFactory();
			Session session2 = factory.getCurrentSession();
			
			try {
				session2.getTransaction().begin();
				String sql = "delete " + Employee.class.getName() + " e " +
							" where e.empId = :empId";
				Query query = session2.createQuery(sql);
				query.setParameter("empId", empId);
				
				query.executeUpdate();
				
				session2.getTransaction().commit();
				return true;
				
			} catch (Exception e) {
				e.printStackTrace();
				session2.getTransaction().rollback();
				return false;
			}
		}
		
		private static void saveOrUpdate_test(Employee emp) {
			SessionFactory factory = HibernateUtils.getSessionFactory();
			
			// mo mot session khac
			Session session3 = factory.getCurrentSession();
			
			try {
				session3.getTransaction().begin();
				
				// thuc te emp dang co trang thai detached
				// no khong duoc quan ly boi Hibernate
				// kiem tra trang thai cua emp
				// ===> false
				System.out.println("emp Persistent ? " + session3.contains(emp));
				
				System.out.println("emp salary before update : " + emp.getSalary());
				
				// set salary moi cho doi tuong detached emp
				// ban cung co the set id moi neu muon
				emp.setSalary(emp.getSalary() + 100);
				
				// merge(emp) tra ve empmerge, mot ban copy cua emp
				// empmerge duoc quan ly boi Hibernate
				// con emmp van traong tinh trang detached
				//
				// luc nay van chua co xu ly gi lien quan db
				Employee empMerge = (Employee) session3.merge(emp);
				
				// ==> false
				System.out.println("emp persistent? " + session3.contains(emp)); 
				
				// ===> true
				System.out.println("empmerge persistent? " + session3.contains(empMerge));
				
				// chu dong day du lieu xuong db
				// tai day co the tao ra cau insert hoac update vao db
				// neu ban ghi tuong ung da bi xoa boi ai do, tao ra insert
				// nguoc lai, tao ra update
				session3.flush();
				
				System.out.println("emp salary after update: " + emp.getSalary());
				
				// session3 da bi dong lai sau commit duoc goi
				session3.getTransaction().commit();
				
			} catch(Exception e) {
				e.printStackTrace();
				session3.getTransaction().rollback();
			}
		}
		
		
}
