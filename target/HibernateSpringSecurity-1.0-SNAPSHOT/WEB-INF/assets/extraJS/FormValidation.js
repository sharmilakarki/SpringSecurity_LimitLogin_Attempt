/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


var un = document.getElementById("username");
var pa = document.getElementById("password");
var pa2 = document.getElementById("password2");
var em = document.getElementById("email");
var add = document.getElementById("address");
var p = document.getElementById("para");

function checkVal() {
    re = /^\w+$/;
    var s = "only alphabets,numbers and underscore allowed in username";
    if (!re.test(signupForm.username.value)) {
        p.innerHTML = s;
        return false;
    }
}