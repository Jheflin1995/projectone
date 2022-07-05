console.log('Review Submit Scripts')

let table = document.querySelector('table');


let seeAllResolvedButton = document.getElementById('vrr');
let seeAllPendingButton = document.getElementById('vpr');
let viewEmployeeButton = document.getElementById('all-emps');

seeAllResolvedButton.addEventListener('click', fetchResolvedRequests);
seeAllPendingButton.addEventListener('click', fetchPendingRequests);
viewEmployeeButton.addEventListener('click', fetchEmps);


function buildResolvedRequestsTable(data) {

    console.log('buildTable method triggered');

    console.log(data);

    let header = document.createElement('thead'); // these are HTML elements
    let headerRow = document.createElement('tr');

    header.appendChild(headerRow);
    table.appendChild(header); 

    // create a header column for FirstName
    let th1 = document.createElement('th');
    th1.innerHTML = 'REQUEST ID:';

    // create a header column for last Name
    let th2 = document.createElement('th');
    th2.innerHTML = 'AMOUNT:';

    // create a header column for username
    let th3 = document.createElement('th');
    th3.innerHTML = 'DESCRIPTION:';

    let th4 = document.createElement('th');
    th4.innerHTML = 'STATUS:';

    let th5 = document.createElement('th');
    th5.innerHTML = 'TYPE:';

    let th6 = document.createElement('th');
    th6.innerHTML = 'AUTHOR:'

    let th7 = document.createElement('th');
    th7.innerHTML = 'TIME SUBMITTED:'

    let th8 = document.createElement('th');
    th8.innerHTML = 'TIME RESOLVED:'

    let th9 = document.createElement('th');
    th9.innerHTML = 'RESOLVED BY:'



    // apend the child nodes onto the header
    headerRow.appendChild(th1);
    headerRow.appendChild(th2);
    headerRow.appendChild(th3);
    headerRow.appendChild(th4);
    headerRow.appendChild(th5);
    headerRow.appendChild(th6);
    headerRow.appendChild(th7);
    headerRow.appendChild(th8);
    headerRow.appendChild(th9);
    


    data.forEach(e => {

        console.log(e);

        let row = document.createElement('tr');
        let td1 = document.createElement('td');
        let td2 = document.createElement('td');
        let td3 = document.createElement('td');
        let td4 = document.createElement('td');
        let td5 = document.createElement('td');
        let td6 = document.createElement('td');
        let td7 = document.createElement('td');
        let td8 = document.createElement('td');
        let td9 = document.createElement('td');
        

        // set the inner HTML of each cell to the diff propertie s (firstname, lastname, usewrnam )
        td1.innerHTML = e.id;
        td2.innerHTML = e.amount;
        td3.innerHTML = e.description;
        td4.innerHTML = e.status.status;
        td5.innerHTML = e.type.type;
        td6.innerHTML = e.reimb_AUTHOR.id;
        td7.innerHTML = e.reimb_SUBMITTED;
        td8.innerHTML = e.reim_RESOLVED;
        td9.innerHTML = e.reimb_RESOLVER.id;
       

        // finally append each table cell to the row
        row.appendChild(td1);
        row.appendChild(td2);
        row.appendChild(td3);
        row.appendChild(td4);
        row.appendChild(td5);
        row.appendChild(td6);
        row.appendChild(td7);
        row.appendChild(td8);
        row.appendChild(td9);
        

        // append the row to table
        table.appendChild(row);

        console.log(td4);

    });

}

function buildPendingTable(data) {

    console.log('buildPendingTable method triggered');

    console.log(data);

    let header = document.createElement('thead'); // these are HTML elements
    let headerRow = document.createElement('tr');

    header.appendChild(headerRow);
    table.appendChild(header); 

    // create a header column for FirstName
    let th1 = document.createElement('th');
    th1.innerHTML = 'REQUEST ID:';

    // create a header column for last Name
    let th2 = document.createElement('th');
    th2.innerHTML = 'AMOUNT:';

    // create a header column for username
    let th3 = document.createElement('th');
    th3.innerHTML = 'DESCRIPTION:';

    let th4 = document.createElement('th');
    th4.innerHTML = 'STATUS:';

    let th5 = document.createElement('th');
    th5.innerHTML = 'TYPE:';

    let th6 = document.createElement('th');
    th6.innerHTML = 'AUTHOR:'

    let th7 = document.createElement('th');
    th7.innerHTML = 'TIME SUBMITTED:'

    let th8 = document.createElement('th');
    th8.innerHTML = 'TIME RESOLVED:'

    let th9 = document.createElement('th');
    th9.innerHTML = 'RESOLVED BY:'



    // apend the child nodes onto the header
    headerRow.appendChild(th1);
    headerRow.appendChild(th2);
    headerRow.appendChild(th3);
    headerRow.appendChild(th4);
    headerRow.appendChild(th5);
    headerRow.appendChild(th6);
    headerRow.appendChild(th7);
    headerRow.appendChild(th8);
    headerRow.appendChild(th9);
    


    data.forEach(e => {

        console.log(e);

        let row = document.createElement('tr');
        let td1 = document.createElement('td');
        let td2 = document.createElement('td');
        let td3 = document.createElement('td');
        let td4 = document.createElement('td');
        let td5 = document.createElement('td');
        let td6 = document.createElement('td');
        let td7 = document.createElement('td');
        let td8 = document.createElement('td');
        let td9 = document.createElement('td');
        

        // set the inner HTML of each cell to the diff propertie s (firstname, lastname, usewrnam )
        td1.innerHTML = e.id;
        td2.innerHTML = e.amount;
        td3.innerHTML = e.description;
        td4.innerHTML = e.status.status;
        td5.innerHTML = e.type.type;
        td6.innerHTML = e.reimb_AUTHOR.id;
        td7.innerHTML = e.reimb_SUBMITTED;
        
        
       

        // finally append each table cell to the row
        row.appendChild(td1);
        row.appendChild(td2);
        row.appendChild(td3);
        row.appendChild(td4);
        row.appendChild(td5);
        row.appendChild(td6);
        row.appendChild(td7);

        

        // append the row to table
        table.appendChild(row);

        console.log(td4);

    });

}

