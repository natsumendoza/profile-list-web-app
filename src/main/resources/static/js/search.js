var query = document.getElementById('query').value;

// On load
// Self calling method
var loadProfilesAPI = function() {

    xhr.open('GET', '/api/profiles/search/' + query);
    xhr.send(null);

    xhr.onreadystatechange = function () {

        var DONE = 4;
        var OK = 200;

        if (xhr.readyState === DONE) {
            if (xhr.status === OK) {
                var response = xhr.response;
                var parsed = JSON.parse(response);
                var profiles = parsed.profiles;

                console.log(profiles);

                createTable(profiles);
            }
        } else {
            console.log('Error: ' + xhr.status);
        }

    };

}();