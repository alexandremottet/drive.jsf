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
    $("#uploadForm\\:fileUploadComponent").trigger( "click" );
});

$("#uploadForm\\:fileUploadComponent").on('change', function() {
    $("#uploadForm\\:fileUploadComponentButton").trigger( "click" );
});