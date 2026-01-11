package loanSysModel.tasks;

import loanSysControllers.Controller;
import loanSysModel.*;
import loanSysModel.managers.*;

import java.util.Random;

public class LoanTask extends Thread {
    private ProductManager productManager;
    private MemberManager memberManager;
    private LoanItemManager loanItemManager;
    private Controller controller;
    private boolean isRunning = true;
    private Random random = new Random();

    public LoanTask(ProductManager productManager, MemberManager memberManager,
                    LoanItemManager loanItemManager, Controller controller) {
        this.productManager = productManager;
        this.memberManager = memberManager;
        this.loanItemManager = loanItemManager;
        this.controller = controller;
    }

    public void setIsRunning(boolean isRunning) {
        this.isRunning = isRunning;
    }

    public void run() {
        while (isRunning) {
            try {
                // Vänta 2–6 sekunder
                int waitTime = 2000 + random.nextInt(4001); // 2000–6000 ms
                Thread.sleep(waitTime);

                // Hoppa över om inga produkter finns
                if (productManager.noProductsAvailable()) {
                    continue;
                }

                // Slumpa produkt och medlem
                int prodIndex = random.nextInt(productManager.size());
                int memberIndex = random.nextInt(memberManager.size());

                Product product = productManager.get(prodIndex);
                Member member = memberManager.get(memberIndex);

                if (product == null || member == null)
                    continue;

                // Skapa LoanItem och uppdatera listor
                LoanItem item = new LoanItem(product, member);
                loanItemManager.add(item);
                productManager.remove(prodIndex);

                // Skicka info till GUI
                controller.updateView("Loaned " + product.getName() + " to " + member.getName());

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
