package com.hand;

import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;


public class Method {
	
//	public SessionFactory getSessionFactory(){
//		try{
//			@SuppressWarnings("deprecation")
//			SessionFactory factory = new Configuration().configure().buildSessionFactory();
//			return factory;
//	     }catch (Throwable ex) { 
//	        System.err.println("Failed to create sessionFactory object." + ex);
//	        throw new ExceptionInInitializerError(ex); 
//	     }
//	}
	
	/* Method to CREATE an employee in the database */
	   public Integer addCustomer(SessionFactory factory,int store_id,String first_name,String last_name,
			   					String email,int address_id,Date create_date){
	   //(String fname, String lname, int salary){
//		  SessionFactory factory = this.getSessionFactory();
	      Session session = factory.openSession();
	      Transaction tx = null;
	      Integer ID = null;
	      try{
	         tx = session.beginTransaction();
	         Customer customer = new Customer(store_id, first_name, last_name, email, address_id, 1,create_date,create_date);
	         ID = (Integer) session.save(customer); 
	         tx.commit();
	      }catch (HibernateException e) {
	         if (tx!=null) tx.rollback();
	         e.printStackTrace(); 
	      }finally {
	         session.close(); 
	      }
	      return ID;
	   }
	   
	   /* Method to  READ all the employees */
	   public void listEmployees(SessionFactory factory ,Integer ID){//		   SessionFactory factory;
	      Session session = factory.openSession();
	      Transaction tx = null;
	      try{
	    	  tx = session.beginTransaction();
		         Customer customer = 
		                   (Customer)session.get(Customer.class, ID); 
//		         customer.getFirst_name();
		         
		         System.out.println("已经保存的数据如下：");
		     	System.out.println("ID:"+customer.getCustomer_id());
		     	System.out.println("FirstName:"+customer.getFirst_name());
		     	System.out.println("LastName:"+customer.getLast_name());
		     	System.out.println("Email:"+customer.getEmail());
		     	System.out.println("Address:"+customer.getAddress_id());
		         
		         tx.commit();
	      }catch (HibernateException e) {
	         if (tx!=null) tx.rollback();
	         e.printStackTrace(); 
	      }finally {
	         session.close(); 
	      }
	   }
	   
	   
	   /* Method to DELETE an employee from the records */
	   public void deleteCustomer(SessionFactory factory,Integer ID){
//		   SessionFactory factory;
	      Session session = factory.openSession();
	      Transaction tx = null;
	      Scanner in = new Scanner(System.in);
	      try{
	         tx = session.beginTransaction();
	         Customer customer = 
	              (Customer)session.get(Customer.class, ID); 
	         while(customer==null){
	        	 System.out.println("你输入的ID不存在，请重新输入");
	        	 ID = in.nextInt();
	        	 customer = 
	   	              (Customer)session.get(Customer.class, ID);
	         }
	         session.delete(customer); 
	         System.out.println("你输入的ID为" + ID + "的Customer已经 删除.");
	         tx.commit();
	      }catch (HibernateException e) {
	         if (tx!=null) tx.rollback();
	         e.printStackTrace(); 
	      }finally {
	         session.close(); 
	      }
	   }
	
}
