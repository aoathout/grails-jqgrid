function resize_${gridVals.id}_grid() {
  $('#${gridVals.id}Grid').fluidGrid({
      base:'#${gridVals.id}Wrapper',
      offset: ${gridVals.resizeOffset}
})};
$(window).resize(resize_${gridVals.id}_grid);
