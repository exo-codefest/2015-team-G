@Application(defaultController = PinsMindController.class)
@Portlet(name="PinsMindPortlet")
@Bindings({
  @Binding(IdeaService.class)
})

@Scripts({
    @Script(value = "jquery.min.js", id = "jquery"),
    @Script(value = "jquery-ui.min.js", id = "jqueryui"),
    @Script(value = "raphael-min.js", id = "raphaelmin", depends = {"jquery", "jqueryui"}),
    @Script(value = "js-mindmap.js", id = "jsmindmap", depends = {"jquery", "jqueryui","raphaelmin"}),
    @Script(value = "idea.js", id = "idea-js", depends = {"jquery", "jqueryui"}),
    @Script(value = "mindboard.js", id="mindboard-js", depends = "jquery")
  })

@Stylesheets({
  @Stylesheet(value = "style.css", id="pinsmind-css"),
  @Stylesheet(value = "js-mindmap.css", id="mindmap-css")
})

//@Assets("*")
@Assets({"jquery","jqueryui","pinsmind-css"})
package org.exoplatform.pinsmind;

import org.exoplatform.pinsmind.services.IdeaService;

import juzu.Application;
import juzu.plugin.asset.Assets;
import juzu.plugin.asset.Scripts;
import juzu.plugin.asset.Script;
import juzu.plugin.asset.Stylesheet;
import juzu.plugin.asset.Stylesheets;
import juzu.plugin.binding.Bindings;
import juzu.plugin.binding.Binding;
import juzu.plugin.portlet.Portlet;
