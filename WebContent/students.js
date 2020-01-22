function main() {
	getDataFromServer("/FullStackWebApp/students", printStudents);
}

function printStudents(studentList) {
	var toInsert = "";
	for (var student of studentList) {
		toInsert = toInsert + "<tr><td>" + student.id + "</td>\n" +
			"<td>" + student.lastName + "</td>\n" +
			"<td>" + student.firstName + "</td>\n" +
			"<td>" + student.streetAddress + "</td>\n" +
			"<td>" + student.postcode + "</td>\n" +
			"<td>" + student.city + "</td>\n" +
			"<td>" + createUpdateDeleteLinks(student.id) + "</td></tr>\n";
	}
	document.getElementById("tableData").innerHTML = toInsert;
}

function createUpdateDeleteLinks(id) {
	return "<span class='link' onclick='updateStudent(" + id
			+ ");'>Update</span>" + "&nbsp;&nbsp;"
			+ "<span class='link' onclick='deleteStudent(" + id
			+ ");'>Delete</span>";
}

function addStudent() {
	location.replace("studentAdd.html");
}

function updateStudent(id) {
	location.replace("studentUpdate.html?id=" + id);
}

function deleteStudent(id) {
	var input = confirm("Delete student " + id + "?");
	if( input == true ) {
	    var parameterData = "studentID=" + id;

	    var requestOptions = {
	        method: "POST",
	        headers: {"Content-Type": "application/x-www-form-urlencoded"},
	        body: parameterData
	    };

	    postDataToServer("/FullStackWebApp/deleteStudent", requestOptions, processResponseStatus);
	}
}

function processResponseStatus(status) {
    if (status.errorCode === 1) {
        alert("Student data deleted!");
    } else if (status.errorCode === 0) {
        alert("Student data not deleted. Unknown student id!");
    } else {
        alert("The database is temporarily unavailable. Please try again later.");
    }
    location.reload();
}

main();