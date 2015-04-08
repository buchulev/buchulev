var username = document.querySelector("input[name='nickname']");
var email = document.querySelector("input[name='email']");
var password = document.querySelector("input[name='password']");
var password_again = document.querySelector("input[name='password_again']");
var submit = document.querySelector("form");


var p_valid = true;
var pa_valid = true;
var email_valid = true;
var username_valid = true;


password.addEventListener('blur', function(e) {
	
	if (password.value != password_again.value) {
		
		var p_err = password.parentNode.querySelector(".error_space");
		var p_err_a = password_again.parentNode.querySelector(".error_space");
		var text = document.createTextNode("Passwords differ");
		var text1 = document.createTextNode("Passwords differ");
		if (p_err.firstChild == undefined) {
			p_valid = false;
			p_err.appendChild(text);
		}
		if (p_err_a.firstChild == undefined) {
			pa_valid = false;
			p_err_a.appendChild(text1);
		}
	} else {
		
		var p_err = password.parentNode.querySelector(".error_space");
		var p_err_a = password_again.parentNode.querySelector(".error_space");

		if (p_err.firstChild != undefined) {
			p_valid = true;
			p_err.removeChild(p_err.firstChild);
		}

		if (p_err_a.firstChild != undefined) {
			pa_valid = true;
			p_err_a.removeChild(p_err_a.firstChild);
		}

	}
});

password_again.addEventListener('blur', function(e) {
	
	if (password.value != password_again.value) {
		pa_valid = false;
		var p_err = password.parentNode.querySelector(".error_space");
		var p_err_a = password_again.parentNode.querySelector(".error_space");
		var text = document.createTextNode("Passwords differ");
		var text1 = document.createTextNode("Passwords differ");
		if (p_err.firstChild == undefined) {
			p_valid = false;
			p_err.appendChild(text);
		}
		if (p_err_a.firstChild == undefined) {
			pa_valid = false;
			p_err_a.appendChild(text1);
		}
	} else {
		pa_valid = true;
		var p_err = password.parentNode.querySelector(".error_space");
		var p_err_a = password_again.parentNode.querySelector(".error_space");

		if (p_err.firstChild != undefined) {
			p_valid= true;
			p_err.removeChild(p_err.firstChild);
		}

		if (p_err_a.firstChild != undefined) {
			pa_valid = true;
			p_err_a.removeChild(p_err_a.firstChild);
		}

	}
});

username.addEventListener('blur', function(e) {
	if (username.value != '') {
		var httpRequest = window.XMLHttpRequest ? new XMLHttpRequest : new ActiveXObject('Microsoft.XMLHTTP');
		httpRequest.open("POST", "ajax_check_username.php");
		httpRequest.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
		httpRequest.send("nickname=" + username.value);
		httpRequest.onreadystatechange = function() {
			if (httpRequest.readyState == 4) {

if(httpRequest.status == 200){

				if (httpRequest.responseText == '') {
                  username_valid = true;
					var p_err = username.parentNode.querySelector(".error_space");
					if (p_err.firstChild != undefined) {
						p_err.removeChild(p_err.firstChild);
					}
				}

				if (httpRequest.responseText == 'e') {
username_valid = false;
					var p_err = username.parentNode.querySelector(".error_space");
					var text = document.createTextNode("Choose another nickname");
					if (p_err.firstChild == undefined) {
						p_err.appendChild(text);
					}
				}
			}
}
		};
	}
	else username_valid = false;

});

email.addEventListener('blur', function(e) {
	if (email.value != '') {
		var httpRequest1 = window.XMLHttpRequest ? new XMLHttpRequest : new ActiveXObject('Microsoft.XMLHTTP');
		httpRequest1.open("POST", "ajax_check_email.php");
		httpRequest1.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
		httpRequest1.send("email=" + email.value);

		httpRequest1.onreadystatechange = function() {
			if (httpRequest1.readyState == 4 && httpRequest1.status == 200) {

				if (httpRequest1.responseText == 'e') {
email_valid = false;
					var p_err = email.parentNode.querySelector(".error_space");
					var text = document.createTextNode("Choose another email adress");

					if (p_err.firstChild == undefined) {

						p_err.appendChild(text);
					}
				}

				if (httpRequest1.responseText == '') {
email_valid = true;
					var p_err = email.parentNode.querySelector(".error_space");
					if (p_err.firstChild != undefined) {
						p_err.removeChild(p_err.firstChild);
					}
				}

			}

		};
	}
else email_valid = false;
});

submit.addEventListener('submit', function(e){
	console.log(p_valid);
	console.log(pa_valid);
	console.log(email_valid);
	console.log(username_valid);
	if(p_valid && pa_valid && email_valid && username_valid) {
		submit.submit();
	}
	
	else e.preventDefault();
});
