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
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import juzu.Action;
import juzu.Mapped;
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
import org.exoplatform.pinsmind.models.Idea.Status;
import org.exoplatform.pinsmind.services.IdeaService;
import org.json.JSONObject;

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
    Set<Idea> hotList = ideaService.getHotMinds();
    Map<Status,List<Idea>> ideas = ideaService.findAllSubjectOrderByStatus(); 
    List<Idea> backlogList = ideas.get(Status.BACKLOG);
    List<Idea> progList = ideas.get(Status.PROGRESS);
    List<Idea> closedList = ideas.get(Status.CLOSED);
    return board.with()
        .set("hotList", (hotList != null) ? hotList : new ArrayList<Idea>())
        .set("backlogList", (backlogList != null) ? backlogList : new ArrayList<Idea>())
        .set("progList", (progList != null) ? progList : new ArrayList<Idea>())
        .set("closedList", (closedList != null) ? closedList : new ArrayList<Idea>())
        .ok()
        .withAssets("mindboard-js");
  }
  
  @View
  @Route("/idea/{id}")
  public Response.Content show(String id) {
    Idea idea = ideaService.getIdea(id);
    return ideaPage.with()
        .set("idea", idea)
        .set("mindmapHtml",generateHtml(idea))
        .set("top3", ideaService.getTop(idea, 3))
        .ok()
        .withAssets("raphaelmin","jsmindmap","idea-js","mindmap-css");
  }
  
  @View
  public Response.Content createNew(String name, String description) {
    Idea idea = ideaService.createNewIdea(name, description);
    return ideaPage.with()
                   .set("idea", idea)
                   .set("mindmapHtml", generateHtml(idea))
                   .ok()
                   .withAssets("raphaelmin","jsmindmap","idea-js","mindmap-css");
  }

  private String getCurrentUser(SecurityContext context) {
    Principal user = context.getUserPrincipal();
    if (user == null) {
      return "Anonymous";
    } else {
      return user.getName();
    }
  }

  private String generateHtml(Idea idea){
    StringBuilder html = new StringBuilder();
    html.append("<li id=\"list-"+idea.getId()+"\"><a href=\"#\">");
    html.append(idea.getName());
    html.append("</a>");
    List<Idea> subIdeas = idea.getSubIdeas();
    if (subIdeas != null){
      html.append("<ul>");
        for (Idea subIdea : subIdeas){
          html.append(generateHtml(subIdea));
        }
      html.append("</ul>");
    }
    html.append("</li>");
    return html.toString();
  }
}
