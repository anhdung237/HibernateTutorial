package org.o7planning.tutorial.hibernate.entities;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "EMPLOYEE", 
uniqueConstraints = {@UniqueConstraint(columnNames = {"EMP_NO"})})
public class Employee {

	private Long empId;
	private String empNo;
	private String empName;
	private String job;
	private Employee manager;
	private Date hideDate;
	private Float salary;
	private byte[] image;
	
	private Department department;
	private Set<Employee> employees = new HashSet<Employee>(0);
	
	
	public Employee(){
	}

	public Employee(Long empId, String empName, String job, Employee manager, 
		Date hideDate, Float Salary, Float comm, Department department	){
		this.empId = empId;
		this.empNo = "E" + this.empId;
		this.empName = empName;
		this.job = job;
		this.manager = manager;
		this.hideDate = hideDate;
		this.salary = salary;
		this.department = department;
	}
	
	
	@Id
	@Column(name = "EMP_ID")
	public Long getEmpId(){
		return empId;
	}
	
	public void setEmpId(Long empId){
		this.empId = empId;
	}
	
	@Column(name = "EMP_NO" , length = 20, nullable = false)
	public String getEmpNo(){
		return empNo;
	}
	
	public void setEmpNo(String empNo){
		this.empNo = empNo;
	}
	
	@Column (name = "EMP_NAME", length = 50, nullable = false)
	public String getEmpName(){
		return empName;
	}
	
	public void setEmpName(String empName){
		this.empName = empName;
	}
	
}
