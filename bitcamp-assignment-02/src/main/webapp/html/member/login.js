"use strict"

var data = null;
var {id, page, size} = $.parseQuery(location.href);

$(eListBtn).click(function() {
    if (page) {
        location.href = `list.html?page=${page}&size=${size}`;
    } else {
        location.href = 'list.html';
    }
});