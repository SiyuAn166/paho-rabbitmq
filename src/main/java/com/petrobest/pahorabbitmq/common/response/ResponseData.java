package com.petrobest.pahorabbitmq.common.response;

import java.util.HashMap;
import java.util.Map;

public class ResponseData extends HashMap<String, Object> {
	private static final long serialVersionUID = 1L;

	public ResponseData() {
		put("code", 200);
		put("msg", "操作成功");
	}

	public static ResponseData error() {
		return error(1, "操作失败");
	}

	public static ResponseData error(String msg) {
		return error(500, msg);
	}

	public static ResponseData error(int code, String msg) {
		ResponseData r = new ResponseData();
		r.put("code", code);
		r.put("msg", msg);
		return r;
	}

	public static ResponseData ok(String msg) {
		ResponseData r = new ResponseData();
		r.put("msg", msg);
		return r;
	}

	public static ResponseData ok(Map<String, Object> map) {
		ResponseData r = new ResponseData();
		r.putAll(map);
		return r;
	}

	public static ResponseData ok() {
		return new ResponseData();
	}

	@Override
	public ResponseData put(String key, Object value) {
		super.put(key, value);
		return this;
	}
}
