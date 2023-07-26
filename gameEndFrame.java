package Numbaseballgame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class gameEndFrame extends JFrame {
    //재시작시 이전프레임으로 돌아갈 수 있도록 프레임 저장
    private JFrame previousFrame;

    private String userID;
    private double passedTime;
    private int tryNumber;
    private double best_passedTime;
    private int clearOrOver;
    private JButton restartButton;
    private JButton levelButton;
    private JButton endButton;

    public gameEndFrame(JFrame previousFrame){
        this.previousFrame = previousFrame;

        setTitle("게임 결과");
        setSize(1000, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // 전체 패널을 GridLayout으로 설정
        JPanel mainPanel = new JPanel(new GridLayout(2, 1));

        // 게임 정보를 나타내는 패널
        JPanel gameInfoPanel = new JPanel(new GridLayout(5, 1));

        //게임정보 라벨들 생성
        JLabel clearOrOverLabel= new JLabel((clearOrOver==0?"Game Over":"Game Clear"));
        JLabel userIDLabel = new JLabel("유저 ID: "+userID);
        JLabel passedTimeLabel = new JLabel("게임 시간: "+passedTime);
        JLabel tryNumberLabel = new JLabel("시도 횟수: "+tryNumber);
        JLabel bestLabel = new JLabel("최고 기록: "+best_passedTime);

        //라벨 가운데 정렬
        clearOrOverLabel.setHorizontalAlignment(SwingConstants.CENTER);
        userIDLabel.setHorizontalAlignment(SwingConstants.CENTER);
        passedTimeLabel.setHorizontalAlignment(SwingConstants.CENTER);
        tryNumberLabel.setHorizontalAlignment(SwingConstants.CENTER);
        bestLabel.setHorizontalAlignment(SwingConstants.CENTER);
        
        //게임정보 나타내기
        gameInfoPanel.add(clearOrOverLabel);
        gameInfoPanel.add(userIDLabel);
        gameInfoPanel.add(passedTimeLabel);
        gameInfoPanel.add(tryNumberLabel);
        gameInfoPanel.add(bestLabel);

        //버튼을 가운데에 배치하는 패널
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));

        //버튼 생성
        restartButton = new JButton("재시작");
        levelButton = new JButton("레벨 선택");
        endButton = new JButton("종료");

        //버튼 크기 조정
        Dimension buttonSize = new Dimension(100, 70);
        restartButton.setPreferredSize(buttonSize);
        levelButton.setPreferredSize(buttonSize);
        endButton.setPreferredSize(buttonSize);

        //버튼 추가
        buttonPanel.add(restartButton);
        buttonPanel.add(levelButton);
        buttonPanel.add(endButton);

        //게임 정보 패널과 버튼 패널을 전체 패널에 추가
        mainPanel.add(gameInfoPanel);
        mainPanel.add(buttonPanel);

        //패널 프레임에 추가
        add(mainPanel);

        //재시작 버튼
        restartButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                //이전프레임 보여주고 현재 프레임 숨김
                previousFrame.setVisible(true);
                setVisible(false);
            }
        });

        //레벨 선택 버튼
        levelButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                //levelFrame.java 실행
                new levelFrame().setVisible(true);
                setVisible(false);
            }
        });

        //종료 버튼
        endButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                //종료
                System.exit(0);
            }
        });
        
    }

    //최고기록 업데이트
    private void updateBestTime(){
        if(best_passedTime==0||passedTime<best_passedTime){
            best_passedTime=passedTime;

            //라벨 변경
            JLabel bestJLabel=(JLabel)((JPanel)getContentPane().getComponent(0)).getComponent(4);
            bestJLabel.setText("최고 기록: "+best_passedTime);
        }
    }

    public static void main(String[] args){
        SwingUtilities.invokeLater(() -> {
            JFrame previousFrame=new JFrame();  //이전 프레임 생성, 초기화
            //이전프레임 정보 가져오기
            gameEndFrame frame=new gameEndFrame(previousFrame);
            frame.setVisible(true);
        });
    }
}