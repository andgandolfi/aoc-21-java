package xyz.gandolfi.aoc21.day24;

import xyz.gandolfi.aoc21.day24.instructions.*;

public abstract class Instruction {
    protected String[] params;

    public static Instruction parse(String inputLine) {
        String[] tokens = inputLine.split("\\s+");
        Instruction instruction = switch (tokens[0]) {
            case "inp" -> new InpInstruction();
            case "add" -> new AddInstruction();
            case "mul" -> new MulInstruction();
            case "div" -> new DivInstruction();
            case "mod" -> new ModInstruction();
            case "eql" -> new EqlInstruction();
            default -> throw new IllegalStateException("Unexpected value: " + tokens[0]);
        };
        String[] params = new String[tokens.length - 1];
        System.arraycopy(tokens, 1, params, 0, params.length);
        instruction.params = params;
        return instruction;
    }

    protected boolean isVarName(String param) {
        char c = param.charAt(0);
        return c >= 'w' && c <= 'z';
    }

    public abstract boolean execute(State state, InputProvider inputProvider);
}
