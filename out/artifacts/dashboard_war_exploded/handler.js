var ws = new WebSocket("ws://localhost:8080/dashboard/ws");
var mydata = [];
ws.onmessage = function (event) {

    // JSON object
    let obj = JSON.parse(event.data)
    mydata.push(event.data);
    //data.push(event.data);
    console.log(mydata);
    //console.log([1].temperature);
    //let myString = JSON.stringify(obj)

    // Set data
    $("#temperature > .content").text(obj.temperature + " °C");
    $("#humidity > .content").text(obj.humidity + " %");

    // Temperature status logic handler
    if(obj.tempAlert === "true"){
        let time;
        $("#temp-alert > .content").text(obj.temperature + "°C - " + newDate(time));
        $("#temperature").removeClass("primary").addClass("danger");
    } else {
        $("#temperature").removeClass("danger").addClass("success");
    }

    if(obj.humidAlert === "true"){
        let time;
        $("#humid-alert > .content").text(obj.humidity + "°% - " + newDate(time));
        $("#humidity").removeClass("primary").addClass("danger");
    } else {
        $("#humidity").removeClass("danger").addClass("success");
    }

}

function newDate(d){
    let today = new Date();
    return today.getFullYear()+'-'+(today.getMonth()+1)+'-'+today.getDate() + " - " + today.getHours() + ":" + today.getMinutes() + ":" + today.getSeconds();
}


$(function () {
    $('#table').bootstrapTable({
        data: mydata
    });
});

var $table = $('#table');
