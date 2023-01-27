
var basic_salary_admin ;
var basic_salary_edu ;
var search_bonus ;
var library_bonus;
function init(){
    if(!localStorage.getItem("flag")){
        localStorage.setItem("basic_salary_admin","2000");
        localStorage.setItem("basic_salary_edu","1000");
        localStorage.setItem("search_bonus","200");
        localStorage.setItem("library_bonus","100");
        localStorage.setItem("flag", true);
    }
    basic_salary_admin = parseInt(localStorage.getItem("basic_salary_admin"));
    basic_salary_edu = parseInt(localStorage.getItem("basic_salary_edu"));
    search_bonus = parseInt(localStorage.getItem("search_bonus"));
    library_bonus = parseInt(localStorage.getItem("library_bonus"));
}
function reset() {
    localStorage.clear();
    return "clear";
}


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
        data = {"contract": flag,"main_salary":null,"bonus":0};
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
        }else if(key === 'ages'){
            let array = value.split(",").map(Number);
            data[key] = array;
        } else{
            data[key] = value;
        }
    });
    if(flag===false){
        if(data["category"]===false){
            data["main_salary"] = basic_salary_admin;

        }else if(data["category"]===true){
            data["main_salary"] = basic_salary_edu;
            data["bonus"] = search_bonus;
        }
    }else{
         if(data["category"]===true){
             data["bonus"] = library_bonus;
         }else{
             data["bonus"] = 0;
         }
    }
    console.log(data["main_salary"]);
    console.log(data["main_salary"]);
    let xhr = new XMLHttpRequest();
    xhr.onload =  function (){
        if(xhr.readyState === 4 && xhr.status === 200){
            console.log("ok");
            Swal.fire({
                icon: 'success',
                title: 'Success',
                text: 'Employee added successfully!',
                showConfirmButton: true,
                }).then((result) => {
                if (result.isConfirmed) {
                    window.location.href = "http://localhost:8080/web";
                }
            });
        }
        else if(xhr.status!==200){
            console.log("not success");
            console.log(xhr.status);
            console.log(xhr.readyState);
            Swal.fire({
                icon: 'error',
                title: 'Oops...',
                text: 'Something went wrong!',
                timer: 2000,
                });
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
    document.getElementById("editSalary").style.display = "none";
    document.getElementById("delete").style.display = "none";
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
    document.getElementById("editSalary").style.display = "none";
    document.getElementById("delete").style.display = "none";
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
    document.getElementById("editSalary").style.display = "none";
    document.getElementById("delete").style.display = "none";

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
            if(flag2){
                html += "<div class='mb-3'><label for='contract' class='form-label'>Contract</label>" +
                    "<div onChange='selection()'><select id='userType' name='contract' class='form-select' aria-label='Default select example'> <option value='0' >Permanent</option> <option value='1' selected>Contracted</option> </select> </div></div>";
            }
            else{
                html += "<div class='mb-3'><label for='contract' class='form-label'>Contract</label>" +
                    "<div onChange='selection()'><select id='userType' name='contract' class='form-select' aria-label='Default select example'> <option value='0' selected>Permanent</option> <option value='1'>Contracted</option> </select> </div></div>";
            }


            for (const x in data){
                let label = x;
                console.log(data[x]);
                let input = data[x];

                switch (label){
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
                    case "married": {
                        if(input === 0){
                            html += "<div class='mb-3'><label for='married' class='form-label'>married</label>" +
                                "<div><select id='married' name='married' class='form-select' aria-label='Default select example'> <option value='false' selected>Unmarried</option> <option value='true'>Married</option> </select> </div></div>";
                        }else{
                            html += "<div class='mb-3'><label for='married' class='form-label'>married</label>" +
                                "<div><select id='married' name='married' class='form-select' aria-label='Default select example'> <option value='false'>Unmarried</option> <option value='true' selected>Married</option> </select> </div></div>";
                        }

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
                        if(input === 0){
                            html += "<div class='mb-3'><label for='category' class='form-label'>married</label>" +
                                "<div><select id='category' name='category' class='form-select' aria-label='Default select example'> <option value='false' selected>Administrative</option> <option value='true'>Educational</option> </select> </div></div>";
                        }else{
                            html += "<div class='mb-3'><label for='married' class='form-label'>married</label>" +
                                "<div><select id='category' name='category' class='form-select' aria-label='Default select example'> <option value='false'>Administrative</option> <option value='true' selected>Educational</option> </select> </div></div>";
                        }


                        break;
                    }
                    case "main_salary": {

                        html += "<div class='mb-3'><label for='main_salary' class='form-label'>salary</label>" +
                            "<input id='main_salary' name='main_salary' type='text' class='form-control' aria-label='Name' value='" + input + "' /></div>";

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
    var html = "<table class='mb-3'><tr><th>Category</th><th>Value</th></tr>";
    for (const x in data) {
        var category = x;
        var value = data[x];
        html += "<tr><td>" + category + "</td><td>" + value + "</td></tr>";
    }
    html += "</table>";
    html += "<input type='date' class='form-control mb-3' id='deleteDate' value='2023-01-30'/>"
    html += "<button class='btn btn-danger' onclick='servlet_delete()'>Fire/Retire</button>"

    return html;
}

function change(){
    let form = document.getElementById("updateInfo_form")
    let pname = document.getElementById("searchField").value;
    let bonus;
    console.log(pname);
    console.log(flag2);

    let formData = new FormData(form);
    let data = {"contract": flag2,"pname":pname,"bonus":0,"main_salary":basic_salary_admin};

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
    if(data["contract"]===false){
        if(data["category"]===false){
            data["main_salary"] = basic_salary_admin;

        }else if(data["category"]===true){
            data["main_salary"] = basic_salary_edu;
            data["bonus"] = search_bonus;
        }
    }else{
        if(data["category"]===true){
            data["bonus"] = library_bonus;
        }else{
            data["bonus"] = 0;
        }
    }
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

function editSalary(){
    document.getElementById("newAdd").style.display = "none";
    document.getElementById("search").style.display = "none";
    document.getElementById("delete").style.display = "none";
    document.getElementById("editSalary").style.display = "block";
    document.getElementById("salaryField").innerHTML= " ";
}
function delete_e(){
    document.getElementById("newAdd").style.display = "none";
    document.getElementById("search").style.display = "none";
    document.getElementById("delete").style.display = "block";
    document.getElementById("editSalary").style.display = "none";
    document.getElementById("salaryField").innerHTML= " ";
}
function find_employ(){
    var xhr = new XMLHttpRequest();
    xhr.onload = function () {
        if (xhr.readyState === 4 && xhr.status === 200) {
            $("#deleteResult").html(createTableFromJSON(JSON.parse(xhr.responseText)));
        } else if (xhr.status !== 200) {
            $("#deleteResult").html("employee doesn't exists");
        }
    };
    var data = $('#deleteField').serialize();
    xhr.open('GET', 'deleteEmploy?'+data);
    xhr.setRequestHeader('Content-type','application/x-www-form-urlencoded');
    xhr.send();
}
function servlet_delete(){
    var data = {"user":$('#deleteField').val(),"date":$('#deleteDate').val()};
    var xhr = new XMLHttpRequest();
    xhr.onload = function () {
        if (xhr.readyState === 4 && xhr.status === 200) {
            $("#deleteResult").html("user delete");
            $("#deleteResult").css("color","green")
        } else if (xhr.status !== 200) {
            $("#deleteResult").html("error"+xhr.status);
            $("#deleteResult").css("color","darkred");
        }
    };
    xhr.open('DELETE', 'deleteEmploy');
    xhr.setRequestHeader('Content-type','application/x-www-form-urlencoded');
    xhr.send(JSON.stringify(data));
}
function change_admin_Salary(){

    let temp = document.getElementById("salaryField").value;

    if(temp<=basic_salary_admin){
        document.getElementById("salary_msg").innerHTML = "The new admin salary must be bigger than the past salary of "+ basic_salary_admin;
        document.getElementById("salary_msg").style.color = "darkred";
    }else{
        basic_salary_admin = temp;
        localStorage.setItem("basic_salary_admin",basic_salary_admin);
        servlet_changeSalary(basic_salary_admin);
    }
    console.log(basic_salary_admin);
}
function change_edu_Salary(){

    let temp = document.getElementById("edu_Field").value;

    if(temp<=basic_salary_edu){
        document.getElementById("edu_msg").innerHTML = "The new main salary must be bigger than "+ basic_salary_edu;
        document.getElementById("edu_msg").style.color = "darkred";
    }else{
        basic_salary_edu = temp;
        localStorage.setItem("basic_salary_edu",basic_salary_edu);

        servlet_changeEduSalary(basic_salary_edu);
    }
    console.log(basic_salary_edu);
}
function change_search_bonus(){
    let temp = document.getElementById("sb_Field").value;
    if(temp<=search_bonus ){
        document.getElementById("sb_msg").innerHTML = "The new main salary must be bigger than "+ search_bonus;
        document.getElementById("sb_msg").style.color = "darkred";
    }else{
        search_bonus = temp;
        localStorage.setItem("search_bonus",search_bonus);
        servlet_changeBonus(1);
    }
    console.log(search_bonus);
}
function change_lib_bonus(){

    let temp = document.getElementById("lb_Field").value;
    if(temp<=library_bonus){
        document.getElementById("lb_msg").innerHTML = "The new main salary must be bigger than "+ library_bonus;
        document.getElementById("lb_msg").style.color = "darkred";
    }else{
        library_bonus = temp;
        localStorage.setItem("library_bonus",library_bonus);
        servlet_changeBonus(0);
    }
    console.log(library_bonus);
}
function servlet_changeSalary(new_salary){
    let xhr = new XMLHttpRequest();
    xhr.onload =  function (){
        if(xhr.readyState === 4 && xhr.status === 200){
            document.getElementById("salary_msg").innerHTML = "Success";
            document.getElementById("salary_msg").style.color = "green";
        }
        else if(xhr.status!==200){
            document.getElementById("salary_msg").innerHTML = "Error-" + xhr.status;
            document.getElementById("salary_msg").style.color = "darkred";
            console.log(xhr.status);
            console.log(xhr.readyState);
        }
    }
    xhr.open('PUT', 'changeSalary');
    xhr.setRequestHeader('Content-type','application/x-www-form-urlencoded');
    xhr.send(new_salary);
}
function servlet_changeEduSalary(new_salary){
    let xhr = new XMLHttpRequest();
    xhr.onload =  function (){
        if(xhr.readyState === 4 && xhr.status === 200){
            document.getElementById("edu_msg").innerHTML = "Success";
            document.getElementById("edu_msg").style.color = "green";
        }
        else if(xhr.status!==200){
            document.getElementById("edu_msg").innerHTML = "Error-" + xhr.status;
            document.getElementById("edu_msg").style.color = "darkred";
            console.log(xhr.status);
            console.log(xhr.readyState);
        }
    }
    xhr.open('PUT', 'changeEdu_salary');
    xhr.setRequestHeader('Content-type','application/x-www-form-urlencoded');
    xhr.send(new_salary);
}
function servlet_changeBonus(flag){
    let data = {"admin_salary":basic_salary_admin,"edu_salary":basic_salary_edu,"bonus_search":search_bonus,"bonus_lib":library_bonus};
    let xhr = new XMLHttpRequest();
    xhr.onload =  function (){
        if(xhr.readyState === 4 && xhr.status === 200){
            if(flag===1){
                document.getElementById("sb_msg").innerHTML = "Success";
                document.getElementById("sb_msg").style.color = "green";
            }else{
                document.getElementById("lb_msg").innerHTML = "Success";
                document.getElementById("lb_msg").style.color = "green";
            }
        }
        else if(xhr.status!==200){
            if(flag===1){
                document.getElementById("sb_msg").innerHTML = "Error-" + xhr.status;
                document.getElementById("sb_msg").style.color = "darkred";
            }else{
                document.getElementById("lb_msg").innerHTML = "Error-" + xhr.status;
                document.getElementById("lb_msg").style.color = "darkred";
            }

            console.log(xhr.status);
            console.log(xhr.readyState);
        }
    }
    xhr.open('PUT', 'change_bonus');
    xhr.setRequestHeader('Content-type','application/x-www-form-urlencoded');
    xhr.send(JSON.stringify(data));
}

function make_payments(){
    document.getElementById("newAdd").style.display = "none";
    document.getElementById("search").style.display = "none";
    document.getElementById("delete").style.display = "none";
    document.getElementById("editSalary").style.display = "none";
    document.getElementById("payments").style.display = "block";
}
function servlet_payments(){
    let date = document.getElementById("dates").value;
    let data = {"date": date,"admin_salary":basic_salary_admin,"edu_salary":basic_salary_edu,"bonus_search":search_bonus,"bonus_lib":library_bonus};
    let xhr = new XMLHttpRequest();
    xhr.onload =  function (){
        if(xhr.readyState === 4 && xhr.status === 200){
            console.log("payments done")
        }
        else if(xhr.status!==200){

            console.log(xhr.status);
            console.log(xhr.readyState);
        }
    }
    xhr.open('POST', 'doPayments');
    xhr.setRequestHeader('Content-type','application/x-www-form-urlencoded');
    xhr.send(JSON.stringify(data));
}

function show_payments(){
    let xhr = new XMLHttpRequest();
    xhr.onload =  function (){
        if(xhr.readyState === 4 && xhr.status === 200){
            let str =xhr.responseText;
            str = replaceEuro(str);
            document.getElementById("payments-msg").innerHTML = str;
            console.log(xhr.responseText)
        }
        else if(xhr.status!==200){
            console.log(xhr.status);
            console.log(xhr.readyState);
        }
    }
    xhr.open('GET', 'doPayments');
    xhr.setRequestHeader('Content-type','application/x-www-form-urlencoded');
    xhr.send();
}
function replaceEuro(str) {
    return str.replace(/\?/g, "&euro;");
}