package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ModelManagementPanel extends JPanel {
    private JTextField idField, nameField, manufacturerField, priceField;
    private JButton addButton, updateButton, deleteButton, searchButton;
    private JTextArea resultArea;

    public ModelManagementPanel() {
        setLayout(new BorderLayout());

        // 입력 필드와 버튼을 담을 패널
        JPanel inputPanel = new JPanel(new GridLayout(5, 2));
        inputPanel.add(new JLabel("기종 ID:"));
        idField = new JTextField();
        inputPanel.add(idField);
        inputPanel.add(new JLabel("기종명:"));
        nameField = new JTextField();
        inputPanel.add(nameField);
        inputPanel.add(new JLabel("제조사:"));
        manufacturerField = new JTextField();
        inputPanel.add(manufacturerField);
        inputPanel.add(new JLabel("가격:"));
        priceField = new JTextField();
        inputPanel.add(priceField);

        addButton = new JButton("추가");
        updateButton = new JButton("수정");
        deleteButton = new JButton("삭제");
        searchButton = new JButton("조회");

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(addButton);
        buttonPanel.add(updateButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(searchButton);

        inputPanel.add(buttonPanel);
        add(inputPanel, BorderLayout.NORTH);

        // 결과를 출력할 텍스트 영역
        resultArea = new JTextArea();
        resultArea.setEditable(false);
        add(new JScrollPane(resultArea), BorderLayout.CENTER);

        // 버튼 이벤트 처리
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 기종 추가 로직
                String name = nameField.getText();
                String manufacturer = manufacturerField.getText();
                double price = Double.parseDouble(priceField.getText());
                // TODO: 데이터베이스에 기종 추가
                resultArea.append("기종 추가: " + name + ", " + manufacturer + ", " + price + "\n");
            }
        });

        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 기종 수정 로직
                int id = Integer.parseInt(idField.getText());
                String name = nameField.getText();
                String manufacturer = manufacturerField.getText();
                double price = Double.parseDouble(priceField.getText());
                // TODO: 데이터베이스에서 기종 수정
                resultArea.append("기종 수정: ID=" + id + ", 이름=" + name + ", 제조사=" + manufacturer + ", 가격=" + price + "\n");
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 기종 삭제 로직
                int id = Integer.parseInt(idField.getText());
                // TODO: 데이터베이스에서 기종 삭제
                resultArea.append("기종 삭제: ID=" + id + "\n");
            }
        });

        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 기종 조회 로직
                int id = Integer.parseInt(idField.getText());
                // TODO: 데이터베이스에서 기종 조회
                resultArea.append("기종 조회: ID=" + id + "\n");
            }
        });
    }
}
