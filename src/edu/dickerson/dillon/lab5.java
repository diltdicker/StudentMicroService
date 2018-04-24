package edu.dickerson.dillon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

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
	private static String map = null;
	private static String calc = null;
       
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
		// TODO Auto-generated method stub
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
		List<String> calcParams = new ArrayList<String>();
		List<String> calcValues = new ArrayList<String>();
		List<String> gradeParams = new ArrayList<String>();
		List<String> gradeValues = new ArrayList<String>();
		calcParams.add("year");
		calcParams.add("subject");
		calcValues.add("2");
		calcValues.add("History");
		gradeParams.add("grade");
		gradeValues.add("95");
		response.getWriter().append(LabLogic.getRequest("http://localhost:8080/CalcGradeMicroService/lab5calc", calcParams, calcValues)).append(
				LabLogic.getRequest("http://localhost:8080/MapGradeMicroService/lab5map", gradeParams, gradeValues));
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
