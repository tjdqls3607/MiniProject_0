package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StockManagementPanel extends JPanel {
    private JTextField modelIdField, quantityField;
    private JButton addStockButton, removeStockButton;
    private JTextArea resultArea;

    public StockManagementPanel() {
        setLayout(new BorderLayout());

        // 입력 필드와 버튼을 담을 패널
        JPanel inputPanel = new JPanel(new GridLayout(3, 2));
        inputPanel.add(new JLabel("기종 ID:"));
        modelIdField = new JTextField();
        inputPanel.add(modelIdField);
        inputPanel.add(new JLabel("수량:"));
        quantityField = new JTextField();
        inputPanel.add(quantityField);

        addStockButton = new JButton("입고");
        removeStockButton = new JButton("출고");

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(addStockButton);
        buttonPanel.add(removeStockButton);

        inputPanel.add(buttonPanel);
        add(inputPanel, BorderLayout.NORTH);

        // 결과를 출력할 텍스트 영역
        resultArea = new JTextArea();
        resultArea.setEditable(false);
        add(new JScrollPane(resultArea), BorderLayout.CENTER);

        // 버튼 이벤트 처리
        addStockButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 입고 로직
                int modelId = Integer.parseInt(modelIdField.getText());
                int quantity = Integer.parseInt(quantityField.getText());
                // TODO: 데이터베이스에 입고 처리
                resultArea.append("입고: 기종 ID=" + modelId + ", 수량=" + quantity + "\n");
            }
        });

        removeStockButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 출고 로직
                int modelId = Integer.parseInt(modelIdField.getText());
                int quantity = Integer.parseInt(quantityField.getText());
                // TODO: 데이터베이스에 출고 처리
                resultArea.append("출고: 기종 ID=" + modelId + ", 수량=" + quantity + "\n");
            }
        });
    }
}
