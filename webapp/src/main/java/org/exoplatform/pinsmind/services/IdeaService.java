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
package org.exoplatform.pinsmind.services;

import java.security.Principal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.UUID;

import javax.inject.Inject;
import javax.inject.Singleton;

import juzu.request.SecurityContext;

import org.exoplatform.pinsmind.models.Idea;
import org.exoplatform.pinsmind.models.Idea.Status;
import org.exoplatform.pinsmind.models.RootIdea;
import org.exoplatform.pinsmind.models.SubIdea;
import org.exoplatform.services.security.ConversationState;

/**
 * Created by The eXo Platform SAS
 * Author : eXoPlatform
 *          exo@exoplatform.com
 * Jul 7, 2015  
 */
@Singleton
public class IdeaService {
  
  //private ConversationState state;
  
  private Map<String,Idea> fakeIdeas = new HashMap<String,Idea>();
  //symlinks in JCR
  private Set<Idea> hotIdeas =  new HashSet<Idea>();
  
  /*
  public IdeaService(ConversationState state){
    this.state = state;
    initFakeData();
  }*/
  public IdeaService(){
    initFakeData();
  }
  

  public Idea createNewIdea(String name, String description, SecurityContext context){
    String id = UUID.randomUUID().toString();
    RootIdea newIdea = new RootIdea(id, name, getCurrentUser(context));
    newIdea.setDescription(description);
    return saveIdea(newIdea);
  }
  
  public Idea createSubIdea(String parentId, String name, SecurityContext context){
    Idea parent = findById(parentId);
    SubIdea idea = new SubIdea(UUID.randomUUID().toString(), name, getCurrentUser(context));
    parent.addSubIdea(idea);
    saveIdea(idea);
    saveIdea(parent);
    return idea;
  }
  
//============ EDIT ============
  
  public Idea like(String id, SecurityContext context){
    Idea idea = findById(id);
    idea.addLike(getCurrentUser(context));
    return saveIdea(idea);
  }
  
  public RootIdea close(String id) throws Exception{
    try{
      RootIdea idea = (RootIdea) findById(id);
      if (idea != null){
        idea.close();
        saveIdea(idea);
      }
      return idea;
    }catch(ClassCastException e){
      throw new Exception("Close only subject");
    }
  }
  
  public Idea update(Idea idea){
    if (idea.isHot())
      hotIdeas.add(idea);
    else
      hotIdeas.remove(idea);
    //return saveIdea(idea);
    return idea;
  }
  
//=========== SEARCHING =========
  public Idea getIdea(String id){
    return findById(id);
  }
  
  public Set<Idea> getHotMinds(){
    return hotIdeas;
  }

  public Map<Status,List<Idea>> findAllSubjectOrderByStatus(){
    Map<Status,List<Idea>> ideas = new HashMap<Status,List<Idea>>();
    for (Idea i : fakeIdeas.values()){
      if (i instanceof RootIdea){
        Status s = ((RootIdea) i).getStatus();
        if (ideas.get(s) == null){
          ideas.put(s, new ArrayList<Idea>());
        }
        ideas.get(s).add(i);
      }
    }
    return ideas;
  }
  
  public LinkedList<Idea> getTop(Idea idea,int n){
    TreeSet<Idea> ideas = getAllSubIdea(idea);
    LinkedList<Idea> top = new LinkedList<Idea>();
    Idea id = ideas.pollLast();
    while (top.size() < n && id != null){
        top.add(id);
        id = ideas.pollLast();
    }
    return top;
  }
  
//=================== 
  
  private String getCurrentUser(SecurityContext context) {
    //return state.getCurrent().getIdentity().getUserId());
    Principal user = context.getUserPrincipal();
    if (user == null) {
      return "Anonymous";
    } else {
      return user.getName();
    }
  }
  
  private TreeSet<Idea> getAllSubIdea(Idea idea){
    TreeSet<Idea> ideas = new TreeSet<Idea>();
    for (Idea i : idea.getSubIdeas()){
      ideas.add(i);
      ideas.addAll(getAllSubIdea(i));
    }
    return ideas;
  }
  
  
//FAKE DAO  

  private Idea findById(String id){
    Idea idea = fakeIdeas.get(id);
    return idea;
  }
  
  private Idea saveIdea(Idea idea){
    fakeIdeas.put(idea.getId(), idea);
    return idea;
  }
  
  private void initFakeData(){
    RootIdea fakeIdea = new RootIdea(UUID.randomUUID().toString(), "Codefest 2015", "liar");
    
    fakeIdea.setDescription("Design & program an add-on for simplifying choices in a team.");
    SubIdea subIdea1 = new SubIdea(UUID.randomUUID().toString(), "Scheduling","Ha Le");
    SubIdea subsubIdea1 = new SubIdea(UUID.randomUUID().toString(), "doodle.com","Sau beo");
    subIdea1.addSubIdea(subsubIdea1);
    saveIdea(subsubIdea1);
    saveIdea(subIdea1);
    
    SubIdea subIdea2 = new SubIdea(UUID.randomUUID().toString(), "Make choice","Lan Nguyen");
    SubIdea subIdea3 = new SubIdea(UUID.randomUUID().toString(), "Find groups availability","May Dang");
    SubIdea subIdea4 = new SubIdea(UUID.randomUUID().toString(), "Find restaurant","Duong Tran");
    SubIdea subIdea5 = new SubIdea(UUID.randomUUID().toString(), "Poll voting","Minh Dang");
    SubIdea subIdea6 = new SubIdea(UUID.randomUUID().toString(), "Share knowledge","Vu Nguyen");
    saveIdea(subIdea2);
    saveIdea(subIdea3);
    saveIdea(subIdea4);
    saveIdea(subIdea5);
    saveIdea(subIdea6);

    fakeIdea.addSubIdea(subIdea1);
    fakeIdea.addSubIdea(subIdea2);
    fakeIdea.addSubIdea(subIdea3);
    fakeIdea.addSubIdea(subIdea4);
    fakeIdea.addSubIdea(subIdea5);
    fakeIdea.addSubIdea(subIdea6);
    
    saveIdea(fakeIdea);
    fakeIdea.toogleHot();
    update(fakeIdea);
    
    RootIdea mindmapIdea = new RootIdea(UUID.randomUUID().toString(), "Mind Map", "Terrence Tao");
    mindmapIdea.setDescription("A mind map is a diagram used to visually organize information. ");
    SubIdea mindmapsubIdea1 = new SubIdea(UUID.randomUUID().toString(), "Scheduling","Ha Le");
    SubIdea mindmapsubsubIdea1 = new SubIdea(UUID.randomUUID().toString(), "doodle.com","Sau beo");
    mindmapsubIdea1.addSubIdea(mindmapsubsubIdea1);
    saveIdea(mindmapsubsubIdea1);
    saveIdea(mindmapsubIdea1);

    saveIdea(mindmapIdea);
  }
  
  private List<Idea> getFakeList(){
    List<Idea> ideas = new ArrayList<Idea>();
    ideas.addAll(fakeIdeas.values());
    return ideas;
  }

}