function fetchResolvedRequests(){

    let hostname = window.location.hostname;

    console.log('fetchAllRequests triggered');

    fetch(`http://localhost:8080/employee-servlet-app/reviewresolved`)
    // this is changed because the port will be inferred when deployed on Elastic beanstalk
    .then(response => response.json()) // trakes a json string and transforms
                                        // it to a javaScript object
    //.then(obj => console.log(obj)); // print the JS obj to the console
    .then(data => buildResolvedRequestsTable(data)); // this automatically passes the data that's been parsed
                      // The JS object is an array of Employee objects
                      // passes to the build table

}

function fetchPendingRequests(){

    let hostname = window.location.hostname;

    console.log('fetchPendingRequests triggered');

    fetch(`http://localhost:8080/employee-servlet-app/reviewpending`)
    // this is changed because the port will be inferred when deployed on Elastic beanstalk
    .then(response => response.json()) // trakes a json string and transforms
                                        // it to a javaScript object
    //.then(obj => console.log(obj)); // print the JS obj to the console
    .then(data => buildPendingTable(data)); // this automatically passes the data that's been parsed
                      // The JS object is an array of Employee objects
                      // passes to the build table

}

function buildTable(data) {

    console.log('buildTable method triggered');

    console.log(data);

    let header = document.createElement('thead'); // these are HTML elements
    let headerRow = document.createElement('tr');

    header.appendChild(headerRow);
    table.appendChild(header); 

    // create a header column for FirstName
    let th1 = document.createElement('th');
    th1.innerHTML = 'id';

    // create a header column for last Name
    let th2 = document.createElement('th');
    th2.innerHTML = 'FIRST NAME:';

    // create a header column for username
    let th3 = document.createElement('th');
    th3.innerHTML = 'LAST NAME:';

    let th4 = document.createElement('th');
    th4.innerHTML = 'USERNAME:';

    let th5 = document.createElement('th');
    th5.innerHTML = 'PASSWORD:';



    // apend the child nodes onto the header
    headerRow.appendChild(th1);
    headerRow.appendChild(th2);
    headerRow.appendChild(th3);
    headerRow.appendChild(th4);
    headerRow.appendChild(th5);
    


    data.forEach(e => {

        console.log(e);

        let row = document.createElement('tr');
        let td1 = document.createElement('td');
        let td2 = document.createElement('td');
        let td3 = document.createElement('td');
        let td4 = document.createElement('td');
        let td5 = document.createElement('td');
        

        // set the inner HTML of each cell to the diff propertie s (firstname, lastname, usewrnam )
        td1.innerHTML = e.id;
        td2.innerHTML = e.firstName;
        td3.innerHTML = e.lastName;
        td4.innerHTML = e.username;
        td5.innerHTML = e.password;
       

        // finally append each table cell to the row
        row.appendChild(td1);
        row.appendChild(td2);
        row.appendChild(td3);
        row.appendChild(td4);
        row.appendChild(td5);
        

        // append the row to table
        table.appendChild(row);

    });

}

function fetchEmps() {

    // Fetch API is modern interface that allows you
    // to make HTTP requests to a server and process the results that 
    // you get back asynchrnously
    let hostname = window.location.hostname;  // this will grab the IP of where it's deployed  

    console.log('fetchEmps triggered')

    // this is a template literal (introduced in ES6)

    // the following request will work for testing on localhost
    
    fetch(`http://localhost:8080/employee-servlet-app/employees`)
    // this is changed because the port will be inferred when deployed on Elastic beanstalk
    .then(response => response.json()) // trakes a json string and transforms
                                        // it to a javaScript object
    //.then(obj => console.log(obj)); // print the JS obj to the console
    .then(data => buildTable(data)); // this automatically passes the data that's been parsed
                      // The JS object is an array of Employee objects
                      // passes to the build table

}