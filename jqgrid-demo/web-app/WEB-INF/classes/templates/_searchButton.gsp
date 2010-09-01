// Create a custom 'search' button for the toolbar
$('#${gridVals.id}Grid').navButtonAdd('#${gridVals.id}GridPager', {
    caption:"${gridVals.caption}",
    buttonicon:"ui-icon-search",
    onClickButton:function() {
        // We are doing inline search/filtering so when the user clicks
        // search either display or hide the filter/search row
        $('#${gridVals.id}Grid')[0].toggleToolbar();
    }
});