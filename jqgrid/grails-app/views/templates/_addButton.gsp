// Create a custom 'add' button for the toolbar
$('#${gridVals.id}Grid').navButtonAdd('#${gridVals.id}GridPager', {
    caption:"${gridVals.caption}",
    position: "first",
    buttonicon:"ui-icon-plus",
    <g:if test="${!gridVals.addButtonFunction}">
	    onClickButton:function() {
	        $(location).attr('href', '${gridVals.url}');
	    }
	</g:if>
	<g:if test="${gridVals.addButtonFunction}">
		onClickButton: ${gridVals.addButtonFunction}
	</g:if>
});