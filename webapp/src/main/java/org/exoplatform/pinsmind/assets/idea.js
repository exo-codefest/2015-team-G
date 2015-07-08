$(document).ready(function() {
//========= Menu ===============
  $('#btn-pin').click(function(){
  	idea.tooglePin(this);
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

  // Ignore right click of browser
  window.oncontextmenu = function() {
        return false;
  };
});

//============= IDEA =============
function Idea(id) {
	this.id = id;
}

Idea.prototype.tooglePin = function(target) {
	$(target).jzAjax('IdeaController.pin()', {
        data: {'id': idea.id},
        success: function (data) {
        	if (data) {
        		$(target).addClass("btn-primary");
            	$(target).attr("title", "Unpin it");
           	} else {
           		$(target).removeClass("btn-primary");
           		$(target).attr("title", "Pin it");
           	}
        }
    });
    return false;
}

Idea.prototype.addSubIdea = function(target,newIdea,callback) {
	$(target).jzAjax('IdeaController.addSub()', {
        data: {'parentId': getIdeaId(target), 'newIdeaName':newIdea},
        success: function (data) {
        	callback(target,data);
        }
    });
}

Idea.prototype.like = function(target) {
	$(target).jzAjax('IdeaController.like()', {
        data: {'name': idea.name},
        success: function (data) {
        	if (data) {
        		$(target).addClass("btn-primary");
            	$(target).attr("title", "Unpin it");
           	} else {
           		$(target).removeClass("btn-primary");
           		$(target).attr("title", "Pin it");
           	}
        }
    });
    return false;
}

function getIdeaId(target){
	//return $(target).html();
	return "codefest15";
}

function focusToNode(id){
	var liId="list-"+id;
	alert("focus to <li id="+liId+">")
	//focus mindmap node
}
