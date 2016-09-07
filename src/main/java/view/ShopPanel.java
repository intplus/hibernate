package view;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;

import model.*;
import service.Service;


public class ShopPanel extends JPanel implements Runnable{

   private ShopTableModel stm;

    private Order o;
    private Service s;
    private Map<Product, Integer> order;
    private double totalSumm;
    private JComboBox statusBox;
    private String strName;
    private JComboBox productsBox;
    private TableRowSorter<ShopTableModel> sorter;
    private JTextField filterText;
    private JLabel warningsLabel;

    public ShopPanel(Map order) throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException {
        this.order = order;
        stm = new ShopTableModel();

        setLayout(new GridBagLayout());

        (new Thread(this)).start();
    }

    public void init() throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException {

        JLabel name = new JLabel("Name: ");
        final JTextField textName = new JTextField(10);
        textName.setText(strName);

        final JLabel surname = new JLabel("Surname: ");
        final JTextField textSurname = new JTextField(10);


        JLabel phone = new JLabel("Phone: ");
        final JTextField textPhone = new JTextField(10);

        JLabel delivery = new JLabel("Delivery address: ");
        final JTextField textDelivery = new JTextField(20);

        add(name, new GridBagConstraints(0, 0, 1, 1, 1, 1,
                GridBagConstraints.NORTH, GridBagConstraints.NONE,
                new Insets(2, 2, 2, 2), 0, 0));

        add(textName, new GridBagConstraints(1, 0, 1, 1, 1, 1,
                GridBagConstraints.NORTH, GridBagConstraints.NONE,
                new Insets(2, 2, 2, 2), 0, 0));

        add(surname, new GridBagConstraints(2, 0, 1, 1, 1, 1,
                GridBagConstraints.NORTH, GridBagConstraints.NONE,
                new Insets(2, 2, 2, 2), 0, 0));

        add(textSurname, new GridBagConstraints(3, 0, 1, 1, 1, 1,
                GridBagConstraints.NORTH, GridBagConstraints.NONE,
                new Insets(2, 2, 2, 2), 0, 0));

        add(phone, new GridBagConstraints(4, 0, 1, 1, 1, 1,
                GridBagConstraints.NORTH, GridBagConstraints.NONE,
                new Insets(2, 2, 2, 2), 0, 0));

        add(textPhone, new GridBagConstraints(5, 0, 1, 1, 1, 1,
                GridBagConstraints.NORTHWEST, GridBagConstraints.NONE,
                new Insets(2, 2, 2, 2), 0, 0));

        add(delivery, new GridBagConstraints(6, 0, 1, 1, 1, 1,
                GridBagConstraints.NORTH, GridBagConstraints.NONE,
                new Insets(2, 2, 2, 2), 0, 0));

        add(textDelivery, new GridBagConstraints(7, 0, 1, 1, 1, 1,
                GridBagConstraints.NORTH, GridBagConstraints.NONE,
                new Insets(2, 2, 2, 2), 0, 0));
//----------------------------------------------------------------------------------------------------------------------         

        final DefaultListModel listProductsModel = new DefaultListModel();

        JLabel lPoducts = new JLabel("Goods: ");

        List<Product> products = s.getProducts(1);

        for (int i = 0; i < products.size(); ++i) {
            listProductsModel.addElement(products.get(i).getTitle() + "   " + products.get(i).getPrice() + " $");
        }

        final JList productsList = new JList(listProductsModel);
        JScrollPane productScrollPaneList = new JScrollPane(productsList);
        productScrollPaneList.setPreferredSize(new Dimension(200, 200));

        add(lPoducts, new GridBagConstraints(0, 2, 1, 1, 1, 1,
                GridBagConstraints.NORTH, GridBagConstraints.NONE,
                new Insets(2, 2, 2, 2), 0, 0));

        add(productScrollPaneList, new GridBagConstraints(1, 2, 1, 1, 1, 1,
                GridBagConstraints.NORTH, GridBagConstraints.NONE,
                new Insets(2, 2, 2, 2), 0, 0));
//----------------------------------------------------------------------------------------------------------------------         

        JLabel orderLabel = new JLabel("Order: ");

        final DefaultListModel listOrderModel = new DefaultListModel();

            Set<Map.Entry<Product, Integer>> set = order.entrySet();
            for (Map.Entry<Product, Integer> me : set) {
                listOrderModel.addElement(me.getKey().getTitle() + "   " + me.getValue() + " pcs");
                totalSumm += me.getKey().getPrice().doubleValue() * me.getValue();
            }


        final JList orderList = new JList(listOrderModel);
        JScrollPane orderListPane = new JScrollPane(orderList);
        orderListPane.setPreferredSize(new Dimension(300, 200));

        add(orderLabel, new GridBagConstraints(4, 2, 1, 1, 1, 1,
                GridBagConstraints.NORTH, GridBagConstraints.NONE,
                new Insets(2, 2, 2, 2), 0, 0));

        add(orderListPane, new GridBagConstraints(5, 2, 1, 1, 1, 1,
                GridBagConstraints.NORTH, GridBagConstraints.NONE,
                new Insets(2, 2, 2, 2), 0, 0));
//----------------------------------------------------------------------------------------------------------------------         

        JLabel lCount = new JLabel("Count: ");
        NumberFormat nf = NumberFormat.getNumberInstance();
        final JFormattedTextField tfCount = new JFormattedTextField(nf);
        tfCount.setColumns(2);
        tfCount.setValue(1);

        add(lCount, new GridBagConstraints(2, 2, 1, 1, 1, 1,
                GridBagConstraints.NORTH, GridBagConstraints.NONE,
                new Insets(2, 2, 2, 2), 0, 0));
        add(tfCount, new GridBagConstraints(3, 2, 1, 1, 1, 1,
                GridBagConstraints.NORTHWEST, GridBagConstraints.NONE,
                new Insets(2, 2, 2, 2), 0, 0));

        JLabel lSumm = new JLabel("Total Summ: ");
        NumberFormat nf2 = NumberFormat.getCurrencyInstance(Locale.US);

        final JFormattedTextField tfSumm = new JFormattedTextField(nf2);
        tfSumm.setColumns(6);
        tfSumm.setValue(totalSumm);
        tfSumm.setEditable(false);

        add(lSumm, new GridBagConstraints(6, 2, 1, 1, 1, 1,
                GridBagConstraints.NORTH, GridBagConstraints.NONE,
                new Insets(2, 2, 2, 2), 0, 0));
        add(tfSumm, new GridBagConstraints(7, 2, 1, 1, 1, 1,
                GridBagConstraints.NORTHWEST, GridBagConstraints.NONE,
                new Insets(2, 2, 2, 2), 0, 0));
//----------------------------------------------------------------------------------------------------------------------         

        JButton btnAdd = new JButton("ADD");
        add(btnAdd, new GridBagConstraints(1, 3, 1, 1, 1, 1,
                GridBagConstraints.NORTH, GridBagConstraints.NONE,
                new Insets(2, 2, 2, 2), 0, 0));

//----------------------------------------------------------------------------------------------------------------------         

        sorter = new TableRowSorter<ShopTableModel>(stm);
        final JTable table = new JTable(stm);
        table.setRowSorter(sorter);
        JScrollPane scrollTable = new JScrollPane(table);
        scrollTable.setPreferredSize(new Dimension(400, 400));

        table.getColumnModel().getColumn(0).setMaxWidth(60);
        table.getColumnModel().getColumn(0).setResizable(false);

        JLabel l1 = new JLabel("Filter:", SwingConstants.TRAILING);
        add(l1, new GridBagConstraints(1, 4, 1, 1, 1, 1,
                GridBagConstraints.SOUTHWEST, GridBagConstraints.NONE,
                new Insets(2, 2, 2, 2), 0, 0));
        filterText = new JTextField(4);
        //Whenever filterText changes, invoke newFilter.
        filterText.getDocument().addDocumentListener(
                new DocumentListener() {
                    public void changedUpdate(DocumentEvent e) {
                        newFilter();
                    }
                    public void insertUpdate(DocumentEvent e) {
                        newFilter();
                    }
                    public void removeUpdate(DocumentEvent e) {
                        newFilter();
                    }
                });
        l1.setLabelFor(filterText);
        add(filterText, new GridBagConstraints(1, 4, 1, 1, 1, 1,
                GridBagConstraints.SOUTH, GridBagConstraints.NONE,
                new Insets(2, 2, 2, 2), 0, 0));


        for (int i = 0; i < 6; ++i) {
            table.getColumnModel().getColumn(i).setCellRenderer(new Renderer());
        }

        add(scrollTable, new GridBagConstraints(1, 5, 6, 1, 1, 1,
                GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL,
                new Insets(2, 2, 2, 2),0, 0));

//----------------------------------------------------------------------------------------------------------------------         
        JButton updateBtn = new JButton("Update");
        Font font = new Font("Verdana", 10, 20);
        updateBtn.setFont(font);
        add(updateBtn, new GridBagConstraints(7, 5, 1, 1, 1, 1,
                GridBagConstraints.NORTHWEST, GridBagConstraints.NONE,
                new Insets(2, 2, 2, 2), 0, 0));
        updateBtn.setVisible(true);

        updateBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (textName.getText().equals("") & textSurname.getText().equals("")) {
                    return;
                }
                if (totalSumm == 0) {
                    return;
                }
                s.createCustomer(textName.getText(), textSurname.getText(), textPhone.getText());
                ArrayList products = new ArrayList();
                ArrayList quantitys = new ArrayList();
                ArrayList goods_id = new ArrayList();

                Set<Map.Entry<Product, Integer>> set = order.entrySet();
                for(Map.Entry<Product, Integer> me : set) {
                    goods_id.add(me.getKey().getId());
                    products.add(me.getKey());
                    quantitys.add(me.getValue());
                }
//                s.createOrder()
//                o = new Order();
//                o.setCount();
//                o.getPrice();
//                o.setProduct();
//                o.setStatus();
//                o.setCustomer(customer);
//                purchase = new Purchase(customer, goods_id, products, quantitys, statusBox.getSelectedIndex() + 1);
//
//                int order_id = Integer.valueOf((String)stm.getValueAt(table.convertRowIndexToModel(table.getSelectedRow()), 0));
//
//                s.updateService(purchase, order_id);

            }
        });


