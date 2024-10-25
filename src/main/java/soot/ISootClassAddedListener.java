package soot;

/*-
 * #%L
 * Soot - a J*va Optimization Framework
 * %%
 * Copyright (C) 2002 Ondrej Lhotak
 * %%
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation, either version 2.1 of the
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Lesser Public License for more details.
 * 
 * You should have received a copy of the GNU General Lesser Public
 * License along with this program.  If not, see
 * <http://www.gnu.org/licenses/lgpl-2.1.html>.
 * #L%
 */

/**
 * Interface to notify client code that a new class has been added to the scene
 * 
 * @author Steven Arzt
 *
 */
public interface ISootClassAddedListener {

  /**
   * Callback that is invoked when a SootClass has been added to the scene
   * 
   * @param sc
   *          The SootClass that as been added
   */
  public void onSootClassAdded(SootClass sc);

}
