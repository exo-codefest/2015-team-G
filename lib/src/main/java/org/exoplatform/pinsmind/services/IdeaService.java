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

import java.util.HashMap;
import java.util.Map;

import org.exoplatform.pinsmind.models.Idea;

/**
 * Created by The eXo Platform SAS
 * Author : eXoPlatform
 *          exo@exoplatform.com
 * Jul 7, 2015  
 */
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
  
  private String getUser(){
    return "tao";
  }
  
//FAKE DAO  
  
  private Idea findByName(String name){
    Idea idea = fakeIdeas.get(name);
    if (idea == null){
      idea = fakeIdeas.get("root");
      idea.setName(name);
    }
    return idea;
  }
  
  private Idea saveIdea(Idea idea){
    fakeIdeas.put(idea.getName(), idea);
    return idea;
  }
  
  private void initFakeData(){
    Idea fakeIdea = new Idea("root", "liar");
    Idea subIdea1 = new Idea(fakeIdea,"Ha","Ha");
    Idea subIdea2 = new Idea(fakeIdea,"Lan","Lan");
    Idea subIdea3 = new Idea(fakeIdea,"May","May");
    Idea subIdea4 = new Idea(fakeIdea,"Duong","Duong");
    fakeIdea.addSubIdea(subIdea1);
    fakeIdea.addSubIdea(subIdea2);
    fakeIdea.addSubIdea(subIdea3);
    fakeIdea.addSubIdea(subIdea4);
    fakeIdea.addSubIdea(fakeIdea);
    
    fakeIdeas.put("root", fakeIdea);
  }
  
  
}