//----------------------------------------------------------------------------------------------------------------------         

        JButton newOrderBtn = new JButton("New Order");
        newOrderBtn.setFont(font);
        add(newOrderBtn, new GridBagConstraints(5, 1, 1, 1, 1, 1,
                GridBagConstraints.SOUTHWEST, GridBagConstraints.NONE,
                new Insets(2, 2, 2, 2), 0, 0));
        newOrderBtn.setVisible(true);

        newOrderBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                order.clear();
                textName.setText("");
                textSurname.setText("");
                textDelivery.setText("");
                textPhone.setText("");
                statusBox.setSelectedIndex(0);
                listOrderModel.clear();
                tfSumm.setText("0.0$");
                tfCount.setText("1");


            }
        });
//----------------------------------------------------------------------------------------------------------------------         

        table.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
            }

            @Override
            public void keyReleased(KeyEvent e) {

                int order_id = Integer.valueOf((String) stm.getValueAt(table.getSelectedRow(), 0));


                String textname = (String) stm.getValueAt(table.convertRowIndexToModel(table.getSelectedRow()), 2);
                String textsurname = (String) stm.getValueAt(table.convertRowIndexToModel(table.getSelectedRow()), 3);
                String textphone = s.getPhoneByOrder(order_id);


                textName.setText(textname.trim());
                textSurname.setText(textsurname.trim());
                textPhone.setText(textphone);

                statusBox.setSelectedIndex((int) s.statusOrder(order_id) - 1);

                List<OrdersItems> list = s.getProductsForOrder(order_id);
                listOrderModel.clear();
                order.clear();

                for (int i = 0; i < list.size(); ++i) {
                    listOrderModel.addElement(list.get(i).getProduct().getTitle() + "   " + list.get(i).getProduct().getPrice() + "$   " + list.get(i).getQuantity() + " pcs");
                    order.put(list.get(i).getProduct(), list.get(i).getQuantity());
                }
                totalSumm = 0;


                Set<Map.Entry<Product, Integer>> set = order.entrySet();
                for(Map.Entry<Product, Integer> me : set) {
                    totalSumm += me.getKey().getPrice().doubleValue() * me.getValue();
                }

                tfSumm.setText(String.valueOf(totalSumm));


                /*
                Product p = s.getProducts(productsBox.getSelectedIndex()+1).get(id_goods -1);
                int count = Integer.parseInt(tfCount.getText());

                order.put(p, Integer.valueOf(count));
                strName = textName.getText();

                listOrderModel.addElement(p.getTitle() + "   " + p.getPrice() + "$   " + count + " pcs");
                totalSumm = 0;

                Set<Map.Entry<Product, Integer>> set = order.entrySet();
                for(Map.Entry<Product, Integer> me : set) {
                    totalSumm += me.getKey().getPrice().doubleValue() * me.getValue();
                }

                tfSumm.setText(String.valueOf(totalSumm));
                 */



              /*  String textname = (String) stm.getValueAt(table.convertRowIndexToModel(table.getSelectedRow()), 2);
                String textsurname = (String) stm.getValueAt(table.convertRowIndexToModel(table.getSelectedRow()), 3);
                System.out.println(table.convertRowIndexToModel(table.getSelectedRow()));
                System.out.println(stm.getValueAt(table.getSelectedRow(), 0));

                textName.setText(textname.trim());
                textSurname.setText(textsurname.trim());

                String [][] items = new String[5][];

                items = s.getOrder(
                        Integer.valueOf((String)
                                stm.getValueAt(
                                        table.convertRowIndexToModel
                                                (table.getSelectedRow()),
                                        0)));


                listOrderModel.clear();
                order.clear();

                Product [] productsArrayUpdate = new Product[items.length];

                for (int i = 0; i < items[0].length; ++i) {
                    productsArrayUpdate[i] = new Product();
                    productsArrayUpdate[i].
//                    productsArrayUpdate[i]  =
//                            new Product(Integer.valueOf(items[0][i]),
//                                    Integer.valueOf(items[1][i]),
//                                    items[2][i],
//                                    Double.parseDouble(items[4][i].trim()));
//                    listOrderModel.addElement(productsArrayUpdate[i].getTitle().trim() + "   " + productsArrayUpdate[i].getPrice() + "$   " + items[3][i].trim() + " pcs");
//                    order.put(productsArrayUpdate[i], Integer.valueOf(items[3][i].trim()));

                }
                String str = (String)stm.getValueAt(table.convertRowIndexToModel(table.getSelectedRow()), 5);

                statusBox.setSelectedIndex(s.returnStatusBox(str));

                totalSumm = 0;
                Set<Map.Entry<Product, Integer>> set = order.entrySet();
                for(Map.Entry<Product, Integer> me : set) {
                    totalSumm += me.getKey().getPrice().doubleValue() * me.getValue();
                }
                tfSumm.setText(Double.toString(totalSumm));
                */

            }
        });

