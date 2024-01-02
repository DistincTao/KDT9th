package com.jspminipjt.etc;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.json.simple.JSONObject;

public class OutputJsonForError {
	public static String outputJson(Exception e) {
		JSONObject json = new JSONObject();
		
		json.put("status","fail");
		json.put("errorMsg", e.getMessage());
		SimpleDateFormat fmt = new SimpleDateFormat("yyyy년 MM월 dd일 HH시 mm분 ss초");
		String outputDate = fmt.format(Calendar.getInstance().getTime());
		json.put("outputDate", outputDate);
		// error code 도 넣어 줄 수 있다.
		// 코드화 하여 에러를 분류하여 보여 주도록 한다
		
		return json.toJSONString();
	}
}
