// Create a custom 'add' button for the toolbar
$('#${gridVals.id}Grid').navButtonAdd('#${gridVals.id}GridPager', {
    caption:"${gridVals.caption}",
    position: "first",
    buttonicon:"ui-icon-plus",
    onClickButton:function() {
        $(location).attr('href', '${gridVals.url}');
    }
});