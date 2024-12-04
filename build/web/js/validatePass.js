/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */
function validatePass() {
    const password = document.getElementById('newPassword').value;
    const confirmPassword = document.getElementById('confirmPassword').value;
    const passwordPattern = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=!])(?=\\S+$).{8,15}$";
    
    if(!password.match(passwordPattern)) {
        alert('Password must be 8-15 characters long, include a number, a lowercase letter, an uppercase letter, and a special character.');
        return false;
    }
    if(password !== confirmPassword) {
        alert('Passwords do not match!');
        return false;
    }
    return true;
}

function togglePassVisibility(id) {
    const passwordField = document.getElementById(id);
    const toggleIcon = document.getElementById(id + 'Toggle');
    
    if(passwordField.type === "password") {
        passwordField.type = "text";
        toggleIcon.innerHTML = "&#128065;"; //open eyes
    }else {
        passwordField.type = "password";
        toggleIcon.innerHTML = "&#128065;"; //close eyes
    }
}


