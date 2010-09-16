$(window).resize(function() {
	$('#${gridVals.id}Grid').fluidGrid({
	      base:'#${gridVals.id}Wrapper',
	      offset: ${gridVals.resizeOffset}
	});
});
