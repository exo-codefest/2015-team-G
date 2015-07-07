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
import java.util.List;
import java.util.Map;

import javax.inject.Singleton;

import org.exoplatform.pinsmind.models.Idea;
import org.exoplatform.pinsmind.models.Idea.Status;

/**
 * Created by The eXo Platform SAS
 * Author : eXoPlatform
 *          exo@exoplatform.com
 * Jul 7, 2015  
 */
@Singleton
public class IdeaService {
  
  private Map<String,Idea> fakeIdeas = new HashMap<String,Idea>();
  
  public IdeaService(){
    initFakeData();
  }

  public Idea createNewIdea(String name){
    Idea newIdea = new Idea(name,getUser());
    return saveIdea(newIdea);
  }
  
  public void addIdea(Idea parentIdea, Idea idea){
    parentIdea.addSubIdea(idea);
    saveIdea(parentIdea);
    saveIdea(idea);
  }
  
  public Idea getIdea(String name){
    return findByName(name);
  }
  
  public List<Idea> getHotMinds(){
    return getFakeList();
  }

  public List<Idea> findByStatus(Status status){
    return getFakeList();
  }
  
  public Idea close(String name){
    Idea idea = findByName(name);
    if (idea != null){
      idea.setStatus(Status.CLOSED);
      saveIdea(idea);
    }
    return idea;
  }
  
  public Idea update(Idea idea){
    return saveIdea(idea);
  }
  
  private String getUser(){
    return "tao";
  }
  
  
//FAKE DAO  

  private Idea findByName(String name){
    Idea idea = fakeIdeas.get(name);
    if (idea == null){
      idea = new Idea(name, "liar");
    }
    return idea;
  }
  
  private Idea saveIdea(Idea idea){
    fakeIdeas.put(idea.getName(), idea);
    return idea;
  }
  
  private void initFakeData(){
    Idea fakeIdea = new Idea("codefest15", "liar");
    Idea subIdea1 = new Idea("Ha","Ha");
    Idea subsubIdea1 = new Idea("Saubeo","Ha");
    subIdea1.addSubIdea(subsubIdea1);
    
    Idea subIdea2 = new Idea("Lan","Lan");
    Idea subIdea3 = new Idea("May","May");
    Idea subIdea4 = new Idea("Duong","Duong");
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
