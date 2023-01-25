
var permanentSalary = 2000;
function new_permanent(){
     // let data = {
     //     "contract": false,
     //     "name": "Giorghs Mathioudakhs",
     //     "address": "Theokritou 18",
     //     "telephone_num": "6906045239",
     //     "IBAN": "GR4701121149381872825467513",
     //     "bank_name": "Pancreta Bank",
     //     "startDate": "2001-05-23",
     //     "salaryId": 100,
     //     "department": "Mathematics",
     //     "numOfChildren": 2,
     //     "ages": [25,17],
     //     "married": true,
     //     "category": false,
     //     "years": 11,
     //     "main_salary": 20000.00
     // };
    let form = document.getElementById("employeeForm")
    let formData = new FormData(form);
    let data;
    if(flag===false){
        data = {"contract": flag,"main_salary":null};
    }else {
        data = {"contract": flag};
    }

     // formData.forEach((value, key) => (data[key] = value));
    formData.forEach(function (value,key){
        if(key==="married"||key==="category"){
            data[key] = (value === 'true');
        }else if(key === 'years'||key==='numOfChildren'){
            data[key] = parseInt(value.toString());
        }else if(key==="main_salary"){
            data[key] = parseInt(value.toString());
        } else if(key === 'ages'){
            let array = value.split(",").map(Number);
            data[key] = array;
        } else{
            data[key] = value;
        }
    });
    if(flag===false){
        data["main_salary"] = permanentSalary;
    }
    console.log(data["main_salary"]);
    let xhr = new XMLHttpRequest();
    xhr.onload =  function (){
        if(xhr.readyState === 4 && xhr.status === 200){
            console.log("ok");
        }
        else if(xhr.status!==200){
            console.log("not success");
            console.log(xhr.status);
            console.log(xhr.readyState);
        }
    }
        if(flag===false){
            xhr.open('POST', 'AddPermanent');
        }else{
            xhr.open('POST', 'AddContracted');
        }

        xhr.setRequestHeader('Content-type','application/x-www-form-urlencoded');

        xhr.send(JSON.stringify(data));
}
var flag;
var flag2;
function add_permanent(){
    document.getElementById("newAdd").style.display = "initial";
    document.getElementById("description").innerHTML = "New Permanent";
    document.getElementById("end").style.display = "none";
    document.getElementById("end").style.visibility = "hidden";
    document.getElementById("salary").style.display = "none";
    document.getElementById("salary").style.visibility = "hidden";
    document.getElementById("search").style.display = "none";
    flag = false;
}
function add_contracted(){
    document.getElementById("newAdd").style.display = "initial";
    document.getElementById("description").innerHTML = "New Contracted";
    document.getElementById("end").style.visibility = "visible";
    document.getElementById("end").style.display = "initial";
    document.getElementById("salary").style.visibility = "visible";
    document.getElementById("salary").style.display = "initial";
    document.getElementById("search").style.display = "none";
    flag = true;
}

// function new_contracted(){
//     // let form = document.getElementById("signup")
//     // let formData = new FormData(form);
//     let data = {
//         "contract": true,
//         "name": "Alexandros Markodimitrakis",
//         "address": "Fourtounatou 6",
//         "telephone_num": "6981073112",
//         "IBAN": "GR2701121149381872825494513",
//         "bank_name": "Piraeus Bank",
//         "startDate": "2022-05-23",
//         "salaryId": 12,
//         "department": "Programming",
//         "numOfChildren": 4,
//         "ages": [23,18,16,12],
//         "married": true,
//         "category": true,
//         "endDate": "2023-01-01"
//     };
//     // formData.forEach((value, key) => (data[key] = value));
//
//     let xhr = new XMLHttpRequest();
//
//     xhr.onload = new function (){
//         if(xhr.readyState === 4 && xhr.status === 200){
//             console.log("ok");
//         }
//         else if(xhr.status!==200){
//             console.log("not success");
//             console.log(xhr.status);
//             console.log(xhr.readyState);
//         }
//     }
//
//     xhr.open('POST', 'AddContracted');
//     xhr.setRequestHeader('Content-type','application/x-www-form-urlencoded');
//
//     xhr.send(JSON.stringify(data));
// }

