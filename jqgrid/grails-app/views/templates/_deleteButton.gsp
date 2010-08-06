// Add a custom 'delete' button.
$('#${gridVals.id}Grid').navButtonAdd('#${gridVals.id}GridPager', {
    caption:"${gridVals.deleteCaption}",
    position: "first",
    buttonicon:"ui-icon-trash",
    onClickButton:function() {
        // Custom javascript handler that is executed when our button is clicked
        // Get the row that is selected
        var rowid = $("#${gridVals.id}Grid").getGridParam("selrow");
        // User must select a row for delete to work
        if (rowid != null) {
            // Post to our controller for the delete operation
            $.post('${gridVals.url}', {id: rowid, oper: "del"}, function(data){
                // Server returned success so show message and set it to fade
                if (data.state == "OK") {
                    // Tell the Grid to remove the selected row from the view
                    $("#${gridVals.id}Grid").delRowData(rowid);
                    $('#${gridVals.messageId}').html(data.message);
                    $('#${gridVals.messageId}').show().fadeOut(5000);
                } else {
                    // Server reported failure. Show message and set to fade
                    $('#${gridVals.messageId}').html(data.message);
                    $('#${gridVals.messageId}').show().fadeOut(10000);
                }
            });
        } else {
            // User did not select a row. Show a dialog telling them what is wrong
            if ($("#noSelection").length <= 0) {
              $("body").append('<div id="noSelection" style="display: none;" />');
            }

            $("#noSelection").html("You must select a row to delete.");
            $("#noSelection").dialog({
                modal: true,
                title: "Invalid Selection"
            });
        }
    }
});
