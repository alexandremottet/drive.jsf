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
    $("#tableForm\\:fileUploadComponent").trigger( "click" );
});

$("#tableForm\\:fileUploadComponent").on('change', function() {
    $("#tableForm\\:fileUploadComponentButton").trigger( "click" );
});