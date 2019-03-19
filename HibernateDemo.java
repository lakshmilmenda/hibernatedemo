package com;


import java.util.List;
import java.util.Scanner;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class HibernateDemo {
public static void main(String[] args) {
	Configuration config = new Configuration();
	config.configure("hibernate.cfg.xml");
	
	SessionFactory sessionFactory=config.buildSessionFactory();
	Session session=sessionFactory.openSession();
	Transaction transaction=session.beginTransaction();
	
	com.Product product=new com.Product();
	
	product.setProductId(10);
	product.setProductName("Dell Laptop");
	product.setProductPrice(45000);

	session.save(product);

	

	transaction.commit();
	
	session.close();
	
	System.out.println("Object Saved in Database");
	
}


	public Product retrieveProduct(SessionFactory sessionFactory)
	{
		Scanner scanner=new Scanner(System.in);
		System.out.println("Enter the Product ID:");
		int productId;
		productId=scanner.nextInt();
		
		Session session=sessionFactory.openSession();
		Product product=(Product)session.get(Product.class, productId);
		session.close();
		
		return product;
	}
	
	public void updateProduct(SessionFactory sessionFactory)
	{
		Product product=this.retrieveProduct(sessionFactory);
		String productName;
		int productPrice;
		
		Scanner scanner=new Scanner(System.in);
		System.out.println("Enter the Product Name:");
		productName=scanner.next();
		System.out.println("Enter the Product Price:");
	    productPrice=scanner.nextInt();
		
		product.setProductName(productName);
		product.setProductPrice(productPrice);
		
		Session session=sessionFactory.openSession();
		Transaction transaction=session.beginTransaction();
		session.update(product);
		transaction.commit();
		session.close();
	}
	

public void deleteProduct(SessionFactory sessionFactory)
{
	Product product=this.retrieveProduct(sessionFactory);
		
	Session session=sessionFactory.openSession();
	Transaction transaction=session.beginTransaction();
	session.delete(product);
	transaction.commit();
	session.close();
}
	
public void displayProducts(SessionFactory sessionFactory)
{
	Session session=sessionFactory.openSession();
	Query query=session.createQuery("from Product");
	List<Product> listProducts=query.list();
		
	for(Product product:listProducts)
	{
		System.out.print(product.getProductId()+"  ");
		System.out.print(product.getProductName()+"  ");
		System.out.println(product.getProductPrice());
	}		
}
public void displayPro(SessionFactory sessionFactory)
{
Session session=sessionFactory.openSession();
Query query=session.createQuery("from Product where productName=:prodname");
String str;
Scanner scanner=new Scanner(System.in);
System.out.println("Enter Product Name:");
str=scanner.nextLine();
query.setParameter("prodname",str);
List<Product> listProducts=query.list();

}
}
