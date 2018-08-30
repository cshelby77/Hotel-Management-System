
/***************************************** 
* Create Guest Controller
*****************************************/
function createGuest() {
	
	    $.ajax({
	        type: 'POST',
	        url: 'create',
	        dataType: 'text',
	        data: $('form#createform').serialize(),
	        success: function(data) {
	                  console.log("Sent");
	        }
	    });
	
}

/***************************************** 
* Guest Controller
*****************************************/

function loadGuest() {
	//TODO: Edit Output
	$.ajax({  
        type: "GET",
        url: "guest",
        xhrFields: { withCredentials: true },
        contentType: "application/json; charset=utf-8",
        dataType: "json",
        success: function(data) {
        	//obj = data;
        	let inputstring = ` <form id="createreg" onsubmit="createReservation()" method="POST">`;
        	//for (let x in data) {
        		let obj = data["rooms"];
        		for(let t in obj){
        		inputstring += `
        				<div>
        					<label>` + obj[t].roomnum + `</label>
        					<label>` + obj[t].type + `</label>
        					<label>` + obj[t].bednum + `</label>
        					<label>` + obj[t].bedsize + `</label>
        					<label>` + obj[t].rentrate + `</label>
        					<label>` + obj[t].description + `</label>
        					<input type="radio" name="room" value=` + obj[t].roomnum +`>
        				</div>
        				<br>
        			`
        	}
        	
        	inputstring += `
        		<label>Number of Guests</label>
        		<input type="number" name="numofguest">
        		<label>Checkin Date</label>
        		<input type="date" name="checkin">
        		<label>Checkout Date</label>
        		<input type="date" name="checkout">
        		<button type="submit">Make Reservation</button>
        		<a href="#guest">
        			<button>Cancel</button>
        		</a> 
        		</form>`

        	document.getElementById("room").innerHTML = inputstring;
		},
		error: function(e) { alert(e.Message); }
	});

}

function changeGuestValue() {
	//TODO: EDIT FORM
	$.ajax({  
        type: "POST",
        url: "guest",
        dataType: "text",
        data: $('form#createreg').serialize(),
        xhrFields: { withCredentials: true },
        success: function(data) {
        	console.log(data);
		},
		error: function(e) { alert(e.Message); }
	});
	
}

/***************************************** 
* Hotel Controller
*****************************************/

function loadRooms() {
	
    $.ajax({  
        type: "GET",
        url: "hotel",
        xhrFields: { withCredentials: true },
        contentType: "application/json; charset=utf-8",
        dataType: "json",
        success: function(data) {
        	//obj = data;
        	let inputstring = ` <form id="createreg" onsubmit="createReservation()" method="POST">`;
        	//for (let x in data) {
        		let obj = data["rooms"];
        		for(let t in obj){
        		inputstring += `
        				<div>
        					<label>` + obj[t].roomnum + `</label>
        					<label>` + obj[t].type + `</label>
        					<label>` + obj[t].bednum + `</label>
        					<label>` + obj[t].bedsize + `</label>
        					<label>` + obj[t].rentrate + `</label>
        					<label>` + obj[t].description + `</label>
        					<input type="radio" name="room" value=` + obj[t].roomnum +`>
        				</div>
        				<br>
        			`
        	}
        	
        	inputstring += `
        		<label>Number of Guests</label>
        		<input type="number" name="numofguest">
        		<label>Checkin Date</label>
        		<input type="date" name="checkin">
        		<label>Checkout Date</label>
        		<input type="date" name="checkout">
        		<button type="submit">Make Reservation</button>
        		<a href="#guest">
        			<button>Cancel</button>
        		</a> 
        		</form>`

        	document.getElementById("room").innerHTML = inputstring;
		},
		error: function(e) { alert(e.Message); }
	});

}

function loadAvailableRooms() {
	
    $.ajax({  
        type: "GET",
        url: "hotel",
        contentType: "application/json; charset=utf-8",
		dataType: "json",
		data: $('form#availableform').serialize(),
        success: function(data) {
        	//obj = data;
        	let inputstring = ` <form id="createreg" onsubmit="createReservation()" method="POST">`;
        	//for (let x in data) {
        		let obj = data["rooms"];
        		for(let t in obj){
        		inputstring += `
        				<div>
        					<label>` + obj[t].roomnum + `</label>
        					<label>` + obj[t].type + `</label>
        					<label>` + obj[t].bednum + `</label>
        					<label>` + obj[t].bedsize + `</label>
        					<label>` + obj[t].rentrate + `</label>
        					<label>` + obj[t].description + `</label>
        					<input type="radio" name="room" value=` + obj[t].roomnum +`>
        				</div>
        				<br>
        			`
			}
			$('form#advailableform').data()
        	
        	inputstring += `
        		<label>Number of Guests</label>
				<input type="number" name="numofguest">
				<label>Checkin Date</label>
				<input type="date" name="checkin">
				<label>Checkout Date</label>
				<input type="date" name="checkout">
        		<button type="submit">Make Reservation</button>
        		<a href="#guest">
        			<button>Cancel</button>
        		</a> 
        		</form>`

        	document.getElementById("room").innerHTML = inputstring;
		},
		error: function(e) { alert(e.Message); }
	});

}

/***************************************** 
* Login Controller
*****************************************/

function login() {
	
	let create = document.getElementById("loginform");
	let temp = "";
    $.ajax({
    	type: 'POST',
        url: 'login',
		dataType: 'text',
        data: $('form#loginform').serialize(),
        success: function(x) {
        	self.location = 'index.html';
		},
		async: false
		//error: function(e) { alert(e.Message); }
    });

	//window.location.href = 'index.html';
}

