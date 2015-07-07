/*
 * Copyright (C) 2012 eXo Platform SAS.
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation; either version 2.1 of
 * the License, or (at your option) any later version.
 *
 * This software is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this software; if not, write to the Free
 * Software Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA
 * 02110-1301 USA, or see the FSF site: http://www.fsf.org.
 */

package org.exoplatform.pinsmind;

import java.security.Principal;

import juzu.Path;
import juzu.Resource;
import juzu.Response;
import juzu.Route;
import juzu.View;
import juzu.plugin.ajax.Ajax;
import juzu.request.SecurityContext;
import juzu.template.Template;

import javax.inject.Inject;

import org.exoplatform.pinsmind.models.Idea;
import org.exoplatform.pinsmind.services.IdeaService;

public class PinsMindController {

  @Inject
  @Path("board.gtmpl")
  Template board;

  @Inject
  @Path("idea.gtmpl")
  Template ideaPage;
  
  @Inject
  IdeaService ideaService;

  
  @View
  public Response.Content index() {
    return board.with().ok();
  }
  
  @View
  @Route("/idea/{id}")
  public Response.Content show(String id) {
    Idea idea = ideaService.getIdea(id);
    return ideaPage.with().set("idea", idea).ok();
  }

  @Resource
  @Ajax
  @Route("/pin/{id}")
  public Response.Content pin(String id) {
    return Response.Content.ok("pined");
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
