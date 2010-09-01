// Create a custom 'edit' button in our toolbar
$('#${gridVals.id}Grid').navButtonAdd('#${gridVals.id}GridPager', {
    caption:"${gridVals.caption}",
    position: "first",
    buttonicon:"ui-icon-pencil",
    onClickButton:function() {
        // Get selected row
        var rowid = $("#${gridVals.id}Grid").getGridParam("selrow");
        // Must select a row to edit
        if (rowid != null) {
            // direct browser to our 'edit' page
            $(location).attr('href', '${gridVals.url}' + "/" + rowid);
        } else {
            // No row selected, can't edit. Show a dialog
            if ($("#noSelection").length <= 0) {
              $("body").append('<div id="noSelection" style="display: none;" />');
            }

            $("#noSelection").html("You must select a row to edit.");
            $("#noSelection").dialog({
                modal: true,
                title: "Invalid Selection"
            });
        }
    }
});