/***************************************** 
* Logout Controller
*****************************************/

function logout() {

    $.ajax({
    	type: 'POST',
        url: 'logout',
        dataType: 'text',
        xhrFields: { withCredentials: true },
        success: function(x) {
        	temp = x;
		},
		async: false
		//error: function(e) { alert(e.Message); }
    });
	self.location = 'welcome.html';

}

/***************************************** 
* Message Controller
*****************************************/

function loadMessages() {
	//TODO: EDIT INPUT
	$.ajax({  
        type: "GET",
        url: "message",
        xhrFields: { withCredentials: true },
        contentType: "application/json; charset=utf-8",
        dataType: "json",
        success: function(data) {

			let obj = data["messages"];
			let inputstring = "<div>"
			for(let t in obj){
				inputstring += ` <div>`;
				//if(//TODO ALL HOST STUFF cookie = Host){
				// inputstring += `
				//		<form id="editreg" onsubmit="editReservation()" method="POST">
				//			<input type="hidden" name="reg" value=` + obj[t].id +`>
				// `
				//}

				inputstring += `
						<label>From: ` + obj[t].senderid + `</label>
						<label>To: ` + obj[t].receiverid + `</label>
						<label>Issue:` + obj[t].text + `</label>
						<label>Date:` + obj[t].timesent + `</label>
						`
						
			//if(//TODO cookie = Host){
			// inputstring += `	
			//          <select name ="status">
			//				<option value="A">Approve</option>
			//				<option value="D">Deny</option>
			//			</select>
			// <button type="submit">Change Status</button>	
			// </form>
			// <button>Delete Message Chain </button>			`
			//}
			inputstring += `
					</div>
				`
		}
		inputstring += "</div>"

        	document.getElementById("issues").innerHTML = inputstring;
		},
		//error: function(e) { alert(e.Message); }
	});
}

function sendMessage() {
	//TODO: EDIT FORM
	$.ajax({  
        type: "POST",
        url: "message",
        dataType: "text",
        xhrFields: { withCredentials: true },
        data: $('form#createmessform').serialize(),
        success: function(data) {
        	console.log(data);
		},
		//error: function(e) { alert(e.Message); }
	});
}

function deleteMessages() {

	//TODO: EDIT FORM
	$.ajax({  
        type: "DELETE",
        url: "message",
        dataType: "text",
        data: $('form#createreg').serialize(),
        xhrFields: { withCredentials: true },
        success: function(data) {
        	console.log(data);
		},
		error: function(e) { alert(e.Message); }
	});
}

/***************************************** 
* Reservation Controller
*****************************************/
function loadReservations() {
	$.ajax({  
        type: "GET",
        url: "reservation",
        xhrFields: { withCredentials: true },
        contentType: "application/json; charset=utf-8",
        dataType: "json",
        success: function(data) {
			let ptype = getCookie(type);
			let inputstring = ` <div>`;
				let obj = data["reservations"];
        		for(let t in obj){
					inputstring += ` <div>`;
					if(ptype = "Host"){
					 inputstring += `
							<form id="editreg" onsubmit="editReservation()" method="POST">
								<input type="hidden" name="reg" value=` + obj[t].id +`>
					 `
					}

					inputstring += `
        					<label>` + obj[t].roomid + `</label>
        					<label>` + obj[t].numofguest + `</label>
        					<label>` + obj[t].checkin + `</label>
        					<label>` + obj[t].checkout + `</label>
							<label>` + obj[t].status + `</label>
							`
							
				if(ptype = "Host"){
				 inputstring += `	
				          	<select name ="status">
								<option value="A">Approve</option>
								<option value="D">Deny</option>
							</select>
				 	<button type="submit">Change Status</button>	
				 	</form>
							`
				}
				inputstring += "</div>"
			}
			inputstring += "</div>"
			
        	document.getElementById("reservation").innerHTML = inputstring;
		},
		error: function(e) { alert(e.Message); }
	});

}

function createReservation() {

	$.ajax({  
        type: "POST",
        url: "reservation",
        dataType: "text",
        data: $('form#createreg').serialize(),
        xhrFields: { withCredentials: true },
        success: function(data) {
        	console.log(data);
		},
		error: function(e) { alert(e.Message); }
	});

}

function editReservations() {
	//TODO: EDIT FORM
	$.ajax({  
        type: "POST",
        url: "reservation",
        dataType: "text",
        data: $('form#createreg').serialize(),
        xhrFields: { withCredentials: true },
        success: function(data) {
        	console.log(data);
		},
		error: function(e) { alert(e.Message); }
	});
}

/***************************************** 
* Room Controller
*****************************************/
function editRoom() {
	let xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function() {
		if(this.readyState == 4) {
			if(this.status == 200) {
				//TODO
			} else {
				console.log("editRoom() AJAX Error");
			}
		}
	}

	xhr.open('POST','/Project1/RoomController');
	xhr.send();
}



$('#password, #password_confirm').on('keyup', function(){
	if($('#password').val() != $('#password_confirm').val()){
		$('#message').html('Passwords don\'t match').css('color', 'red');
	}
});
 
function cancelFormSubmission(e) {
	e.returnValue = false;
}


function getCookie(name) {
    var v = document.cookie.match('(^|;) ?' + name + '=([^;]*)(;|$)');
    return v ? v[2] : null;
}

