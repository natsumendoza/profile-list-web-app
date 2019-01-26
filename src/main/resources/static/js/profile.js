var profileId = document.getElementById('profileId').value;

// On load
// Self calling method
var loadProfileByIdAPI = function() {

    xhr.open('GET', '/api/profiles/' + profileId);
    xhr.send(null);

    xhr.onreadystatechange = function () {

        var DONE = 4;
        var OK = 200;

        if (xhr.readyState === DONE) {
            if (xhr.status === OK) {
                var response = xhr.response;
                var parsed = JSON.parse(response);
                var profiles = parsed.profiles;
                var profile = profiles[0];
                console.log(profile);

                populateProfileFields(profile);
            } else {
                console.log('Error: ' + xhr.status);
            }
        }

    };

}();

var populateProfileFields = function (profile) {

    var profileName = document.getElementById('profile-name');
    var profileIdentifier = document.getElementById('profile-id');
    var profileDescription = document.getElementById('profile-description');
    var profileEmail = document.getElementById('profile-email');
    var profilePhone = document.getElementById('profile-phone');
    var profileAddress = document.getElementById('profile-address');
    var profileAge = document.getElementById('profile-age');
    var profileBalance = document.getElementById('profile-balance');
    var profileImg = document.getElementById('profile-img');

    profileImg.src = profile.picture;
    profileName.innerText = profile.name.first + " " + profile.name.last;
    profileIdentifier.innerText = profile.id;
    profileDescription.innerText = profile.profile;
    profileEmail.innerText = profile.email;
    profilePhone.innerText = profile.phone;
    profileAddress.innerText = profile.address;
    profileAge.innerText = profile.age;
    profileBalance.innerText = profile.balance;


};