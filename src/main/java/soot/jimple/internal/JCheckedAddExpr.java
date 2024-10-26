package soot.jimple.internal;

/*-
 * #%L
 * Soot - a J*va Optimization Framework
 * %%
 * Copyright (C) 2015 Steven Arzt
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

import soot.Value;
import soot.jimple.ExprSwitch;
import soot.jimple.Jimple;
import soot.util.Switch;

/**
 * This add expression checks for an overflow and throws an exception 
 * in case the addition cannot be performed without an overflow.
 * In .NET CIL code, this corresponds to a Add_Ovf instruction. 
 * {@link https://learn.microsoft.com/de-de/dotnet/api/system.reflection.emit.opcodes.add_ovf}
 * 
 * Note that since this class inherits from {@link JAddExpr}, most analysis (e.g. data flow) can treat this like a 
 * normal add expression without further changes.
 */
public class JCheckedAddExpr extends JAddExpr implements ICheckedExpr {

  private static final long serialVersionUID = 1L;

  public JCheckedAddExpr(Value op1, Value op2) {
    super(op1, op2);
  }

  @Override
  public void apply(Switch sw) {
    ((ExprSwitch) sw).caseCheckedAddExpr(this);
  }

  @Override
  public Object clone() {
    return new JCheckedAddExpr(Jimple.cloneIfNecessary(getOp1()), Jimple.cloneIfNecessary(getOp2()));
  }
}
