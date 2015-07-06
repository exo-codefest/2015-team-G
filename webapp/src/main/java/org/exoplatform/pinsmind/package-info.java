@Application(defaultController = PinsMindController.class)
@Portlet(name="PinsMindPortlet")
@Bindings({})

/*@Assets(
        location = AssetLocation.SERVER,
        scripts = {
            @Script(src = "js/jquery-1.8.3.min.js", id = "jquery"),
            @Script(src = "js/jquery-juzu-utils-0.1.0.js", depends = "jquery", id = "juzu-utils")
        }
)*/

package org.exoplatform.pinsmind;

import juzu.Application;
//import juzu.asset.AssetLocation;
//import juzu.plugin.asset.Assets;
//import juzu.plugin.asset.Script;
//import juzu.plugin.binding.Binding;
import juzu.plugin.binding.Bindings;
import juzu.plugin.portlet.Portlet;
