@Application(defaultController = PinsMindController.class)
@Portlet(name="PinsMindPortlet")
@Bindings({})

@Scripts({
    @Script(value = "jquery.min.js", id = "jquery"),
    @Script(value = "jquery-ui.min.js", id = "jqueryui"),
    @Script(value = "raphael-min.js", id = "raphaelmin", depends = {"jquery", "jqueryui"}),
    @Script(value = "js-mindmap.js", id = "jsmindmap", depends = {"jquery", "jqueryui","raphaelmin"}),
    @Script(value = "script.js", id = "idea", depends = {"jquery", "jqueryui","raphaelmin","jsmindmap"}),
    @Script(value = "pinsmind.js", id="js-pinsmind", depends = "jquery")
  })

@Stylesheets({
  @Stylesheet(value = "style.css", id="pinsmind-css"),
  @Stylesheet(value = "js-mindmap.css", id="mindmap-css")
})

@Assets("*")
//@Assets({"jquery","jqueryui","raphaelmin","jsmindmap","idea","pinsmind","pinsmind-css","mindmap-css"})
package org.exoplatform.pinsmind;

import juzu.Application;
import juzu.plugin.asset.Assets;
import juzu.plugin.asset.Scripts;
import juzu.plugin.asset.Script;
import juzu.plugin.asset.Stylesheet;
import juzu.plugin.asset.Stylesheets;
import juzu.plugin.binding.Bindings;
import juzu.plugin.portlet.Portlet;
