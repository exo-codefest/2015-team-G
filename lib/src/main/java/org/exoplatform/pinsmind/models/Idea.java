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
package org.exoplatform.pinsmind.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by The eXo Platform SAS
 * Author : eXoPlatform
 *          exo@exoplatform.com
 * Jul 7, 2015  
 */
public class Idea implements Serializable {

  private String name;
  private String description;
  private String author;
  
  private List<Idea> subIdeas;
  private Idea parent;
  
  public Idea(String name, String author){
    this(null, name, author);
  }
  
  public Idea(Idea parent, String name, String author){
    this.parent = parent;
    this.name = name;
    this.author = author;
  }
  
  public String getName(){
    return this.name;
  }
  
  public void setName(String name){
    this.name = name;
  }
  
  public void addSubIdea(Idea idea){
    if (subIdeas == null){
      subIdeas = new ArrayList<Idea>();
    }
    this.subIdeas.add(idea);
  }
  
  public boolean isRoot(){
    return (parent== null);
  }

  public boolean isChild(){
    return (parent != null && subIdeas != null);
  }
  
  public boolean isFinal(){
    return (subIdeas == null);
  }
  
  
}