package com.modemo.javase.base;
/**
 * <p>Title:IntegerDemo</p>
 * <p>Description:最新幼升小试题：
已知
（1）法国新总统比第一夫人小24岁；
（2）美国新总统比第一夫人大24岁；
（3）法国新总统比美国新总统小32岁。
请问：美国第一夫人比法国第一夫人小多少岁？</p>
 * @author moshengwei
 * @date 2017年5月10日 下午2:59:45
 */
public class IntegerDemo {
	public static void main(String[] args) {
//		System.out.println(0 == 1);
		int 法国总统 = 0;
		int 美国总统 = 0;
		int 法国第一夫人 = 0;
		int 美国第一夫人 = 0;
		美国总统 = 美国第一夫人 + 24;
		法国总统 = 法国第一夫人 - 24;
		美国总统 = 法国总统 + 32;
		美国第一夫人 = 美国总统 - 24;
		法国第一夫人 = 法国总统 + 24;
		System.out.println("美国第一夫人比法国第一夫人小  "+(美国第一夫人-法国第一夫人)+" 岁！");
	}
}
