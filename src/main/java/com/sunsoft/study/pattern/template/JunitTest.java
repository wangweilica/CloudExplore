package com.sunsoft.study.pattern.template;

import java.util.List;

import org.junit.Test;

public class JunitTest {
	
	@SuppressWarnings("unchecked")
	@Test
	public void getUser() {
		try {
//			String sql = " SELECT id FROM information_schema.processlist WHERE time>0 AND  user='dbu_haochedai' AND  (host like '10.168.79.203%' OR  host like '10.117.85.8%' OR host like '10.47.132.68%' OR host like '10.168.189.39%' OR host like '10.168.241.55%' OR host like '10.161.141.24%')";
			String sql = " SELECT id FROM information_schema.processlist WHERE time>0";

			JdbcTemplate jt = new JdbcTemplateUserImpl();
			jt.execute("kill 671977633");
			/*List<Integer> userList = (List<Integer>) jt.execute(sql);
			for(Integer proc : userList) {
				System.out.println(proc);
			}*/
		} catch (Exception e) {
			e.printStackTrace();
		}  
	}
}
