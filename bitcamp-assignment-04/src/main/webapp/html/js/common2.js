'use strict'
var serverApiAddr = "http://localhost:8181/bitcamp-assignment-04";



$(() => {
	console.log('111');
	$('.container > header').load(`${serverApiAddr}/html/header.html`)
	$('.container > footer').load(`${serverApiAddr}/html/footer2.html`)
});