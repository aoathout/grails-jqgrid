// Found on this site: http://stevenharman.net/blog/archive/2009/08/21/creating-a-fluid-jquery-jqgrid.aspx
(function($) {
  jQuery.jgrid.fluid =
  {
    fluidGrid: function(options)
    {
      var grid = $(this);
      var settings = $.extend(
                        {
                          example: grid.closest('.ui-jqgrid').parent(),
                          offset: 0
                        }, options || {});
  
      var width = $(settings.example).innerWidth() + settings.offset;
      grid.setGridWidth(width);
    }
  }
})(jQuery);
jQuery.fn.extend({ fluidGrid : jQuery.jgrid.fluid.fluidGrid });