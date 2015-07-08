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
public class RootIdea extends Idea{

  /**
   * 
   */
  private static final long serialVersionUID = 1L;

  private Status status;
  
  public RootIdea(String id, String name, String author){
    super(id, name,author);
    this.status = Status.BACKLOG;
  }
  
  public Status getStatus(){
    return this.status;
  }
  
  public void close() {
    this.status = Status.CLOSED;
  }
  
  public void reopen(){
    this.status = Status.PROGRESS;
  }
  
  
  @Override
  public void addSubIdea(SubIdea idea){
    super.addSubIdea(idea);
    this.status = Status.PROGRESS;
  }
  
}
