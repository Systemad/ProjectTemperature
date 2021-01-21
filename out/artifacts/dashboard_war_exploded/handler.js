var ws = new WebSocket("ws://localhost:8080/dashboard/ws");

ws.onmessage = function (event) {

    // JSON object
    let obj = JSON.parse(event.data)

    //let myString = JSON.stringify(obj)

    // Set data
    $("#temperature > .content").text(obj.temperature + " °C");
    $("#humidity > .content").text(obj.humidity + " %");

    console.log(event.data);

    // Temperature status logic handler
    if(obj.temperature < 10 || obj.temperature > 25){
        let time;
        $("#temp-alert > .content").text(obj.temperature + "°C - " + newDate(time));
        $("#temperature").removeClass("primary").addClass("danger");
    } else {
        $("#temperature").removeClass("danger").addClass("success");
    }

    if(obj.humidity < 25 || obj.humidity > 70){
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