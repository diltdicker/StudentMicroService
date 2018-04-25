package edu.dickerson.dillon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class lab5
 */
@WebServlet(description = "index servlet page", urlPatterns = { "/lab5" })
public class lab5 extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static String mapURLString = null;
	private static String calcURLString = null;
	private static String subjectsURLString = null;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public lab5() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		Properties props = new Properties();
		try {
			InputStream propFile = this.getClass().getClassLoader().getResourceAsStream("server.properties");
			props.load(propFile);
			propFile.close();
		}
		catch (IOException ie) {
			ie.printStackTrace();
			System.out.println("server failed to load properties");
			//throw new Exception("Could not open property file");
		}
		subjectsURLString = props.getProperty("subjects.host") + ":" + props.getProperty("subjects.port") + props.getProperty("subjects.context");
		mapURLString = props.getProperty("map.host") + ":" + props.getProperty("map.port") + props.getProperty("map.context");
		calcURLString = props.getProperty("calc.host") + ":" + props.getProperty("calc.port") + props.getProperty("calc.context");
		System.out.println("server properites loaded");
	}

	/**
	 * @see Servlet#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		List<String> calcParams = new ArrayList<String>();
//		List<String> calcValues = new ArrayList<String>();
//		List<String> gradeParams = new ArrayList<String>();
//		List<String> gradeValues = new ArrayList<String>();
//		calcParams.add("year");
//		calcParams.add("subject");
//		calcValues.add("2");
//		calcValues.add("History");
//		gradeParams.add("grade");
//		gradeValues.add("95");
//		//request.getRequestDispatcher("/LabView").forward(request, response);
//		response.getWriter().append(LabLogic.getRequest("http://localhost:8080/CalcGradeMicroService/lab5calc", calcParams, calcValues)).append(
//				LabLogic.getRequest("http://localhost:8080/MapGradeMicroService/lab5map", gradeParams, gradeValues));
		String query = null;
		if (null != request.getParameter("query")) {
			query = (String) request.getParameter("query");
			//System.out.println(subjectsURLString);
			String jsonStr = LabLogic.getRequest(subjectsURLString, 
					new ArrayList<String>(), new ArrayList<String>());
			List<String> subjects = LabLogic.genJSONList(jsonStr);
			//System.out.println(subjects.get(0));
			for (int i = 0; i < subjects.size(); i++) {
				response.getWriter().append("<option>" + subjects.get(i) + "</option>");
			}
		} else {
			request.getRequestDispatcher("lab5.jsp").forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		//response.getWriter().append("do Post");
		String query = null;
		if (null != request.getParameter("query")) {
			query = (String) request.getParameter("query");
			if (query.equals("calc") && request.getParameter("subject") != null && request.getParameter("year") != null) {
				String year = request.getParameter("year");
				String subject = request.getParameter("subject");
				String jsonStr = LabLogic.getRequest(calcURLString, new ArrayList<String>(Arrays.asList("subject", 
						"year")), new ArrayList<String>(Arrays.asList(subject, year)));
				double grade = LabLogic.getnJSONDouble(jsonStr, "grade");
				response.getWriter().append(Double.toString(grade));
			} else if (query.equals("map") && request.getParameter("grade") != null) {
				String grade = request.getParameter("grade");
				String jsonStr = LabLogic.getRequest(mapURLString, new ArrayList<String>(Arrays.asList("grade")), new ArrayList<String>(Arrays.asList(grade)));
				String lettergrade = LabLogic.genJSONString(jsonStr, "lettergrade");
				response.getWriter().append(lettergrade);
			} else {
				request.getRequestDispatcher("/").forward(request, response);
			}
		} else {
			request.getRequestDispatcher("/").forward(request, response);
		}
	}

}
