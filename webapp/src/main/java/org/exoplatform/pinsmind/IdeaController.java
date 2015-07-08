/*
 * Copyright (C) 2003-2015 eXo Platform SAS.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package org.exoplatform.pinsmind;

import java.security.Principal;

import javax.inject.Inject;

import juzu.Mapped;
import juzu.Resource;
import juzu.Response;
import juzu.Route;
import juzu.plugin.ajax.Ajax;
import juzu.request.SecurityContext;

import org.exoplatform.pinsmind.models.Idea;
import org.exoplatform.pinsmind.services.IdeaService;
import org.json.JSONObject;

/**
 * Created by The eXo Platform SAS
 * Author : eXoPlatform
 *          exo@exoplatform.com
 * Jul 8, 2015  
 */

//Resource controller
public class IdeaController {
  
  @Inject
  IdeaService ideaService;
  
  @Resource
  @Ajax
  @Route("/idea/pin/{name}")
  public Response pin(String name) {
    Idea idea = ideaService.getIdea(name);
    if (idea != null) {
      idea.toogleHot();
      ideaService.update(idea);
      String data = idea.isHot()? "Pinned" : "";
      return Response.ok(data);
    } else {
      return Response.status(500);
    }
  }
  
  @Resource
  @Ajax
  @Route("/idea/like/{id}")
  public Response like(String id) {
    Idea idea = ideaService.like(id);
    return Response.ok(new JSONObject(idea).toString()).withMimeType("text/json");
  }
  
  @Resource
  @Ajax
  @Route("/idea/update")
  public Response update(@Mapped Idea idea){
    Idea persIdea = ideaService.getIdea(idea.getName());
    persIdea.setName(idea.getName());
    ideaService.update(persIdea);
    return Response.ok(new JSONObject(persIdea).toString()).withMimeType("text/json");
  }
  
  
  @Resource
  @Ajax
  @Route("/idea/addSub")
  public Response addSub(String parentId, String newIdeaName){
    Idea idea = ideaService.createSubIdea(parentId, newIdeaName);
    return Response.ok(new JSONObject(idea).toString()).withMimeType("text/json");
  }

  private String getCurrentUser(SecurityContext context) {
    Principal user = context.getUserPrincipal();
    if (user == null) {
      return "Anonymous";
    } else {      
      return user.getName();          
    }
  }
}
