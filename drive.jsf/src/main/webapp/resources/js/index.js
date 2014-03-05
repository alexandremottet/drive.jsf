function fadeOutAllModals() {
    $( ".modalPanel" ).each(function() {
        $(this).fadeOut();
    });
}

function blockUI() {
    $('#container').append('<div id="blockUIFrame"></div>');
    $('#container').fadeTo(400, .5);
}

function unblockUI() {
    $('#container').fadeTo(400, 1);
    $('#blockUIFrame').remove();
}

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
    blockUI();
    fadeOutAllModals();
    $("#share").fadeIn();
});

$("#tableForm\\:shareItem").click(function () {
    blockUI();
    fadeOutAllModals();
    $("#share").fadeIn();
});

$("#validShare").click(function () {
    $(document.getElementById('tableForm:commandButtonShare')).trigger( "click" );
});

$("#addFolderButton").click(function () {
    fadeOutAllModals();
});

$(".closeDialog").click(function () {
    unblockUI();
    fadeOutAllModals();
});

$("#addFolderButton").click(function () {
    blockUI();
    fadeOutAllModals();
    $("#addFolderPanel").fadeIn();
});