package com.sunsoft.study.pattern.template.callback;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.util.CollectionUtils;

public class Optimizer extends JdbcTemplate  {

	public int[] updateWeibo(final String sql, final List<Integer> list) throws SQLException {
		JdbcTemplate jt = new JdbcTemplate();
		return jt.updateBatch(new StatementCallback(){// 匿名内部类实现
			public int[] doInStatement(PreparedStatement stmt, String sql) throws SQLException {
				if (!CollectionUtils.isEmpty(list)) {
					int i = 0;
					for (int userId : list) {
						Date randomdate = randomdate("2015-01-01", "2015-12-31");
						Long secondTime = randomdate.getTime()/1000;
						stmt.setLong(1, secondTime);
						stmt.setInt(2, userId);
						if (i%500 == 0) {
							stmt.executeBatch();
							stmt.getConnection().commit();
							System.out.println("current location:--------------" + i);
						} 
						stmt.addBatch();
						i ++;
					}
				}
				return stmt.executeBatch();
			}
		}, sql, true);
	}
	
	private static Date randomdate(String begindate, String enddate) {
		try {
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			Date start = format.parse(begindate);// 构造开始日期
			Date end = format.parse(enddate);// 构造结束日期
			// gettime()表示返回自 1970 年 1 月 1 日 00:00:00 gmt 以来此 date 对象表示的毫秒数。
			if (start.getTime() >= end.getTime()) {
				return null;
			}
			long date = random(start.getTime(), end.getTime());
			return new Date(date);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	private static long random(long begin, long end) {
		long rtn = begin + (long) (Math.random() * (end - begin));
		// 如果返回的是开始时间和结束时间,则递归调用本函数查找随机值
		if (rtn == begin || rtn == end) {
			return random(begin, end);
		}
		return rtn;
	}
	
	public static void main(String[] args) {
		try {
			List<Integer> list = new ArrayList<>();
			for (int i = 116295; i <=226295; i++) {list.add(i);}
			String sql = "update t_user_weibo set second_time=? where user_id =?";  
			Optimizer service = new Optimizer();  
			service.updateWeibo(sql,list);
		} catch (Exception e) {
			e.printStackTrace();
		}  
		
	}
}
