package xyz.gandolfi.aoc21.day24.instructions;

import xyz.gandolfi.aoc21.day24.InputProvider;
import xyz.gandolfi.aoc21.day24.Instruction;
import xyz.gandolfi.aoc21.day24.State;

public class DivInstruction extends Instruction {
    @Override
    public boolean execute(State state, InputProvider inputProvider) {
        int secondParam = isVarName(params[1]) ? state.getVar(params[1]) : Integer.parseInt(params[1]);

        state.setVar(params[0], state.getVar(params[0]) / secondParam);

        return true;
    }
}
