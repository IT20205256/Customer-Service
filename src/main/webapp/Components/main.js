$(document).ready(function() {
    if ($('#alertSuccess').text().trim() == "") {
        $('#alertSuccess').hide();
    }

    $('#alertError').hide();
})

// SAVE
$(document).on("click","#btnSave", function(event) {
    // Clear alerts
    $("#alertSuccess").text(""); 
    $("#alertSuccess").hide(); 
    $("#alertError").text(""); 
    $("#alertError").hide();

    // Form validation
    var status = validateCusForm(); 
    if (status != true) 
    { 
        $("#alertError").text(status); 
        $("#alertError").show(); 
        return; 
    } 

    // if hidItemIDSave value is null set as POST else set as PUT
    var type = ($("#hidCusIDSave").val() == "") ? "POST" : "PUT";

    // ajax communication
    $.ajax({
        url: "CusAPI",
        type: type,
        data: $("#formCus").serialize(),
        dataType: "text",
        complete: function(response, status) {
            onCusSaveComplete(response.responseText, status);
        }
    });
});

// after completing save request
function onCusSaveComplete(response, status) {

    if (status == "success") { //if the response status is success
        var resultSet = JSON.parse(response);

        if (resultSet.status.trim() === "success") { //if the json status is success
            //display success alert
            $("#alertSuccess").text("Successfully saved");
            $("#alertSuccess").show();
    
            //load data in json to html
            $("#divCusGrid").html(resultSet.data);

        } else if (resultSet.status.trim() === "error") { //if the json status is error
            //display error alert
            $("#alertError").text(resultSet.data);
            $("#alertError").show();
        }
    } else if (status == "error") { 
        //if the response status is error
        $("#alertError").text("Error while saving");
        $("#alertError").show();
    } else { 
        //if an unknown error occurred
        $("#alertError").text("Unknown error occurred while saving");
        $("#alertError").show();
    } 

    //resetting the form
    $("#hidCusIDSave").val("");
    $("#formCus")[0].reset();
}

// UPDATE
//to identify the update button we didn't use an id we used a class
$(document).on("click", ".btnUpdate", function(event) 
{ 
    //get item id from the data-itemid attribute in update button
    $("#hidCusIDSave").val($(this).data('cusid')); 
    //get data from <td> element
    $("#Name").val($(this).closest("tr").find('td:eq(0)').text()); 
    $("#Address").val($(this).closest("tr").find('td:eq(1)').text()); 
    $("#Issue").val($(this).closest("tr").find('td:eq(2)').text()); 
    $("#TelNo").val($(this).closest("tr").find('td:eq(3)').text()); 
    $("#Status").val($(this).closest("tr").find('td:eq(4)').text());
}); 

// DELETE
$(document).on("click",".btnRemove", function(event) {
    // ajax communication
    $.ajax({
        url: "CusAPI",
        type: "DELETE",
        data: "Id=" + $(this).data("cusid"),
        dataType: "text",
        complete: function(response, status) {
            onCusDeleteComplete(response.responseText, status);
        }
    });
});

// after completing delete request
function onCusDeleteComplete(response, status) {

    if (status == "success") { //if the response status is success
        var resultSet = JSON.parse(response);

        if (resultSet.status.trim() === "success") { //if the json status is success
            //display success alert
            $("#alertSuccess").text("Successfully deleted");
            $("#alertSuccess").show();
    
            //load data in json to html
            $("#divCusGrid").html(resultSet.data);

        } else if (resultSet.status.trim() === "error") { //if the json status is error
            //display error alert
            $("#alertError").text(resultSet.data);
            $("#alertError").show();
        }
    } else if (status == "error") { 
        //if the response status is error
        $("#alertError").text("Error while deleting");
        $("#alertError").show();
    } else { 
        //if an unknown error occurred
        $("#alertError").text("Unknown error occurred while deleting");
        $("#alertError").show();
    } 
}

// VALIDATION
function validateCusForm() { 
    // Name 
    if ($("#Name").val().trim() == "") 
    { 
        return "Insert Name"; 
    } 
    
    // Address 
    if ($("#Address").val().trim() == "") 
    { 
        return "Insert Address"; 
    } 
    
    // Issue
    if ($("#Issue").val().trim() == "") 
    { 
        return "Insert Issue"; 
    } 
    
     // TelNo
    if ($("#TelNo").val().trim() == "") 
    { 
        return "Insert Phone Number"; 
    } 
    
     // Issue
    if ($("#Status").val().trim() == "--Not Selected--") 
    { 
        return "Insert Status"; 
    } 
    
    // is numerical value 
    var tmpNum = $("#TelNo").val().trim(); 
    if (!$.isNumeric(tmpNum)) 
    { 
        return "Insert a numerical value for Phone Number"; 
    } 
    
    return true; 
} 
 