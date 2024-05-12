package com.generators;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CitizenIdGenerator implements IdentifierGenerator{

	private Logger logger=LoggerFactory.getLogger(CitizenIdGenerator.class);
	
	@Override
	public Serializable generate(SharedSessionContractImplementor session, Object object) throws HibernateException {
		String prefix="CIT-";
		String suffix="";
		String id="";
		try
		{
			Connection con = session.connection();
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("select cit_id from citizen_seq");
			while(rs.next())
			{
				int val = rs.getInt(1);
				id=prefix+val+suffix;
			}
			int executeUpdate = stmt.executeUpdate("update citizen_seq set cit_id=cit_id+1");
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		
		logger.info("Generated CIT value : "+id);
		return id;
	}
	
}
