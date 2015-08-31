package com.hand;

import java.util.Date;
import java.util.Scanner;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class App 
{
    public static void main( String[] args )
    {
    	SessionFactory factory;
    	try{
            factory = new Configuration().configure().buildSessionFactory();
         }catch (Throwable ex) { 
            System.err.println("Failed to create sessionFactory object." + ex);
            throw new ExceptionInInitializerError(ex); 
         }
    	
    	Method method = new Method();
    	
    	int store_id, address_id, delete_id;
    	String first_name, last_name, email;
    	Date create_date = new Date();//java.sql.Date(new java.util.Date().getTime());
    	long aaa = System.currentTimeMillis();
    	
    	store_id = 1;
    	
    	Scanner in = new Scanner(System.in);
    	
    	System.out.println("请输入FirstName(first_name):");
    	first_name = in.nextLine();
    	System.out.println("请输入LastName(last_name):");
    	last_name = in.nextLine();
    	System.out.println("请输入Email(email):");
    	email = in.nextLine();
    	System.out.println("请输入Address ID:");
    	address_id = in.nextInt();
    	
    	Integer ID = method.addCustomer( factory, store_id, first_name, last_name, email, address_id, create_date);
    	
    	
    	method.listEmployees(factory, ID);
    	
    	System.out.println("请输入你要删除的Customer的ID:");
    	delete_id = in.nextInt();
    	
    	method.deleteCustomer(factory,delete_id);
    	
    	
    }
}
