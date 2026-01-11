package loanSysModel.tasks;

import loanSysControllers.Controller;
import loanSysModel.*;
import loanSysModel.managers.*;

import java.util.Random;

public class ReturnTask extends Thread {
    private ProductManager productManager;
    private LoanItemManager loanItemManager;
    private Controller controller;
    private boolean isRunning = true;
    private Random random = new Random();

    public ReturnTask(ProductManager productManager, LoanItemManager loanItemManager, Controller controller) {
        this.productManager = productManager;
        this.loanItemManager = loanItemManager;
        this.controller = controller;
    }

    public void setIsRunning(boolean isRunning) {
        this.isRunning = isRunning;
    }

    public void run() {
        while (isRunning) {
            try {
                // Vänta 3–15 sekunder
                int waitTime = 3000 + random.nextInt(12001); // 3000–15000 ms
                Thread.sleep(waitTime);

                // Om inga lån finns, hoppa över
                if (loanItemManager.isEmpty()) {
                    continue;
                }

                // Slumpa ett lånat objekt
                int index = random.nextInt(loanItemManager.size());
                LoanItem item = loanItemManager.get(index);
                if (item == null){
                    continue;
                }

                productManager.add(item.getProduct());

                loanItemManager.remove(item);

                // Logga i GUI
                controller.updateView("Returned " + item.getProduct().getName() + " from " + item.getMember().getName());

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
