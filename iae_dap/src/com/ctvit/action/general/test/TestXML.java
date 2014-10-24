package com.ctvit.action.general.test;

import java.util.HashMap;
import java.util.Map;

import com.ctvit.action.base.ActionSupport;
import com.ctvit.converter.Converter;
import com.ctvit.converter.ConverterContext;
import com.ctvit.converter.ConverterXML;
import com.thoughtworks.xstream.XStream;

public class TestXML extends ActionSupport  {
	private static final long serialVersionUID = 9164110008127050433L;

	@Override
	public String execute() throws Exception {
	/*	Map map=new HashMap();
		map.put("key", "key_TestJson");
		map.put("value", "value_TestJson");
		Converter<Map> converterXmlStrategy = new ConverterXML<Map>();
		ConverterContext<Map> context = new ConverterContext<Map>(converterXmlStrategy);
		String responseData = context.runConverter(map);*/
		//
		Student student=new Student("tom", 22);
		Student1 student1=new Student1("tom1", 33);
		Converter<Student> converterXmlStrategy = new ConverterXML<Student>() {

			@Override
			protected void set(XStream stream, Student instance) {
				
				stream.alias("student1", Student1.class);
				stream.alias("student", Student.class);
			}
		
			
		};
		ConverterContext<Student> context = new ConverterContext<Student>(converterXmlStrategy);
		String responseData = context.runConverter(student);
		build(responseData);
		return null;
	}

}

class Student{
	private String name;
	private Integer age;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	public Student(String name, Integer age) {
		super();
		this.name = name;
		this.age = age;
	}
	public Student() {
		super();
	}
	
	
}

class Student1{
	private String name1;
	private Integer age1;
	public String getName1() {
		return name1;
	}
	public void setName1(String name1) {
		this.name1 = name1;
	}
	public Integer getAge1() {
		return age1;
	}
	public void setAge1(Integer age1) {
		this.age1 = age1;
	}
	public Student1(String name1, Integer age1) {
		super();
		this.name1 = name1;
		this.age1 = age1;
	}
	public Student1() {
		super();
	}
	
	
}