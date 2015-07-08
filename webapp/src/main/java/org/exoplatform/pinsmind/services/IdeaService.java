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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.UUID;

import javax.inject.Singleton;

import org.exoplatform.pinsmind.models.Idea;
import org.exoplatform.pinsmind.models.Idea.Status;
import org.exoplatform.pinsmind.models.RootIdea;
import org.exoplatform.pinsmind.models.SubIdea;

/**
 * Created by The eXo Platform SAS
 * Author : eXoPlatform
 *          exo@exoplatform.com
 * Jul 7, 2015  
 */
@Singleton
public class IdeaService {
  
  private Map<String,Idea> fakeIdeas = new HashMap<String,Idea>();
  //symlinks in JCR
  private Set<Idea> hotIdeas =  new HashSet<Idea>();
  
  public IdeaService(){
    initFakeData();
  }

  public Idea createNewIdea(String name, String description){
    String id = UUID.randomUUID().toString();
    RootIdea newIdea = new RootIdea(id, name,getUser());
    newIdea.setDescription(description);
    return saveIdea(newIdea);
  }
  
  public Idea createSubIdea(String parentId, String name){
    Idea parent = findById(parentId);
    SubIdea idea = new SubIdea(UUID.randomUUID().toString(), name, getUser());
    parent.addSubIdea(idea);
    saveIdea(idea);
    saveIdea(parent);
    return idea;
  }
  
//============ EDIT ============
  
  public Idea like(String id){
    Idea idea = findById(id);
    idea.addLike(getUser());
    //return saveIdea(idea);
    return idea;
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
    Idea id = ideas.pollFirst();
    while (top.size() < n && id != null){
        top.add(id);
        id = ideas.pollFirst();
    }
    return top;
  }
  
//=================== 
  
  private String getUser(){
    return "tao";
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
    RootIdea fakeIdea = new RootIdea(UUID.randomUUID().toString(), "codefest15", "liar");
    SubIdea subIdea1 = new SubIdea(UUID.randomUUID().toString(), "Ha","Ha");
    SubIdea subsubIdea1 = new SubIdea(UUID.randomUUID().toString(), "Saubeo","Ha");
    subIdea1.addSubIdea(subsubIdea1);
    saveIdea(subsubIdea1);
    saveIdea(subIdea1);
    
    SubIdea subIdea2 = new SubIdea(UUID.randomUUID().toString(), "Lan","Lan");
    SubIdea subIdea3 = new SubIdea(UUID.randomUUID().toString(), "May","May");
    SubIdea subIdea4 = new SubIdea(UUID.randomUUID().toString(), "Duong","Duong");
    saveIdea(subIdea2);
    saveIdea(subIdea3);
    saveIdea(subIdea1);

    fakeIdea.addSubIdea(subIdea1);
    fakeIdea.addSubIdea(subIdea2);
    fakeIdea.addSubIdea(subIdea3);
    fakeIdea.addSubIdea(subIdea4);
    
    saveIdea(fakeIdea);
  }
  
  private List<Idea> getFakeList(){
    List<Idea> ideas = new ArrayList<Idea>();
    ideas.addAll(fakeIdeas.values());
    return ideas;
  }

}
