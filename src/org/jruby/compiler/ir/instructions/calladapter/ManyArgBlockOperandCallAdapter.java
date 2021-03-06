package org.jruby.compiler.ir.instructions.calladapter;

import org.jruby.compiler.ir.operands.Operand;
import org.jruby.runtime.Block;
import org.jruby.runtime.CallSite;
import org.jruby.runtime.DynamicScope;
import org.jruby.runtime.ThreadContext;
import org.jruby.runtime.builtin.IRubyObject;

/**
 *
 */
public class ManyArgBlockOperandCallAdapter extends ClosureCallAdapter {
    private Operand[] args;
    
    public ManyArgBlockOperandCallAdapter(CallSite callSite, Operand[] args, Operand closure) {
        super(callSite, closure);
        
        this.args = args;
    }

    @Override
    public Object call(ThreadContext context, IRubyObject self, IRubyObject receiver, DynamicScope currDynScope, Object[] temp) {
        IRubyObject[] values = prepareArguments(context, self, args, currDynScope, temp);
        Block block = prepareBlock(context, self, currDynScope, temp);
        return callSite.call(context, self, receiver, values, block);
    }

    protected IRubyObject[] prepareArguments(ThreadContext context, IRubyObject self, Operand[] args, DynamicScope currDynScope, Object[] temp) {
        IRubyObject[] newArgs = new IRubyObject[args.length];

        for (int i = 0; i < args.length; i++) {
            newArgs[i] = (IRubyObject) args[i].retrieve(context, self, currDynScope, temp);
        }

        return newArgs;
    }
}
