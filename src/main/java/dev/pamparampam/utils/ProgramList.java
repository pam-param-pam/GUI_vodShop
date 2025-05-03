package dev.pamparampam.utils;

import dev.pamparampam.Films.ClientProgram;

import java.util.ArrayList;
import java.util.List;

public abstract class ProgramList {
    protected ArrayList<ClientProgram> programs = new ArrayList<>();

    public void addProgram(ClientProgram program) {
        // check if the program already exists
        for (int i = 0; i < programs.size(); i++) {
            if (programs.get(i).equals(program)) {
                //replace it
                programs.set(i, program);
                return;
            }
        }
        // if not, add it
        programs.add(program);
    }

    public void removeProgram(ClientProgram program) {
        programs.remove(program);
    }

    public ArrayList<ClientProgram> getPrograms() {
        return programs;
    }

    public void setPrograms(ArrayList<ClientProgram> programs) {
        this.programs = programs;
    }

    public void clearPrograms() {
        this.programs.clear();
    }

    @Override
    public String toString() {
        return "[" + "programs=" + programs + ']';
    }

    public void removePrograms(List<ClientProgram> programsToRemove) {
        for (ClientProgram program : programsToRemove) {
            removeProgram(program);
        }
    }
}
