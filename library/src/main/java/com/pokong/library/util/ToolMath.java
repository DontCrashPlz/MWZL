package com.pokong.library.util;

import java.math.BigDecimal;
import java.text.DecimalFormat;

public class ToolMath {

	public static String  DecimalFormat_2="0.00";
	public static String  DecimalFormat_3="0.000";
	public static String  DecimalFormat_4="0.0000";
	
	public static float null2Float(Object s) {
		float v = 0.0F;
		if (s != null)
			try {
				v = Float.parseFloat(s.toString());
			} catch (Exception localException) {
			}
		return v;
	}

	public static double null2Double(Object s) {
		double v = 0.0D;
		if (s != null)
			try {
				v = Double.parseDouble(null2String(s));
			} catch (Exception localException) {
			}
		return v;
	}

	/**
	 * 转换成精度数价格使用
	 * 
	 * @param s
	 * @return
	 */
//	public static BigDecimal null2Decimal(Object s) {
//		BigDecimal v = BigDecimal.valueOf(0.00);
//		if (s != null)
//			try {
//				v = BigDecimal.valueOf(null2Double(s));
//			} catch (Exception localException) {
//			}
//		return v;
//	}
	
	public static BigDecimal null2Decimal(Object s) {
		BigDecimal v = BigDecimal.valueOf(0.00);
		if (s != null)
			try {
				v = new BigDecimal(null2String(s));
			} catch (Exception localException) {
			}
		return v;
	}
	
	/**
	 * 字符串转BigDecimal
	 * @param s
	 * @return
	 */
	public static BigDecimal str2Decimal(String s) {
		BigDecimal v = BigDecimal.valueOf(0.00);
		if (s == null || s.equals(""))
		{
			return v;
		}
			
		try {
			v = new BigDecimal(s);
		} 
		catch (Exception localException) {
		}
		
		return v;
	}	
	
	/**
	 * 四舍五入BigDecimal 数值
	 * @param src
	 * @param nScale
	 * @return
	 */
	public static BigDecimal scaleBigDecimal(BigDecimal src,int nScale)
	{
		if(src==null)
		{
			return null;
		}
		
		//ROUND_HALF_UP 四舍五入
		return src.setScale(nScale,BigDecimal.ROUND_HALF_UP);
		
	}
		
	/**
	 * 四舍五入保留一位小数
	 * @param src
	 * @return
	 */
	public static BigDecimal scale1BigDecimal(BigDecimal src)
	{
		return scaleBigDecimal(src,1);		
	}	
	
	/**
	 * 四舍五入保留二位小数
	 * @param src
	 * @return
	 */
	public static BigDecimal scale2BigDecimal(BigDecimal src)
	{
		return scaleBigDecimal(src,2);		
	}	
	
	/**
	 * 四舍五入保留三位小数
	 * @param src
	 * @return
	 */
	public static BigDecimal scale3BigDecimal(BigDecimal src)
	{
		return scaleBigDecimal(src,3);		
	}	
	/**
	 * 四舍五入保留四位小数
	 * @param src
	 * @return
	 */
	public static BigDecimal scale4BigDecimal(BigDecimal src)
	{
		return scaleBigDecimal(src,4);		
	}	

	/**
	 * 转换成Boolean 格式
	 * @param s
	 * @return
	 */
	public static boolean null2Boolean(Object s) {
		boolean v = false;
		if (s != null)
			try {
				v = Boolean.parseBoolean(s.toString());
			} catch (Exception localException) {
			}
		return v;
	}

	public static String null2String(Object s) {
		return s == null ? "" : s.toString().trim();
	}

	public static Long null2Long(Object s) {
		Long v = Long.valueOf(0L);
		if (s != null)
			try {
				v = Long.valueOf(Long.parseLong(s.toString()));
			} catch (Exception localException) {
			}
		return v;
	}

	public static int null2Int(Object s) {
		Integer v = Integer.valueOf(0);
		if (s != null)
			try {
				v = Integer.valueOf(s.toString());
			} catch (Exception localException) {
			}
		return v;
	}

	public static double div(Object a, Object b) {
		double ret = 0.0D;
		if ((!null2String(a).equals("")) && (!null2String(b).equals(""))) {
			BigDecimal e = new BigDecimal(null2String(a));
			BigDecimal f = new BigDecimal(null2String(b));
			if (null2Double(f) > 0.0D)
				ret = e.divide(f, 3, 1).doubleValue();
		}
		DecimalFormat df = new DecimalFormat("0.00");
		return Double.valueOf(df.format(ret)).doubleValue();
	}

	public static double subtract(Object a, Object b) {
		double ret = 0.0D;
		BigDecimal e = new BigDecimal(null2Double(a));
		BigDecimal f = new BigDecimal(null2Double(b));
		ret = e.subtract(f).doubleValue();
		DecimalFormat df = new DecimalFormat("0.00");
		return Double.valueOf(df.format(ret)).doubleValue();
	}

	public static double add(Object a, Object b) {
		double ret = 0.0D;
		BigDecimal e = new BigDecimal(null2Double(a));
		BigDecimal f = new BigDecimal(null2Double(b));
		ret = e.add(f).doubleValue();
		DecimalFormat df = new DecimalFormat("0.00");
		return Double.valueOf(df.format(ret)).doubleValue();
	}

	/**
	 * 两数相乘
	 * @param a
	 * @param b
	 * @return
	 */
	public static double mul(Object a, Object b) {
		BigDecimal e = new BigDecimal(null2Double(a));
		BigDecimal f = new BigDecimal(null2Double(b));
		double ret = e.multiply(f).doubleValue();
		DecimalFormat df = new DecimalFormat("0.00");
		return Double.valueOf(df.format(ret)).doubleValue();
	}

	/**
	 * 格式化成两位精度小数.
	 * @param money
	 * @return
	 */
	public static double formatMoney(Object money) {
		DecimalFormat df = new DecimalFormat("0.00");
		return Double.valueOf(df.format(money)).doubleValue();
	}
	
	/**
	 * 根据指定精度格式化double
	 * @param objDouble
	 * @param strFormat
	 * @return
	 */
	public static double formatDouble(Object objDouble,String strFormat){
		
		DecimalFormat df = new DecimalFormat(strFormat);
		return Double.valueOf(df.format(objDouble)).doubleValue();		
	}
	
	/**
	 * 格式化2位小数
	 * @param objDouble
	 * @return
	 */
	public static double format2Double(Object objDouble)
	{
		return formatDouble(objDouble,DecimalFormat_2);
	}
	
	/**
	 * 格式化3位小数
	 * @param objDouble
	 * @return
	 */
	public static double format3Double(Object objDouble)
	{
		return formatDouble(objDouble,DecimalFormat_3);
	}	
	
	/**
	 * 格式化4位小数
	 * @param objDouble
	 * @return
	 */
	public static double format4Double(Object objDouble)
	{
		return formatDouble(objDouble,DecimalFormat_4);
	}	
	
}
