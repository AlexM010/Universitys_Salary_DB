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
    let data = {"contract": flag};
     // formData.forEach((value, key) => (data[key] = value));
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
function add_permanent(){
    document.getElementById("newAdd").style.display = "initial";
    document.getElementById("description").innerHTML = "New Permanent";
    document.getElementById("end").style.display = "none";
    document.getElementById("end").style.visibility = "hidden";
    document.getElementById("salary").style.display = "none";
    document.getElementById("salary").style.visibility = "hidden";
    flag = false;
}
function add_contracted(){
    document.getElementById("newAdd").style.display = "initial";
    document.getElementById("description").innerHTML = "New Contracted";
    document.getElementById("end").style.visibility = "visible";
    document.getElementById("end").style.display = "initial";
    document.getElementById("salary").style.visibility = "visible";
    document.getElementById("salary").style.display = "initial";
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