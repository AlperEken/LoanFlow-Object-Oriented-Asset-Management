package loanSysModel;

import loanSysControllers.Controller;
import loanSysModel.managers.*;
import loanSysModel.tasks.*;

public class LoanSysManager {
    private ProductManager productManager;
    private MemberManager memberManager;
    private LoanItemManager loanItemManager;
    private LoanTask loanTask;
    private ReturnTask returnTask;
    private AdminTask adminTask;
    private UpdateGUI updateGUI;
    private Controller controller;

    public LoanSysManager(Controller controller) {
        this.controller = controller;

        productManager = new ProductManager();
        memberManager = new MemberManager();
        loanItemManager = new LoanItemManager();

        // Skapar testdata direkt
        productManager.addTestProducts();
        memberManager.addTestMembers();
    }

    // Skapar nya trådar varje gång Start-knappen trycks
    public void start() {
        loanTask = new LoanTask(productManager, memberManager, loanItemManager, controller);
        returnTask = new ReturnTask(productManager, loanItemManager, controller);
        adminTask = new AdminTask(productManager, controller);
        updateGUI = new UpdateGUI(controller);

        loanTask.setIsRunning(true);
        returnTask.setIsRunning(true);
        adminTask.setIsRunning(true);
        updateGUI.setIsRunning(true);

        loanTask.start();
        returnTask.start();
        adminTask.start();
        updateGUI.start();
    }

    public void stop() {
        loanTask.setIsRunning(false);
        returnTask.setIsRunning(false);
        adminTask.setIsRunning(false);
        updateGUI.setIsRunning(false);
    }

    public ProductManager getProductManager() {
        return productManager;
    }

    public LoanItemManager getLoanItemManager() {
        return loanItemManager;
    }
}
