package com.company.GUI.Customer;

import com.company.Account.Account;
import com.company.Account.AccountType;
import com.company.Account.StockAccount;
import com.company.Currency.Currency;
import com.company.Exceptions.AccountNotExistException;
import com.company.Factories.AccountFactory;
import com.company.GUI.MyPage;
import com.company.GUI.PageManager;
import com.company.Persons.Customer;
import com.company.Stock.StockMarket;
import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;

import javax.naming.spi.ObjectFactoryBuilder;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Class of page for viewing my stocks
 */
public class PageServiceMyStocks implements MyPage {
    private JPanel rootPanel;
    private JLabel lbTitle;
    private JScrollPane sp;
    private JTable tbStocks;
    private JButton btCancel;

    /**
     * Constructor
     * @param customer
     * @param currency
     * @param stockMarket
     */
    public PageServiceMyStocks(Customer customer, Currency currency, StockMarket stockMarket) {
        btCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                PageManager.backToOldPage();
            }
        });
        tbStocks.setModel(
                new DefaultTableModel(
                        null,
                        new String[]{"Stock", "Shares"}
                )
        );

        int count = 1;
        List<Account> stockAccounts = new LinkedList<>();
        Map<String, Integer> sharesHolding = new HashMap<>();
        try {
            stockAccounts = customer.getAccountsByType(AccountType.STOCK);
        } catch (AccountNotExistException e) {
            // DO NOTHING
        }
        for (Account stockAccount : stockAccounts) {
            sharesHolding = ((StockAccount) stockAccount).getSharesHolding();
        }
        for (Map.Entry<String, Integer> entry : sharesHolding.entrySet()) {
            DefaultTableModel model = (DefaultTableModel) tbStocks.getModel();
            model.addRow(new Object[]{entry.getKey(), entry.getValue()});
            System.out.println("<" + count + "> " + entry.getKey() + "         " + entry.getValue());
        }
    }


    {
// GUI initializer generated by IntelliJ IDEA GUI Designer
// >>> IMPORTANT!! <<<
// DO NOT EDIT OR ADD ANY CODE HERE!
        $$$setupUI$$$();
    }

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        rootPanel = new JPanel();
        rootPanel.setLayout(new GridLayoutManager(3, 1, new Insets(0, 0, 0, 0), -1, -1));
        lbTitle = new JLabel();
        lbTitle.setText("My Stocks");
        rootPanel.add(lbTitle, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        sp = new JScrollPane();
        rootPanel.add(sp, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        tbStocks = new JTable();
        sp.setViewportView(tbStocks);
        btCancel = new JButton();
        btCancel.setText("Cancel");
        rootPanel.add(btCancel, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return rootPanel;
    }


    /**
     * Method for getting root panel
     * @return
     */
    @Override
    public JPanel getRootPanel() {
        return rootPanel;
    }
}
