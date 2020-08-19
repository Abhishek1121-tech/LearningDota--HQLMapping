package com.inn.expo.client;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;

import com.inn.expo.entities.Address;
import com.inn.expo.entities.Employee;
import com.inn.expo.util.HibernateUtil;

public class HQLClientTest {

	public static void main(String[] args) {
		getEmployeeAndAdressByEmployeeId();
		//getEmployeeAndAdressByAddressId();
	    
	  }

	private static void getEmployeeAndAdressByAddressId() {

	    try(Session session = HibernateUtil.getSessionFactory().openSession()) {
	    	
	    	String HQL=" FROM Address WHERE addressId=:addrId";
	    	Address address = session.createQuery(HQL, Address.class).setParameter("addrId", 1).getSingleResult();
	    	System.out.println(address);
	       // System.out.println(address.getEmployee());
		} catch (HibernateException e) {
			e.printStackTrace();
		}
	
	}

	private static void getEmployeeAndAdressByEmployeeId() {
	    try(Session session = HibernateUtil.getSessionFactory().openSession()) {
	    	String HQL="FROM Address addr LEFT OUTER JOIN FETCH addr.employee WHERE addr.addressId=:addrId";
	    	Address address = session.createQuery(HQL, Address.class).setParameter("addrId", 1).uniqueResult();
	    	System.out.println(address);
	    	System.out.println(address.getEmployee());
		} catch (HibernateException e) {
			e.printStackTrace();
		}
	}
}
