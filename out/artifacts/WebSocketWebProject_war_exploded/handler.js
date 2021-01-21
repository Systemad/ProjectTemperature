var ws = new WebSocket("ws://localhost:8080/WebSocketWebProject_war_exploded/ws");


$(document).ready(function () {
    showGraph();
});


function showGraph()
{
    {
        function (data)
            {
                console.log(data);
                var name = [];
                var marks = [];

                for (var i in data) {
                    name.push(data[i].student_name);
                    marks.push(data[i].marks);
                }

                var chartdata = {
                    labels: name,
                    datasets: [
                        {
                            label: 'Student Marks',
                            backgroundColor: '#49e2ff',
                            borderColor: '#46d5f1',
                            hoverBackgroundColor: '#CCCCCC',
                            hoverBorderColor: '#666666',
                            data: marks
                        }
                    ]
                };

                var graphTarget = $("#graphCanvas");

                var barGraph = new Chart(graphTarget, {
                    type: 'bar',
                    data: chartdata
                });
            };
    }
}

ws.onmessage = function (event) {

    // JSON object
    let obj = JSON.parse(event.data)

    $data = obj.temperature;

}

function newDate(d){
    let today = new Date();
    return today.getFullYear()+'-'+(today.getMonth()+1)+'-'+today.getDate() + " - " + today.getHours() + ":" + today.getMinutes() + ":" + today.getSeconds();
}