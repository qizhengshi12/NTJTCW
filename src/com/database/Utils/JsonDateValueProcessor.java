package com.database.Utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import net.sf.json.JsonConfig;
import net.sf.json.processors.JsonValueProcessor;

/**
 *  当使用JSONObject jsonObject = JSONObject.fromObject(bean)转换为json时，
 *  jsp页面接收到的born日期类型为[object object]，无法获取具体日期！
 * 添加自定义的日期格式转化类 
 * @author hr
 */
public class JsonDateValueProcessor implements JsonValueProcessor {

	private String format = "yyyy-MM-dd";

	public Object processArrayValue(Object value, JsonConfig config) {
		return process(value);
	}

	public Object processObjectValue(String key, Object value, JsonConfig config) {
		return process(value);
	}

	private Object process(Object value) {

		if (value instanceof Date) {
			SimpleDateFormat sdf = new SimpleDateFormat(format, Locale.UK);
			return sdf.format(value);
		}
		return value == null ? "" : value.toString();
	}
}
