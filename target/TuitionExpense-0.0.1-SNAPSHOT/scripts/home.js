
function viewPendingExpense(){
	
	let empId = document.getElementById("emp").value
	console.log(empId)
    //Start by creating an XMLHttpRequest object:
    let xhr = new XMLHttpRequest() //readyState 0
    //We need to define what we want to do when the readyState is 4 (meaning that the response body has been populated on the server side); 
    //in our case, we want to put info on the page for the user to see. We can use the onreadystatechange event listener to listen for changes to our readyState. In essence, this listener is invoked each time the readyState changes.
    xhr.onreadystatechange = function(){
        //You decide what to do each time the readyState changes in this function! Be sure to check that the readyState is 4 and and that the response code is 200 (meaning that everything went smoothly)
        if(xhr.readyState === 4 & xhr.status === 200){
            //JSON.parse is a convenience function for parsing JSON as a JavaScript object
            let pendingExpense = JSON.parse(xhr.response)
            console.log('the ready state was 4 and the status was 200')
            console.log(pendingExpense)
            let table = document.getElementById("myTable")
            //Create new tr for each request and new td for each field
            //Assign text from JSON 
       for (let i of pendingExpense ){      
           let row = document.createElement('tr')
       let amount = document.createElement('td')
       amount.innerHTML = i.amount;
       let authorizedBy = document.createElement('td')
       authorizedBy.innerHTML = i.authorizedBy;
       let comments = document.createElement('td')
       comments.innerHTML = i.comments;
       let details = document.createElement('td')
       details.innerHTML = i.details;
       let employeeId = document.createElement('td')
       employeeId.innerHTML = i.employeeId;
       let expenseId = document.createElement('td')
       expenseId.innerHTML = i.expenseId;
       let expenseImage = document.createElement('td')
       expenseImage.innerHTML = i.expenseImage;
       let status = document.createElement('td')
       status.innerHTML = i.status;  
	   let submissionDate = document.createElement('td')
       submissionDate.innerHTML = i.submissionDate;        
           //Append all td to tr  
           row.append(amount);
           row.append(authorizedBy);
           row.append(comments);
           row.append(details);
		   row.append(expenseImage);
 		   row.append(status);
           row.append(submissionDate);
		   row.append(expenseId);
           row.append(employeeId);
           
           
          
           
        //Append tr to table  
           table.append(row)
        }
    }
}
//Open my XMLHttpRequest, specifying my HTTP verb and the endpoint I would like to hit.
    xhr.open('GET', 'http://localhost:8080/TuitionExpense/dispatcher/Expense/pending/view?employeeId=' + empId ) //readyState 1
    xhr.send() //readyState 2

}

// View employee info 

function viewEmployeeInfo(){
	
	let empId = document.getElementById("emp").value
	console.log(empId)
    //Start by creating an XMLHttpRequest object:
    let xhr = new XMLHttpRequest() //readyState 0
    //We need to define what we want to do when the readyState is 4 (meaning that the response body has been populated on the server side); 
    //in our case, we want to put info on the page for the user to see. We can use the onreadystatechange event listener to listen for changes to our readyState. In essence, this listener is invoked each time the readyState changes.
    xhr.onreadystatechange = function(){
        //You decide what to do each time the readyState changes in this function! Be sure to check that the readyState is 4 and and that the response code is 200 (meaning that everything went smoothly)
        if(xhr.readyState === 4 & xhr.status === 200){
            //JSON.parse is a convenience function for parsing JSON as a JavaScript object
            let employeeInfo = JSON.parse(xhr.response)
            console.log('the ready state was 4 and the status was 200')
            console.log(employeeInfo)
            let table = document.getElementById("myTableInfo")
            //Create new tr for each request and new td for each field
            //Assign text from JSON 
       for (let i of employeeInfo){      
           let row = document.createElement('tr')
       let employeeId = document.createElement('td')
       employeeId.innerHTML = i.employeeId;
       let dob = document.createElement('td')
       dob.innerHTML = i.dob;
       let email = document.createElement('td')
       email.innerHTML = i.email;
       let firstName = document.createElement('td')
       firstName.innerHTML = i.firstName;
       let lastName = document.createElement('td')
       lastName.innerHTML = i.lastName;
       let maritalStatus = document.createElement('td')
       maritalStatus.innerHTML = i.maritalStatus;
       let password = document.createElement('td')
       password.innerHTML = i.password;
       let position = document.createElement('td')
       position.innerHTML = i.position;  
	   let departmentId = document.createElement('td')
       departmentId.innerHTML = i.departmentId ;  
	   let managerId = document.createElement('td')
       managerId.innerHTML = i.managerId ;      
           //Append all td to tr  
           row.append(employeeId);
           row.append(dob);
           row.append(email);
           row.append(firstName);
		   row.append(lastName);
 		   row.append(maritalStatus);
           row.append(password);
		   row.append(position);
           row.append(departmentId);
		   row.append(managerId);
           
           
          
           
        //Append tr to table  
           table.append(row)
        }
    }
}
//Open my XMLHttpRequest, specifying my HTTP verb and the endpoint I would like to hit.
    xhr.open('GET', 'http://localhost:8080/TuitionExpense/dispatcher/employee/info?employeeId='+ empId) //readyState 1
    xhr.send() //readyState 2

}


/*
function getInfo(){
    //Start by creating an XMLHttpRequest object:
    let xhr = new XMLHttpRequest() //readyState 0
    //We need to define what we want to do when the readyState is 4 (meaning that the response body has been populated on the server side);
    //in our case, we want to put info on the page for the user to see. We can use the onreadystatechange event listener to listen for changes to our readyState. In essence, this listener is invoked each time the readyState changes.
    xhr.onreadystatechange = function(){
        //You decide what to do each time the readyState changes in this function! Be sure to check that the readyState is 4 and and that the response code is 200 (meaning that everything went smoothly)
        if(xhr.readyState === 4 & xhr.status === 200){
            //JSON.parse is a convenience function for parsing JSON as a JavaScript object
            let requests = JSON.parse(xhr.response)
            console.log('the ready state was 4 and the status was 200')
            console.log(requests)
            let emptyDiv = document.getElementById('addHere')
            //Create new tr for each request and new td for each field
            //Assign text from JSON
       for (let r of requests){
           let row = document.createElement('tr')
       let id = document.createElement('td')
       id.innerHTML = r.requestId;
       let school = document.createElement('td')
       school.innerHTML = r.school;
       let course = document.createElement('td')
       course.innerHTML = r.course;
       let price = document.createElement('td')
       price.innerHTML = r.price;
       let receipt = document.createElement('td')
       receipt.innerHTML = r.receipt;
       let status = document.createElement('td')
       status.innerHTML = r.status;
       let manager = document.createElement('td')
       manager.innerHTML = r.manager.firstName +" "+ r.manager.lastName;
       let comments = document.createElement('td')
       comments.innerHTML = r.comments;
           //Append all td to tr
           row.append(id);
           row.append(school);
           row.append(course);
           row.append(price);
           row.append(receipt);
           row.append(manager);
           row.append(status);
           row.append(manager);
           row.append(comments);
        //Append tr to table
           emptyDiv.append(row)
        }
    }
}
    //Open my XMLHttpRequest, specifying my HTTP verb and the endpoint I would like to hit.
    xhr.open('GET', 'http://localhost:8080/FreeTuition/MyRequestsServlet') //readyState 1
    xhr.send() //readyState 2
}
console.log("This is JS file new")
//Info will appear as soon as the web page loads.
//Use window's onload event listener which fires immediately after the browser loads the window
window.onload = () => {
    getInfo()
}
 */