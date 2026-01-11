package loanSysModel.managers;

import loanSysModel.LoanItem;

import java.util.ArrayList;
import java.util.List;

// Hanterar alla objekt som är utlånade just nu.
public class LoanItemManager {
    private List<LoanItem> loanItems = new ArrayList<>();

    // Lägger till ett nytt loanItem
    public void add(LoanItem item) {
        loanItems.add(item);
    }

    // Tar bort ett loanItem – används vid återlämning
    public void remove(LoanItem item) {
        loanItems.remove(item);
    }

    // Hämtar ett loanItem via index (för att kunna slumpa fram)
    public LoanItem get(int index) {
        if (index >= 0 && index < loanItems.size()) {
            return loanItems.get(index);
        }
        return null;
    }

    // Antal utlånade produkter just nu
    public int size() {
        return loanItems.size();
    }

    // Returnerar alla utlånade produkter som text – för GUI
    public String[] getLoanItemInfoStrings() {
        if (loanItems.isEmpty()) {
            return new String[]{"No items currently on loan."};
        }

        String[] infoStrings = new String[loanItems.size() + 2];
        infoStrings[0] = "Number of items on loan: " + loanItems.size();
        infoStrings[1] = "";

        int i = 2;
        for (LoanItem item : loanItems) {
            infoStrings[i++] = item.toString();
        }
        return infoStrings;
    }

    public boolean isEmpty() {
        return loanItems.isEmpty();
    }
}
