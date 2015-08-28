package com.sunsoft.study.json;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.alibaba.fastjson.parser.Feature;

public class Test {
	
	public static void main(String[] args) {
		Result<Person> result = new Result<Person>();
		Person person = new Person();
		person.setName("张三");
		person.setAge(20);
		result.setCode(1);
		result.setMsg("成功");
		result.setData(person);
		String text = JSON.toJSONString(result);
		System.out.println(text);
		Result<Person> obj = JSON.parseObject(text,
				new TypeReference<Result<Person>>() {
				}, Feature.AllowComment);
		System.out.println(obj.getData().getAge());
	}
}