//----------------------------------------------------------------------------------------------------------------------         

        btnAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int id_goods = productsList.getAnchorSelectionIndex() + 1;

                Product p = s.getProducts(productsBox.getSelectedIndex()+1).get(id_goods -1);
                int count = Integer.parseInt(tfCount.getText());

                order.put(p, Integer.valueOf(count));
                strName = textName.getText();

                listOrderModel.addElement(p.getTitle() + "   " + p.getPrice() + "$   " + count + " pcs");
                totalSumm = 0;

                Set<Map.Entry<Product, Integer>> set = order.entrySet();
                for(Map.Entry<Product, Integer> me : set) {
                    totalSumm += me.getKey().getPrice().doubleValue() * me.getValue();
                }

                tfSumm.setText(String.valueOf(totalSumm));

            }
        });

        JButton btnBuy = new JButton("BUY");
        add(btnBuy, new GridBagConstraints(5, 3, 1, 1, 1, 1,
                GridBagConstraints.NORTHEAST, GridBagConstraints.NONE,
                new Insets(2, 2, 2, 2), 0, 0));

        warningsLabel = new JLabel("Buying without a name is not possible!");
        warningsLabel.setForeground(Color.MAGENTA);
        Font font2 = new Font("Verdana", 20, 15);
        warningsLabel.setFont(font2);
        add(warningsLabel, new GridBagConstraints(6, 3, 3, 1, 1, 1,
                GridBagConstraints.NORTHWEST, GridBagConstraints.NONE,
                new Insets(2, 2, 2, 2), 0, 0));
        warningsLabel.setVisible(false);

        btnBuy.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                warningsLabel.setVisible(false);

                if (textName.getText().equals("") & textSurname.getText().equals("")) {
                    warningsLabel.setVisible(true);
                    return;
                }
                if (totalSumm == 0) {
                    return;
                }

                Customer customer = new Customer();
                customer.setName(textName.getText());
                customer.setLastname(textSurname.getText());
                customer.setPhone(textPhone.getText());
