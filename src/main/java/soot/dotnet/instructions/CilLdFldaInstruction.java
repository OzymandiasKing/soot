package soot.dotnet.instructions;

/*-
 * #%L
 * Soot - a J*va Optimization Framework
 * %%
 * Copyright (C) 2022 Fraunhofer SIT
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

import soot.Body;
import soot.Scene;
import soot.SootClass;
import soot.SootFieldRef;
import soot.SootResolver;
import soot.Value;
import soot.dotnet.exceptions.NoStatementInstructionException;
import soot.dotnet.members.method.DotnetBody;
import soot.dotnet.proto.ProtoIlInstructions;
import soot.dotnet.types.DotnetTypeFactory;
import soot.jimple.Jimple;

/**
 * Load a non-static field (only non-static)
 */
public class CilLdFldaInstruction extends AbstractCilnstruction {
  public CilLdFldaInstruction(ProtoIlInstructions.IlInstructionMsg instruction, DotnetBody dotnetBody, CilBlock cilBlock) {
    super(instruction, dotnetBody, cilBlock);
  }

  @Override
  public void jimplify(Body jb) {
    throw new NoStatementInstructionException(instruction);
  }

  @Override
  public Value jimplifyExpr(Body jb) {
    // ldflda is only non-static
    SootClass declaringClass = SootResolver.v().makeClassRef(instruction.getField().getDeclaringType().getFullname());

    SootFieldRef fieldRef = Scene.v().makeFieldRef(declaringClass, instruction.getField().getName(),
        DotnetTypeFactory.toSootType(instruction.getField().getType()), false);
    CilInstruction cilExpr = CilInstructionFactory.fromInstructionMsg(instruction.getTarget(), dotnetBody, cilBlock);
    Value target = cilExpr.jimplifyExpr(jb);
    target = simplifyComplexExpression(jb, target);

    return Jimple.v().newInstanceFieldRef(target, fieldRef);
  }
}
