$("#downloadButton").click(function() {
    $("#tableForm\\:commandButtonDownload").trigger( "click" );
});

$("#backButton").click(function() {
    $("#tableForm\\:commandButtonBack").trigger( "click" );
});

$("#deleteButton").click(function() {
    $("#tableForm\\:commandButtonDelete").trigger( "click" );
});

$("#uploadFileButton").click(function() {
    $("#tableForm\\:fileUploadComponent_input").trigger( "click" );
});

$("#tableForm\\:fileUploadComponent_input").on('change', function() {
    setTimeout(function (){
        $("#tableForm\\:fileUploadComponent .start").first().trigger( "click" );
    }, 250);

});

$("#shareButton").click(function () {
    $("#share").show();
});
$("#tableForm\\:shareItem").click(function () {
    $("#share").show();
});
$("#validShare").click(function () {
    $(document.getElementById('tableForm:commandButtonShare')).trigger( "click" );
});