//                s.createCustomer(textName.getText(), textSurname.getText(), textPhone.getText());

                List<Product> products = new ArrayList<Product>();
                List<Integer> quantitys = new ArrayList<Integer>();
                List<Long> goods_id = new ArrayList<Long>();


                Set<Map.Entry<Product, Integer>> set = order.entrySet();
                for(Map.Entry<Product, Integer> me : set) {

                    goods_id.add(me.getKey().getId());
                    products.add(me.getKey());
                    quantitys.add(me.getValue());

                }
                int statusId = statusBox.getSelectedIndex() + 1;
                System.err.println("statusId = " + statusId);

                s.makeOrder(customer, products, quantitys, goods_id, new BigDecimal(totalSumm), statusId);

            }
        });
//----------------------------------------------------------------------------------------------------------------------         
        JButton removeOrderBtn = new JButton("Remove");
        removeOrderBtn.setFocusable(false);

        removeOrderBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                totalSumm = 0;

                Set<Map.Entry<Product, Integer>> set = order.entrySet();
                Object o = new Object();

                for(Map.Entry<Product, Integer> me : set) {
                    if (((String) orderList.getSelectedValue()).contains(me.getKey().getTitle())) {
                        o = me.getKey();
                        totalSumm -= me.getKey().getPrice().doubleValue() * me.getValue();
                    }

                    totalSumm += me.getKey().getPrice().doubleValue() * me.getValue();
                }
                order.remove(o);

                listOrderModel.removeElementAt(orderList.getSelectedIndex());

                tfSumm.setText(String.valueOf(totalSumm));

            }
        });

        add(removeOrderBtn, new GridBagConstraints(5, 3, 1, 1, 1, 1,
                GridBagConstraints.NORTHWEST, GridBagConstraints.NONE,
                new Insets(2, 2, 2, 2), 0, 0));



