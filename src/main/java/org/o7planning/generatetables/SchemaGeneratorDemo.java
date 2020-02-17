package org.o7planning.generatetables;

import java.io.File;
import java.util.EnumSet;

import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.tool.hbm2ddl.SchemaExport;
import org.hibernate.tool.schema.TargetType;

public class SchemaGeneratorDemo {
	
	public static final String SCRIPT_FILE = "exportScript.sql";
	
	private static SchemaExport getSchemaExport(){
		
		SchemaExport export = new SchemaExport();
		
		//Script file
		File outputFile = new File(SCRIPT_FILE);
		String outputFilePath = outputFile.getAbsolutePath();
		
		System.out.print("export file:" + outputFilePath);
		
		export.setDelimiter(";");
		export.setOutputFile(outputFilePath);
		
		// khong ngung neu co loi
		export.setHaltOnError(false);
		
		return export;
	}
	
	public static void dropDataBase(SchemaExport export, Metadata metadata) {
		// TargetType.DATABASE - thuc thi lenh vao database
		// TargetType.SCRIPT - ghi ra file Script
		// TargetType.STDOUT - ghi thong tin log ra man hinh console
		
		EnumSet<TargetType> targetTypes = EnumSet.of(TargetType.DATABASE, TargetType.SCRIPT, TargetType.STDOUT);
		
		export.drop(targetTypes, metadata);
	}
	
	
	public static void createDataBase(SchemaExport export, Metadata metadata){
		// TargetType.DATABASE - thuc thi lenh vao database
		// TargetType.SCRIPT - ghi ra file Script
		// TargetType.STDOUT - ghi thong tin log ra man hinh console
		EnumSet<TargetType> targetTypes = EnumSet.of(TargetType.DATABASE, TargetType.SCRIPT, TargetType.STDOUT);
		
		SchemaExport.Action action = SchemaExport.Action.CREATE;
		
		export.execute(targetTypes, action, metadata);
		
		System.out.println("Export OK");
	}
	
	public static void main (String[] args){
		// Su dung SQLServer
		String configFileName ="hibernate.cfg.xml" ;
			
		// Tao doi tuong ServiceRegistry tu hibernate.cfg.xml
		ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().configure(configFileName).build();
		
		// tao nguon sieu di lieu (metadata) tu Service Registry
		Metadata metadata = new MetadataSources(serviceRegistry).getMetadataBuilder().build();
		
		SchemaExport export = getSchemaExport();
		
		System.out.println("Drop database ...");
		// Drop database 
		dropDataBase(export, metadata);
		
		System.out.println("Create Database ...");
		// tao lai he thong bang
		createDataBase(export, metadata);
		
	}
	
	
}
