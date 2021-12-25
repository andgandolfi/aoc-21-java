package xyz.gandolfi.aoc21.day24.instructions;

import xyz.gandolfi.aoc21.day24.InputProvider;
import xyz.gandolfi.aoc21.day24.Instruction;
import xyz.gandolfi.aoc21.day24.State;

public class InpInstruction extends Instruction {
    @Override
    public boolean execute(State state, InputProvider inputProvider) {
        Character inputChar = inputProvider.getNextInput();
        if (inputChar == null)
            return false;

        int inputDigit = inputChar - '0';

        state.setVar(params[0], inputDigit);

        return true;
    }
}
