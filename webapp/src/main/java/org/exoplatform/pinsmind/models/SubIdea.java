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

import java.util.UUID;

/**
 * Created by The eXo Platform SAS
 * Author : eXoPlatform
 *          exo@exoplatform.com
 * Jul 8, 2015  
 */
public class SubIdea extends Idea implements Comparable<SubIdea>{
  
  private Idea parent;

  public SubIdea(String id, String name, String author) {
    super(id, name, author);
  }
  
  public Idea getParent(){
    return this.parent;
  }
  
  public void setParent(Idea idea){
    this.parent = idea;
  }
  
  @Override
  public void addLike(String username){
    removeFromParent(username);
    this.addLike(username);
  }
  
  private boolean removeFromParent(String username){
    return false;
  }
  
  
  /**
   * 
   */
  private static final long serialVersionUID = 1L;

  public int compareTo(SubIdea idea){
    if (this.getLikeSize() == idea.getLikeSize()){
      return this.getName().compareTo(idea.getName());
    }
    return (this.getLikeSize() < idea.getLikeSize()) ? -1 : 1;
  }
}
