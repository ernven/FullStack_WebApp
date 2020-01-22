function getDataFromServer(server, printData) {
    fetch(server)
        .then(response => {
            if (response.ok) {
                return response.json();
            } else {
                throw "HTTP status code is " + response.status;
            }
        })
        .then(studentList => printData(studentList))
        .catch(errorText => alert("getDataFromServer failed: " + errorText));
}

function postDataToServer(server, requestOptions, processResponseStatus) {
    fetch(server, requestOptions)
        .then (response => {
            if (response.ok) {
                return response.json();
            } else {
                throw "HTTP status code is " + response.status;
            }
        })
        .then(status => processResponseStatus(status))
        .catch(errorText => alert("postDataToServer failed: " + errorText));
}