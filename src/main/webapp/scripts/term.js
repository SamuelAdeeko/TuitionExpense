/**
 * 
 */
let originalColors = true


function changeColors(){
	// select the web element that I want to manipulate
	let bodyHead = document.getElementById('pageTitle')
	
	// change color of Id pageTitle
	if(this.originalColors){
		bodyHead.style.color = 'blue'
	} else {
		bodyHead.style.color = 'black'
	}
	
	// update the state (original color) to reflect that this function has been called and the text color has been updated

	this.originalColors = !this.changeColors

}
// currently the above function doesn't listen to any event thus we can't use it to modify the colors in our text.
// We will need event listeners to add to the element. A callback function is a  function that is passed to another
// function as an argument and later invoked within that function

let titleH1Div = document.getElementById('titleH1')

// now we can add an event listener to this element 

titleH1Div.addEventListener('click', changeColors)

let hasNomessage = true;

/*

function validatePassword(event){
	
	let inputBoxes = document.getElementById('input')
	
	let userPassword = inputBoxes[1].value
	
	if(userPassword.length < 3){
		
		if(hasNomessage){
			let form = document.getElementById('form')
			
			let errorMessage = document.createElement('p')
			
			errorMessage.innerText = 'Password must be at least 3 characters long.'
			
			form.append(errorMessage)
			
			hasNomessage = false
		}
		
		if(event.cancelable){
			event.preventDefault()
		}
	}
	
	}
	
	let button = document.querySelector('button')
	button.addEventListener('click', validatePassword)
	
	
//	titleH1Div.addEventListener('click', () => {
//		window.alert("You have won a free iPhone")},
//		true)
*/		
	

	