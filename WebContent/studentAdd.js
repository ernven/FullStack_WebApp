function main() {
    var form = document.forms["formStudent"];
    var parameterData = "id=" + form["id"].value + "&fN=" + form["fN"].value +
        "&lN=" + form["lN"].value + "&sA=" + form["sA"].value +
        "&pC=" + form["pC"].value + "&pO=" + form["pO"].value;

    var requestOptions = {
        method: "POST",
        headers: {"Content-Type": "application/x-www-form-urlencoded"},
        body: parameterData
    };

    postDataToServer("/FullStackWebApp/addStudent", requestOptions, processResponseStatus);
}

function processResponseStatus(status) {
    if (status.errorCode === 0) {
        alert("Student data added!");
    } else if (status.errorCode === 1) {
        alert("Cannot add the student. The id is already in use!");
    } else {
        alert("The database is temporarily unavailable. Please try again later.");
    }
    location.replace("students.html");
}