function edit(){
    document.getElementById("newAdd").style.display = "none";
    document.getElementById("search").style.display = "block";
    document.getElementById("searchResult").innerHTML= " ";


}
function getUser() {
    let html = "<br><br><form id='updateInfo_form' name='updateInfo_form' onsubmit='change(); return false;'>";
    var xhr = new XMLHttpRequest();
    xhr.onload = function () {
        if (xhr.readyState === 4 && xhr.status === 200) {
            // $("#searchResult").html(createTableFromJSON(JSON.parse(xhr.responseText)));
            const data = JSON.parse(xhr.responseText);
            flag2 = data["contract"];
            // html += "<div class='mb-3'><label for='contract' class='form-label'>First Name</label>" +
            //     "<input id='contract' name='contract' type='text' class='form-control' aria-label='Name' value='" + flag2 + "' /></div>";
            for (const x in data["newData"]){
                let label = x;
                console.log(data["newData"][x]);
                let input = data["newData"][x];

                switch (label){
                    case "contract": {
                        html += "<div class='mb-3'><label for='name' class='form-label'>First Name</label>" +
                            "<input id='name' name='contract' type='text' class='form-control' aria-label='Name' value='" + input + "' /></div>";
                        break;
                    }
                    case "name": {
                        html += "<div class='mb-3'><label for='name' class='form-label'>First Name</label>" +
                            "<input id='name' name='name' type='text' class='form-control' aria-label='Name' value='" + input + "' /></div>";
                        break;
                    }
                    case "address": {
                        html += "<div class='mb-3'><label for='address' class='form-label'>address</label>" +
                            "<input id='address' name='address' type='text' class='form-control' aria-label='Name' value='" + input + "' /></div>";
                        break;
                    }
                    case "phone_number": {
                        html += "<div class='mb-3'><label for='telephone_num' class='form-label'>Phone</label>" +
                            "<input id='telephone_num' name='telephone_num' type='text' class='form-control' aria-label='Name' value='" + input + "' /></div>";
                        break;
                    }
                    case "iban": {
                        html += "<div class='mb-3'><label for='iban' class='form-label'>IBAN</label>" +
                            "<input id='iban' name='IBAN' type='text' class='form-control' aria-label='Name' value='" + input + "' /></div>";
                        break;
                    }
                    case "bank_name": {
                        html += "<div class='mb-3'><label for='bank_name' class='form-label'>bank_name</label>" +
                            "<input id='bank_name' name='bank_name' type='text' class='form-control' aria-label='Name' value='" + input + "' /></div>";
                        break;
                    }
                    case "start_date": {
                        html += "<div class='mb-3'><label for='start_date' class='form-label'>start_date</label>" +
                            "<input id='start_date' name='startDate' type='text' class='form-control' aria-label='Name' value='" + input + "' /></div>";
                        break;
                    }
                    case "end_date": {
                        html += "<div class='mb-3'><label for='end_date' class='form-label'>end_date</label>" +
                            "<input id='end_date' name='endDate' type='text' class='form-control' aria-label='Name' value='" + input + "' /></div>";
                        break;
                    }
                    case "department": {
                        html += "<div class='mb-3'><label for='department' class='form-label'>Department</label>" +
                            "<input id='department' name='department' type='text' class='form-control' aria-label='Name' value='" + input + "' /></div>";
                        break;
                    }
                    case "children": {
                        html += "<div class='mb-3'><label for='children' class='form-label'>Children</label>" +
                            "<input id='children' name='numOfChildren' type='text' class='form-control' aria-label='Name' value='" + input + "' /></div>";
                        html += "<div class='col-sm-4 mb-3'><label for='ages' class='form-label'>ages</label>" +
                            "<input id='ages' name='ages' type='text' class='form-control' aria-label='Name' /></div>";
                        break;
                    }
                    case "category": {
                        html += "<div class='mb-3'><label for='category' class='form-label'>category</label>" +
                            "<input id='category' name='category' type='text' class='form-control' aria-label='Name' value='" + input + "' /></div>";
                        break;
                    }
                    case "years": {
                        html += "<div class='mb-3'><label for='years' class='form-label'>years</label>" +
                            "<input id='years' name='years' type='text' class='form-control' aria-label='Name' value='" + input + "' /></div>";
                        break;
                    }
                    case "years": {
                        html += "<div class='mb-3'><label for='years' class='form-label'>years</label>" +
                            "<input id='years' name='years' type='text' class='form-control' aria-label='Name' value='" + input + "' /></div>";
                        break;
                    }
                }
            }
            html += "<div style='margin-bottom:5px; position: relative; top: 0.5em; color: green;' id='update_msg' class='form-text'></div><br><input type='submit' id='submit' value='Update Info'></form>"
            $("#searchResult").html(html);
        } else if (xhr.status !== 200) {
            $("#searchResult").html("employee doesn't exists");
        }
    };
    var data = $('#searchField').serialize();
    xhr.open('GET', 'edit?'+data);
    xhr.setRequestHeader('Content-type','application/x-www-form-urlencoded');
    xhr.send();
}
function createTableFromJSON(data) {
    var html = "<table><tr><th>Category</th><th>Value</th></tr>";
    for (const x in data) {
        var category = x;
        var value = data[x];
        html += "<tr><td>" + category + "</td><td>" + value + "</td></tr>";
    }
    html += "</table>";
    return html;
}

function change(){
    let form = document.getElementById("updateInfo_form")
    let pname = document.getElementById("searchField").value;
    console.log(pname);
    console.log(flag2);
    let formData = new FormData(form);
    let data = {"contract": flag2,"pname":pname};

    formData.forEach(function (value,key){
        if(key==="married"||key==="category"){
            data[key] = (value === 'true');
        }else if(key === 'years'||key==='numOfChildren'||key==='main_salary'){
            data[key] = parseInt(value.toString());
        }else if(key === 'ages'){
            let array = value.split(",").map(Number);
            data[key] = array;
        } else{
            data[key] = value;
        }
    });

    let xhr = new XMLHttpRequest();
    xhr.onload =  function (){
        if(xhr.readyState === 4 && xhr.status === 200){
            console.log("edited");
        }
        else if(xhr.status!==200){
            console.log("not success");
            console.log(xhr.status);
            console.log(xhr.readyState);
        }
    }

    xhr.open('PUT', 'change');
    xhr.setRequestHeader('Content-type','application/x-www-form-urlencoded');

    xhr.send(JSON.stringify(data));
}