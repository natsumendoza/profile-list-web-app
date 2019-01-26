var xhr = new XMLHttpRequest();

var createTable = function(profiles) {


    var profileArray = Array();
    profileArray.push(["Name", "Age", "Active", "Blocked"]);
    var idArray = Array();


    profiles.forEach(function(profile) {

        var name = profile.name.first + " " + profile.name.last;
        var age = profile.age;
        var active = profile.active;
        var blocked = profile.blocked;

        profileArray.push([name, age, active, blocked]);
        idArray.push([profile.id])

    });

    //Create a HTML Table element.
    var table = document.createElement("table");
    table.setAttribute('class', 'responstable');
    table.setAttribute('id', 'responstable');
    table.border = "1";

    //Get the count of columns.
    var columnCount = profileArray[0].length;

    //Add the header row.
    var row = table.insertRow(-1);
    for (var i = 0; i < columnCount; i++) {
        var headerCell = document.createElement("th");
        headerCell.innerHTML = profileArray[0][i]

        if (i !== columnCount - 1) {
            headerCell.setAttribute('onclick', 'sortTable(' + i +')');
        }

        row.appendChild(headerCell);
    }

    //Add the data rows.
    for (var i = 1; i < profileArray.length; i++) {
        row = table.insertRow(-1);
        for (var j = 0; j < columnCount; j++) {
            var cell = row.insertCell(-1);
            cell.setAttribute('align', 'center');

            if(typeof profileArray[i][j] === 'boolean') {
                var checked = profileArray[i][j];
                var checkbox = document.createElement("input");
                checkbox.type = "checkbox";
                checkbox.setAttribute('disabled', 'disabled');
                if (checked) checkbox.setAttribute('checked', 'checked');
                cell.appendChild(checkbox);
            } else {

                if (j === 0) {
                   var link = document.createElement("a");
                   link.setAttribute('href', '/profile/'+idArray[i - 1]);
                   link.innerHTML = profileArray[i][j];
                   cell.appendChild(link);
                } else {
                    cell.innerHTML = profileArray[i][j];
                }

            }

        }
    }

    var dvTable = document.getElementById("wrapper");
    dvTable.innerHTML = "";
    dvTable.appendChild(table);


};

var sortTable = function (n) {

    var table, rows, switching, i, x, y, shouldSwitch, dir, switchcount = 0;
    table = document.getElementById("responstable");
    switching = true;
    //Set the sorting direction to ascending:
    dir = "asc";
    /*Make a loop that will continue until
    no switching has been done:*/
    while (switching) {
        //start by saying: no switching is done:
        switching = false;
        rows = table.rows;
        /*Loop through all table rows (except the
        first, which contains table headers):*/
        for (i = 1; i < (rows.length - 1); i++) {
            //start by saying there should be no switching:
            shouldSwitch = false;
            /*Get the two elements you want to compare,
            one from current row and one from the next:*/
            x = rows[i].getElementsByTagName("TD")[n];
            y = rows[i + 1].getElementsByTagName("TD")[n];
            /*check if the two rows should switch place,
            based on the direction, asc or desc:*/
            if (dir == "asc") {
                if (x.innerHTML.toLowerCase() > y.innerHTML.toLowerCase()) {
                    //if so, mark as a switch and break the loop:
                    shouldSwitch= true;
                    break;
                }
            } else if (dir == "desc") {
                if (x.innerHTML.toLowerCase() < y.innerHTML.toLowerCase()) {
                    //if so, mark as a switch and break the loop:
                    shouldSwitch = true;
                    break;
                }
            }
        }
        if (shouldSwitch) {
            /*If a switch has been marked, make the switch
            and mark that a switch has been done:*/
            rows[i].parentNode.insertBefore(rows[i + 1], rows[i]);
            switching = true;
            //Each time a switch is done, increase this count by 1:
            switchcount ++;
        } else {
            /*If no switching has been done AND the direction is "asc",
            set the direction to "desc" and run the while loop again.*/
            if (switchcount == 0 && dir == "asc") {
                dir = "desc";
                switching = true;
            }
        }
    }

};