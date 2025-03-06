package ui;

import javax.swing.*;

public class MainFrame extends JFrame {
    public MainFrame() {
        // 프레임 설정 (한 번만 호출)
        setTitle("휴대폰 판매 및 입출고 관리 시스템");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // 탭 패널 생성
        JTabbedPane tabbedPane = new JTabbedPane();

        // 회원 관리 탭 추가
        tabbedPane.addTab("회원 관리", new MemberManagementPanel());

        // 휴대폰 기종 관리 탭 추가
        tabbedPane.addTab("휴대폰 기종 관리", new ModelManagementPanel());

        // 재고 관리 탭 추가
        tabbedPane.addTab("재고 관리", new StockManagementPanel());

        // 탭 패널을 프레임에 추가
        add(tabbedPane);

        // 프레임 표시
        setVisible(true);
    }

    public static void main(String[] args) {
        // Swing은 이벤트 디스패치 스레드에서 실행되어야 합니다.
        SwingUtilities.invokeLater(() -> {
            new MainFrame();
        });
    }
}