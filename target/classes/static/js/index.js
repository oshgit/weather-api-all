function doWeatherRequest(){
	resetLabels();
	$.get("/api/weather/" + $("#city").val(),renderResponse).fail(errorResponse);
}

function renderResponse(data) {
    $("#date").html(data.date);
    $("#name").html(data.name);
    $("#description").html(data.description);
    $("#temperature").html(data.temperature);
    $("#sunrise").html(data.sunrise);
    $("#sunset").html(data.sunset);
}

function errorResponse(data){
	$("#error").html("Server status response: " + data.status + "<br>" + "Code error: " + data.responseJSON.code + "<br>Message error: " + data.responseJSON.message + "<div  id = 'stackTraceLegend' onclick = 'toggle()' style = 'cursor:pointer;'><B>See all stacktrace...</B></div></div><div id = 'stackTrace' style = 'display:none;'>StackTrace error: " + data.responseJSON.stackTrace + "</div>");
}

function resetLabels(){
    $("#date").html("");
    $("#name").html("");
    $("#description").html("");
    $("#temperature").html("");
    $("#sunrise").html("");
    $("#sunset").html("");
    $("#error").html("");
}

function toggle(){
	$("#stackTrace").toggle();
}