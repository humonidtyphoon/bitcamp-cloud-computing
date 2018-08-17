'use strict'
var serverApiAddr = "http://localhost:8181/bitcamp-assignment-04";



$(() => {
	console.log('111');
	$('footer').load(`${serverApiAddr}/html/footer.html`)
});