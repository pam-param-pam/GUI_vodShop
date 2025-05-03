package dev.pamparampam;

import dev.pamparampam.Films.ClientProgram;
import dev.pamparampam.utils.ProgramList;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class LastTransaction extends ProgramList {
    private final Map<ClientProgram, Double> paidPrices = new HashMap<>();

    @Override
    public void addProgram(ClientProgram program) {
        for (int i = 0; i < getPrograms().size(); i++) {
            if (programs.get(i).equals(program)) {
                programs.set(i, program);
                updatePaidPrice(program);
                return;
            }
        }
        super.addProgram(program);
        updatePaidPrice(program);
    }

    @Override
    public void removeProgram(ClientProgram program) {
        super.removeProgram(program);
        paidPrices.remove(program);
    }

    @Override
    public void setPrograms(ArrayList<ClientProgram> programs) {
        clearPrograms();
        for (ClientProgram program : programs) {
            addProgram(program);
        }
    }

    @Override
    public void clearPrograms() {
        super.clearPrograms();
        paidPrices.clear();
    }

    @Override
    public String toString() {
        return "LastTransaction{" + super.toString() + "}";
    }

    public Double getPaidPrice(ClientProgram program) {
        for (Map.Entry<ClientProgram, Double> entry : paidPrices.entrySet()) {
            ClientProgram cp = entry.getKey();
            if (cp.getTitle().equals(program.getTitle()) && cp.getGenre() == program.getGenre() && cp.getDevices() >= program.getDevices()) {
                return entry.getValue() * program.getDevices();
            }
        }
        throw new RuntimeException("Unable to find price for program: " + program);
    }

    private void updatePaidPrice(ClientProgram program) {
        double pricePaid = program.getSingularPrice();
        paidPrices.put(program, pricePaid);
    }
}
