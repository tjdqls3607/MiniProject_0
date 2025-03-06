package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import jdbc.Test;

public class MemberManagementPanel extends JPanel {
    private JTextField idField, nameField, phoneField, birthField;
    private JButton addButton, updateButton, deleteButton, searchButton;
    private JTextArea resultArea;

    public MemberManagementPanel() {
        setLayout(new BorderLayout());

        // 입력 필드와 버튼을 담을 패널
        JPanel inputPanel = new JPanel(new GridLayout(5, 2, 5, 5)); // 간격 추가
        inputPanel.add(new JLabel("회원 ID:"));
        idField = new JTextField();
        inputPanel.add(idField);
        inputPanel.add(new JLabel("이름:"));
        nameField = new JTextField();
        inputPanel.add(nameField);
        inputPanel.add(new JLabel("전화번호:"));
        phoneField = new JTextField();
        inputPanel.add(phoneField);
        inputPanel.add(new JLabel("생년월일 (YYYY-MM-DD):"));
        birthField = new JTextField();
        inputPanel.add(birthField);

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
        resultArea = new JTextArea(10, 40); // 크기 조정
        resultArea.setEditable(false);
        add(new JScrollPane(resultArea), BorderLayout.CENTER);

        // 버튼 이벤트 처리
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 입력값 가져오기
                String name = nameField.getText();
                String phone = phoneField.getText();
                String birth = birthField.getText();

                // 입력값 검증
                if (name.isEmpty() || phone.isEmpty() || birth.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "모든 필드를 입력해주세요.");
                    return;
                }

                try {
                    // 생년월일을 java.sql.Date로 변환
                    java.sql.Date sqlDate = java.sql.Date.valueOf(birth);

                    // 데이터베이스에 회원 추가
                    int ret = Test.insertMember(0, name, phone, sqlDate);
                    if (ret > 0) {
                        resultArea.append("회원 추가 성공: " + name + ", " + phone + ", " + birth + "\n");
                    } else {
                        resultArea.append("회원 추가 실패\n");
                    }
                } catch (IllegalArgumentException ex) {
                    JOptionPane.showMessageDialog(null, "생년월일 형식이 잘못되었습니다. (YYYY-MM-DD)");
                }
            }
        });

        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int id = Integer.parseInt(idField.getText());
                    String name = nameField.getText();
                    String phone = phoneField.getText();

                    int ret = Test.updateMember(id, name, phone);
                    if (ret > 0) {
                        resultArea.append("회원 수정: ID=" + id + ", 이름=" + name + ", 전화번호=" + phone + "\n");
                    } else {
                        resultArea.append("회원 수정 실패\n");
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "회원 ID는 숫자로 입력해주세요.");
                }
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int id = Integer.parseInt(idField.getText());

                    int ret = Test.deleteMember(id);
                    if (ret > 0) {
                        resultArea.append("회원 삭제: ID=" + id + "\n");
                    } else {
                        resultArea.append("회원 삭제 실패\n");
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "회원 ID는 숫자로 입력해주세요.");
                }
            }
        });

        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int id = Integer.parseInt(idField.getText());

                    // TODO: 데이터베이스에서 회원 조회
                    resultArea.append("회원 조회: ID=" + id + "\n");
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "회원 ID는 숫자로 입력해주세요.");
                }
            }
        });
    }
}