package loanSysModel.tasks;

import loanSysControllers.Controller;
import loanSysModel.managers.ProductManager;
import loanSysModel.Product;

import java.util.Random;

public class AdminTask extends Thread {
    private ProductManager productManager;
    private Controller controller;
    private boolean isRunning = true;
    private Random random = new Random();

    public AdminTask(ProductManager productManager, Controller controller) {
        this.productManager = productManager;
        this.controller = controller;
    }

    public void setIsRunning(boolean isRunning) {
        this.isRunning = isRunning;
    }

    public void run() {
        while (isRunning) {
            try {
                // Vänta 6–16 sekunder
                int waitTime = 6000 + random.nextInt(10001); // 6000–16000 ms
                Thread.sleep(waitTime);

                // Slumpa: oftast lägga till, ibland ta bort
                boolean addProduct = random.nextInt(10) < 8; // 80% chans

                if (addProduct) {
                    Product p = productManager.addNewTestProduct();
                    controller.updateView("Admin added new product: " + p.getName());
                } else {
                    if (!productManager.noProductsAvailable()) {
                        int index = random.nextInt(productManager.size());
                        Product removed = productManager.get(index);
                        productManager.remove(index);
                        controller.updateView("Admin removed product: " + removed.getName());
                    }
                }

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
