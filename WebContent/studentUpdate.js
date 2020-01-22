var form = document.forms["formStudent"];
form["id"].disabled = true;
var id = parseInt((window.location.href).split('=')[1], 10);
form["id"].value = id;

function main() {
	getDataFromServer("/FullStackWebApp/students", fillData);
}

function fillData(studentList) {
	for (var student of studentList) {
		if (id === student.id) {
			form["fN"].value = student.firstName;
			form["lN"].value = student.lastName;
			form["sA"].value = student.streetAddress;
			form["pC"].value = student.postcode;
			form["pO"].value = student.city;
		}
	}
}

function studentUpdate() {
    var parameterData = "id=" + form["id"].value + "&fN=" + form["fN"].value +
        "&lN=" + form["lN"].value + "&sA=" + form["sA"].value +
        "&pC=" + form["pC"].value + "&pO=" + form["pO"].value;

    var requestOptions = {
        method: "POST",
        headers: {"Content-Type": "application/x-www-form-urlencoded"},
        body: parameterData
    };

    postDataToServer("/FullStackWebApp/updateStudent", requestOptions, processResponseStatus);
}

function processResponseStatus(status) {
    if (status.errorCode === 1) {
        alert("Student data updated!");
    } else {
        alert("The database is temporarily unavailable. Please try again later.");
    }
    location.replace("students.html");
}

main();