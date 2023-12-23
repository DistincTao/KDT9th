package com.ajaxjsp.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.ajaxjsp.dao.EmployeesDao;
import com.ajaxjsp.dao.EmployeesDaoImpl;
import com.ajaxjsp.etc.OutputJsonForError;
import com.ajaxjsp.vo.JobsVo;

@WebServlet("/getJobsData.do")
public class GetJobDataServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public GetJobDataServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("GetJobSelectionServlet 서블릿 테스트");
		response.setContentType("application/json; charset=utf-8");
		EmployeesDao dao = EmployeesDaoImpl.getInstance();
		JSONObject json = new JSONObject();
		PrintWriter out = response.getWriter();
		
		try {
			List<JobsVo> list = dao.selectAlljobs();
			
			json.put("status", "success");
			SimpleDateFormat fmt = new SimpleDateFormat("yyyy년 MM월 dd일 HH시 mm분 ss초");
			String outputDate = fmt.format(Calendar.getInstance().getTime());
			json.put("outputDate", outputDate);
			json.put("count", list.size());
			
			if (list.size() > 0) {
				JSONArray jobArr = new JSONArray();
				for (JobsVo vo : list) {
					JSONObject job = new JSONObject();
					job.put("job_id", vo.getJob_id());
					job.put("job_title", vo.getJob_title());
					job.put("min_salary", vo.getMin_salary() + "");
					job.put("max_salary", vo.getMax_salary() + "");
					
					
					jobArr.add(job);				
				}
				
				json.put("jobs", jobArr);
			}
		

			out.print(json.toJSONString());
			out.close();
			
		} catch (SQLException | NamingException e) {
			e.printStackTrace();
			out.print(OutputJsonForError.outputJson(e));
		}
		
	}
	
}
