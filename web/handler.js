var ws = new WebSocket("ws://localhost:8080/WebSocketWebProject_war_exploded/ws");

ws.onmessage = function (event) {
    // Received string = [temp humidity]
    const splitString =  event.data;

    // Split string [temp, humid]
    let y = splitString.split(" ");
    // Set data
    $("#temperature > .content").text(y[0] + " °C");
    $("#humidity > .content").text(y[1] + " %");

    // Temperature status logic handler
    if(y[0] < 10 || y[0] > 25){
        let time;
        $("#temp-alert > .content").text(y[0] + "°C - " + newDate(time));
        $("#temperature").removeClass("primary").addClass("danger");
    } else {
        $("#temperature").removeClass("danger").addClass("success");
    }

    if(y[1] < 25 || y[1] > 70){
        let time;
        $("#humid-alert > .content").text(y[1] + "°% - " + newDate(time));
        $("#humidity").removeClass("primary").addClass("danger");
    } else {
        $("#humidity").removeClass("danger").addClass("success");
    }
}

function newDate(d){
    let today = new Date();
    return today.getFullYear()+'-'+(today.getMonth()+1)+'-'+today.getDate() + " - " + today.getHours() + ":" + today.getMinutes();
}