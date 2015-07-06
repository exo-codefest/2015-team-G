@Application(defaultController = PinsMindController.class)
@Portlet(name="PinsMindPortlet")
@Bindings({})

/*
@Scripts(location = AssetLocation.SERVER,
        value = {
            @Script(value = "js/jquery-1.8.3.min.js", id = "jquery"),
            @Script(value = "js/jquery-juzu-utils-0.1.0.js", depends = "jquery", id = "juzu-utils"),
            @Script(value = "js/notif.js", id = "notif", depends = {"jquery", "juzu-utils"})
        }
)
@Stylesheets({
        @Stylesheet(value = "css/style.css", location = AssetLocation.SERVER)
})
*/
package org.exoplatform.pinsmind;

import juzu.Application;
//import juzu.asset.AssetLocation;
//import juzu.plugin.asset.Assets;
//import juzu.plugin.asset.Script;
//import juzu.plugin.binding.Binding;
import juzu.plugin.binding.Bindings;
import juzu.plugin.portlet.Portlet;
