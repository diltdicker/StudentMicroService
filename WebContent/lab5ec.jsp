<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Student Micro Services Extra Credit</title>
</head>
<body onload="update_subjects()">
<form>
Subject:
<select name="subjects" id="subject_select">
</select>
<br>
Year:
<input type="number" min=1 max=4 value=1 name="year" id="year_select"/>
<input type="button" onclick="calc_grade()" value="Calculate Grade">
<br>
Grade:
<input type="number" step=0.01 name="grade" value=0 max=100 id="grade_select"/>
<input type="button" onclick="map_grade()" value="Map Letter Grade"/>
<br>
Letter Grade:
<input type="text" name="lettergrade" id="lettergrade_select"/>
</form>
<script>
function map_grade(){
	var grade = document.getElementById("grade_select").value;
	var xhttp = new XMLHttpRequest();
	  xhttp.onreadystatechange = function() {
	    if (this.readyState == 4 && this.status == 200) {
	    	var lettergrade = this.responseText;
	    	document.getElementById("lettergrade_select").value = lettergrade;
	    }
	  };
	  xhttp.open("POST", "lab5" + "?query=map&grade=" + grade, true);
	  xhttp.send();
}
function calc_grade(){
	var subject = document.getElementById("subject_select").value;
	var year = document.getElementById("year_select").value;
	var xhttp = new XMLHttpRequest();
	  xhttp.onreadystatechange = function() {
	    if (this.readyState == 4 && this.status == 200) {
	    	var grade = parseFloat(this.responseText);
	    	document.getElementById("grade_select").value = grade;
	    }
	  };
	  xhttp.open("POST", "lab5" + "?query=calc&subject="+subject+"&year="+year, true);
	  xhttp.send();
}
function update_subjects(){
	var xhttp = new XMLHttpRequest();
	  xhttp.onreadystatechange = function() {
	    if (this.readyState == 4 && this.status == 200) {
	    	document.getElementById("subject_select").innerHTML = this.responseText;
	    }
	  };
	  xhttp.open("GET", "lab5?query=subjects", true);
	  xhttp.send();
}
</script>
</body>
</html>