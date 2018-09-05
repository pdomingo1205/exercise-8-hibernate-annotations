package com.pdomingo.dao;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.stat.Statistics;
import org.hibernate.Cache.*;
public class DaoConfig {

  private static final SessionFactory sessionFactory;

 // private static Cache cache;

	static {
		try {
			sessionFactory = new Configuration().configure( "/hibernate.cfg.xml" ).buildSessionFactory();

		} catch ( Throwable ex ) {
			System.err.println ( "Failed to create Session Factory!" + ex );
			throw new ExceptionInInitializerError( ex );
		}
	}

	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}

    private static Session currentSession;

	private static Transaction currentTransaction;



	public static Session openCurrentSession() {
		currentSession = DaoConfig.getSessionFactory().openSession();
		return currentSession;
	}

	public static Session openCurrentSessionwithTransaction() {
		currentSession = DaoConfig.getSessionFactory().openSession();
		currentTransaction = currentSession.beginTransaction();
		return currentSession;
	}

	public static void closeCurrentSession() {
		currentSession.close();
	}

	public static void closeCurrentSessionwithTransaction() {
		currentTransaction.commit();
		currentSession.close();
	}

	public static Session getCurrentSession() {
		return currentSession;
	}


	public static Transaction getCurrentTransaction() {
		return currentTransaction;
	}

    public static void showStatistics(String from, Statistics stats) {
        System.out.println("***********************************");
        System.out.println("Second level cache hit count : "+ stats.getSecondLevelCacheHitCount());
        System.out.println("Second level cache put count : " + stats.getSecondLevelCachePutCount());
        System.out.println("Second level cache miss count : " + stats.getSecondLevelCacheMissCount());
        System.out.println("***********************************");
    }





}