//----------------------------------------------------------------------------------------------------------------------         
        JLabel lProducts = new JLabel("Products: ");
        productsBox = new JComboBox(s.productsItemsBox());


        add(lProducts, new GridBagConstraints(0, 1, 1, 1, 1, 1,
                GridBagConstraints.CENTER, GridBagConstraints.NONE,
                new Insets(2, 2, 2, 2), 0, 0));

        add(productsBox, new GridBagConstraints(1, 1, 1, 1, 1, 1,
                GridBagConstraints.CENTER, GridBagConstraints.NONE,
                new Insets(2, 2, 2, 2), 0, 0));

        productsBox.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {

                listProductsModel.clear();
                List<Product> products = s.getProducts(productsBox.getSelectedIndex() + 1);

                for (int i = 0; i < products.size(); ++i) {
                    listProductsModel.addElement(products.get(i).getTitle() + "   " + products.get(i).getPrice() + " $");
                }
            }
        });

        JLabel statusLabel = new JLabel("Status: ");

        statusBox = new JComboBox(s.statusItemsBox());

        add(statusLabel, new GridBagConstraints(4, 4, 1, 1, 1, 1,
                GridBagConstraints.CENTER, GridBagConstraints.NONE,
                new Insets(2, 2, 2, 2), 0, 0));

        add(statusBox, new GridBagConstraints(5, 4, 1, 1, 1, 1,
                GridBagConstraints.WEST, GridBagConstraints.NONE,
                new Insets(2, 2, 2, 2), 0, 0));


        setBackground(Color.LIGHT_GRAY);

    }

    @Override
    public void run() {
        s = new Service();


        while(true) {
            try {
                String[][] data = s.sale();
                stm.dateNull();

                for (int i = data.length - 1; i > 0; --i) {
                    stm.addDate(data[i]);
                }
                repaint();
                Thread.sleep(3000);

            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
    private void newFilter() {
        RowFilter<ShopTableModel, Object> rf = null;
        //If current expression doesn't parse, don't update.
        try {
            rf = RowFilter.regexFilter(filterText.getText(), 0);
        } catch (java.util.regex.PatternSyntaxException e) {
            return;
        }
        sorter.setRowFilter(rf);
    }
    private void sleep(int timeout) {
        try {
            Thread.sleep(timeout);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}

