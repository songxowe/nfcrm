package com.sd.farmework.common;
/**
 * 枚举类普通状态与描述,主要存储结果集的状态码和描述信息
 * 2016-08-14
 * xiangmeng
 */

public enum ResultModelEnum {

		RESULT_000("000", "成功"),
 		RESULT_001("001", "网络异常，请稍后再试"),
		RESULT_002("002", "参数错误"),
		RESULT_003("003", "数据重复"),
		RESULT_004("004", "结果集为空");
 
		// 成员变量
		private String key;
		private String value;
		
		// 构造方法
		private ResultModelEnum(String key, String value) {
			this.key = key;
			this.value = value;
		}
		
		public String getKey() {
			return key;
		}
		public String getValue() {
			return value;
		}
}
