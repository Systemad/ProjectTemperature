var ws = new WebSocket("ws://localhost:8080/WebSocketWebProject_war_exploded/ws");

ws.onmessage = function (event) {
    // Received string = [temp humidity]
    const splitString =  event.data;

    // Split string [temp, humid]
    var y = splitString.split(" ");
    // Set data
    $("#temperature > .content").text(y[0] + " °C");
    $("#humidity > .content").text(y[1] + " %");

    // Temperature status logic handler
    if(y[0] < 10 || y[0] > 25){
        $("#temperature").removeClass("primary").addClass("danger");
    } else {
        $("#temperature").removeClass("danger").addClass("success");
    }

    if(y[1] < 25 || y[1] > 70){
        $("#humidity").removeClass("primary").addClass("danger");
    } else {
        $("#humidity").removeClass("danger").addClass("success");
    }
}