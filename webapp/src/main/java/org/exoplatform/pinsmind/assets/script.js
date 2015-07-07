// load the mindmap
$(document).ready(function() {
//========= Menu ===============
	$('#btn-pin').click(function () {
	    $(this).jzAjax('PinsMindController.pin()', {
	        data: {'id': 'codefest15'},
	        success: function (data) {
	            alert(data);
	            tooglePin();
	        }
	    });
	    return false;
	});

//========= Mindmap =================
  // enable the mindmap in the mindmap-container
  $('#mindmap-container').mindmap();

  // add the data to the mindmap
  var root = $('#mindmap-container>ul>li').get(0).mynode = $('#mindmap-container').addRootNode($('#mindmap-container>ul>li>a').text(), {
    href:'/',
    url:'/',
    onclick:function(node) {
      $(node.obj.activeNode.content).each(function() {
        this.hide();
      });
    }
  });
  $('#mindmap-container>ul>li').hide();
  var addLI = function() {
    var parentnode = $(this).parents('li').get(0);
    if (typeof(parentnode)=='undefined') parentnode=root;
      else parentnode=parentnode.mynode;
    
    this.mynode = $('#mindmap-container').addNode(parentnode, $('a:eq(0)',this).text(), {
//          href:$('a:eq(0)',this).text().toLowerCase(),
      href:$('a:eq(0)',this).attr('href'),
      onclick:function(node) {
        $(node.obj.activeNode.content).each(function() {
          this.hide();
        });
        $(node.content).each(function() {
          this.show();
        });
      }
    });
    $(this).hide();
    $('>ul>li', this).each(addLI);
  };
  $('#mindmap-container>ul>li>ul').each(function() { 
    $('>li', this).each(addLI);
  });

  $('svg').appendTo('#mindmap-container');

  //ignore default settings
  window.oncontextmenu = function() {
        return false;
  };
});

function tooglePin() {
	$('#btn-pin').html("<} Changed!");
}

