function resize_${attrs.id}_grid() {
  $('#${attrs.id}Grid').fluidGrid({
      base:'#${attrs.id}Wrapper',
      offset: ${attrs.resizeOffset}
})};
$(window).resize(resize_${attrs.id}_grid);
