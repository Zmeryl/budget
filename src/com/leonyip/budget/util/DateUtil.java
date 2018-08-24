package com.leonyip.budget.util;

import java.util.Calendar;
import java.util.Date;

public class DateUtil {
/*//	public static int getDayAmount(Date startDate, Date endDate) {
//		long d1, d2, d3;
//		d1 = startDate.getTime();
//		d2 = endDate.getTime();
//		d3 = d2 - d1;
//		d3 /= 1000 * 60 * 60 * 24;
//		Long d4 = new Long(d3);
//		return d4.intValue();
//	}
*/	
	/**
	 * 计算两个日期中间隔的天数 (按零点零分零秒计算)
	 * @param startDate 开始日期
	 * @param endDate	结束日期
	 * @param is_work_overtime	是否计算休息日 (周六日)
	 * @return day 间隔天数
	 */
	public static int getDayAmount(Date startDate, Date endDate , boolean is_work_overtime ){
		int max_int = Integer.MAX_VALUE;
		int loop = 0;
		Calendar ca1 = Calendar.getInstance();
		ca1.setTime(startDate);
		ca1.set(Calendar.HOUR_OF_DAY, 0);
		ca1.set(Calendar.MINUTE, 0);
		ca1.set(Calendar.SECOND, 0);
		
		Calendar ca2 = Calendar.getInstance();
		ca2.setTime(endDate);
		ca2.set(Calendar.HOUR_OF_DAY, 0);
		ca2.set(Calendar.MINUTE, 0);
		ca2.set(Calendar.SECOND, 0);
		
		if(ca1.compareTo(ca2) == 0){
			return 1;
		}
		
		for(int i = 0 ; i < max_int; i++){
			if(ca1.compareTo(ca2) == 0){
				//loop = 1;
				break;
			}
			if( !is_work_overtime ){
				if(ca1.get(Calendar.DAY_OF_WEEK)==Calendar.SATURDAY || ca1.get(Calendar.DAY_OF_WEEK)==Calendar.SUNDAY){
					ca1.set(Calendar.DATE, ca1.get(Calendar.DATE)+1);
					continue;
				}
			}
			ca1.set(Calendar.DATE, ca1.get(Calendar.DATE)+1);
			loop++;
		}
		System.out.println(loop);
		return loop;
	}
	
	/**
	 * 比较后一个日期是否早于第一个日期
	 * @param firstDate
	 * @param secondDate
	 * @return
	 */
	public static boolean isBeforeTheDay(Date firstDate, Date secondDate){
		Calendar ca1 = Calendar.getInstance();
		ca1.setTime(firstDate);
		ca1.set(Calendar.HOUR_OF_DAY, 0);
		ca1.set(Calendar.MINUTE, 0);
		ca1.set(Calendar.SECOND, 0);
		
		Calendar ca2 = Calendar.getInstance();
		ca2.setTime(secondDate);
		ca2.set(Calendar.HOUR_OF_DAY, 0);
		ca2.set(Calendar.MINUTE, 0);
		ca2.set(Calendar.SECOND, 0);
		
		int result = ca2.compareTo(ca1);
		if(result < 0){
			return true;
		}
		return false;
	}
	
	/**
	 * 比较后一个日期是否晚于第一个日期
	 * @param firstDate
	 * @param secondDate
	 * @return
	 */
	public static boolean isAfterTheDay(Date firstDate, Date secondDate){
		Calendar ca1 = Calendar.getInstance();
		ca1.setTime(firstDate);
		ca1.set(Calendar.HOUR_OF_DAY, 0);
		ca1.set(Calendar.MINUTE, 0);
		ca1.set(Calendar.SECOND, 0);
		
		Calendar ca2 = Calendar.getInstance();
		ca2.setTime(secondDate);
		ca2.set(Calendar.HOUR_OF_DAY, 0);
		ca2.set(Calendar.MINUTE, 0);
		ca2.set(Calendar.SECOND, 0);
		
		int result = ca1.compareTo(ca2);
		if(result < 0){
			return true;
		}
		return false;
	}
}
