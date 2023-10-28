import javax.swing.*;
import javax.swing.plaf.FontUIResource;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

public class GameFrame extends JFrame implements KeyListener, MouseListener, ActionListener {
    int x=0;
    int y=0;
    private CARD[][]  cards=new CARD[4][4];
    int score = 0;
    private int cols=4;
    private int rows=4;
    JMenuItem newgame = new JMenuItem("新游戏");
    JMenuItem exit = new JMenuItem("退出");
    JMenuItem ophelp = new JMenuItem("操作帮助");
    JMenuItem winoption = new JMenuItem("获胜条件");
    private int gameFlag =0;
    public GameFrame(){
        //初始化界面
        initJFrame();
        //初始化菜单
        initMenu();
        //初始化画布
        initImage();
        //创建卡片对象
        initCard();
        //随机数
        createRandomNum();
        this.setVisible(true);
    }

    //初始化框架
    private void initJFrame(){
        //创建构造方法
        this.setTitle("2048 v1.0");//标题
        this.setSize(603,680);//设置窗体大小
        this.getContentPane().setBackground(new Color(66,136,83));//设置默认背景
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//如何关闭
        this.setLocationRelativeTo(null);//居中打开
        this.setResizable(false);//设置窗体不允许变大
        this.setLayout(null);
        this.addKeyListener(this);//添加键盘监听器
    }
    //初始化得分
    private void initImage() {
        getContentPane().removeAll();
        //得分
        JLabel stepCount = new JLabel("得分：" + score);
        Font font=new Font("微雅软黑",Font.BOLD,30);
        stepCount.setBounds(60, 15, 200, 100);
        stepCount.setFont(font);
        stepCount.setForeground(Color.WHITE);
        this.getContentPane().add(stepCount);
        this.getContentPane().repaint();
    }
    //初始化菜单
    private void initMenu(){
        //指定文本的字样和大小
        Font font=new Font("微雅软黑",Font.BOLD,18);
        //创建jMenubar
        JMenuBar jmb=new JMenuBar();
        //添加菜单选项
        //菜单选项一：游戏
        JMenu jMenu1=new JMenu("游戏");
        //设置字体
        jMenu1.setFont(font);
        //创建子项
        JMenuItem newgame=new JMenuItem("新游戏");
        newgame.setFont(font);
        JMenuItem pause=new JMenuItem("暂停");
        pause.setFont(font);
        JMenuItem exit=new JMenuItem("退出");
        exit.setFont(font);
        jMenu1.add(newgame);
        jMenu1.add(pause);
        jMenu1.add(exit);

        //菜单选项二：帮助
        JMenu jMenu2=new JMenu("帮助");
        jMenu2.setFont(font);
        //创建子项
        JMenuItem help=new JMenuItem("操作帮助");
        newgame.setFont(font);
        JMenuItem win=new JMenuItem("胜利条件");
        exit.setFont(font);
        //将子项添加到菜单之中去
        jMenu2.add(help);
        jMenu2.add(win);

        //菜单选项三：关于我们
        JMenu jMenu3=new JMenu("关于我们");
        jMenu3.setFont(font);
        //将上面的菜单选项添加到功能中去
        jmb.add(jMenu1);
        jmb.add(jMenu2);
        jmb.add(jMenu3);

        //生成菜单对象
        this.setJMenuBar(jmb);

        //添加事件监听(子事件）
        newgame.addActionListener(this);
        exit.addActionListener(this);
        help.addActionListener(this);
        win.addActionListener(this);
        pause.addActionListener(this);

        //对每个菜单指向添加指令
        newgame.setActionCommand("restart");
        exit.setActionCommand("exit");
        help.setActionCommand("help");
        win.setActionCommand("win");
        pause.setActionCommand("pause");
    }
    //进行菜单的监听事件，弹出对应的窗口
    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        if ("restart".equals(command)) {
            //执行新游戏方法
            int score = 0;
            new GameFrame();
        } else if ("exit".equals(command)) {
            Object[] option = {"确定", "取消"};
            //使用返回值确定是否退出0-退出，1-取消
            int res = JOptionPane.showOptionDialog(this, "你确定要退出游戏吗？", "",
                    JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, option, option[0]);
            if (res == 0) {
                System.exit(0);
            }
        } else if ("help".equals(command)) {
            JOptionPane.showMessageDialog(null, "通过键盘的上下左右来移动，相同数字会合并！",
                    "提示！", JOptionPane.INFORMATION_MESSAGE);
        } else if ("win".equals(command)) {
            JOptionPane.showMessageDialog(null, "得到数字2048获得胜利，当没有空卡片则失败！",
                    "获胜条件", JOptionPane.INFORMATION_MESSAGE);
        } else if ("pause".equals(command)) {
            Object[] option = {"退出", "继续"};
            //使用返回值确定是否退出0-退出，1-继续
            int res = JOptionPane.showOptionDialog(this, "游戏暂停中你可以点击继续游戏", "",
                    JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, option, option[0]);
            if (res == 0) {
                System.exit(0);
            }
        }
    }

    //创建卡片,初始化数组
    private void initCard(){
        CARD card;
        for(int i=0;i<4;i++){
            for(int j=0;j<4;j++){
                card=new CARD(i,j);
                cards[i][j]=card;
            }
        }
    }
    @Override
    //利用画布的paint方法，绘制用户界面的组件和元素
    public void paint(Graphics g){
        super.paint(g);
        //绘制卡片
        drawCard(g);
    }
    //根据前面的赋值来绘制卡片
    private void drawCard(Graphics g) {
        CARD card;
        for(int i=0;i<4;i++){
            for(int j=0;j<4;j++){
                card=cards[i][j];//调用前面的数组来进行绘制
                card.draw(g);
            }
        }
    }

    //创建卡片
    private  void createRandomNum(){
        Random random=new Random();
        int num=random.nextInt(5)+1;
        if(num==1){
            num=4;
        }else{
            num=2;
        }
        score=num+score;
        //如果格子满了，则不需要再去取格子了
        if(cardIsFull()){
            return;
        }
        CARD card=getRandomCard(random);
        if(card!=null){
            card.setNum(num);
        }
    }
    //判断是否还有空卡片
    private boolean cardIsFull(){
        CARD card;
        for(int i=0;i<rows;i++){
            for(int j=0;j<cols;j++){
                card=cards[i][j];
                if(card.getNum()==0){
                    return false;
                }
            }
        }
        return true;
    }

    //获得初始化卡片的位置
    private CARD getRandomCard(Random random) {
        int i=random.nextInt(rows);
        int j=random.nextInt(cols);
        CARD card=cards[i][j];
        //如果是空白卡片直接返回
        if(card.getNum()==0){
            return card;
        }
        //没有找到继续递归找
        return getRandomCard(random);
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }
    @Override
    public void keyPressed(KeyEvent e) {
    }

    //监听键盘
    @Override
    public void keyReleased(KeyEvent e) {
        //游戏的状态不处于开始的时候，直接返回
        int key=e.getKeyCode();
        if(gameFlag!=0){
            return;
        }
        switch(key){
            //向上
            case KeyEvent.VK_UP:
            case KeyEvent.VK_W:
                moveCard(1);
                break;
            //向右
            case KeyEvent.VK_RIGHT:
            case KeyEvent.VK_D:
                moveCard(2);
                break;
            //向下
            case KeyEvent.VK_DOWN:
            case KeyEvent.VK_S:
                moveCard(3);
                break;
            //向左
            case KeyEvent.VK_LEFT:
            case KeyEvent.VK_A:
                moveCard(4);
                break;
        };
        repaint();
    }
    //按方向来移动卡片
    private void moveCard(int dir) {
        //清理卡片的合并标记
        ClearCard();
        CARD card;
        if(dir==1){
            moveCardTop(true);
        }else if(dir==2){
            moveCardRight(true);
        }else if(dir==3) {
            moveCardBotton(true);
        }else if(dir==4){
            moveCardLeft(true);
        }
        //重新创建卡片
        createRandomNum();
        //得分重刷
        initImage();
        //重绘画布
        repaint();
        //判断游戏是否结束
        gameOverOrNot();
    }

    private void ClearCard() {
        CARD card;
        for(int i=0;i<rows;i++){
            for(int j=0;j<cols;j++){
                card=cards[i][j];
                card.setMerge(false);
            }
        }
    }
    //判断卡片是否还能合并
    private boolean canMerge() {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 3; j++) {
                if (cards[i][j].getNum() == cards[i][j + 1].getNum()) {
                    return true;
                }
            }
        }
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 4; j++) {
                if (cards[i][j].getNum() == cards[i + 1][j].getNum()) {
                    return true;
                }
            }
        }
        return false;
    }
    //判断游戏是否结束
    private void gameOverOrNot(){
        //结束条件：1.位置已经满 2.四个方向都没有可以合并的卡片
        if(isWin()){
            gameWin();
        }else if(cardIsFull()||!canMerge()){
            gameOver();
        }
    }

    private boolean moveCardLeft(boolean b) {
        CARD card;
        boolean res=false;
        for (int i = 0; i < 4; i++) {
            for (int j = 1; j < 4; j++) {
                card = cards[i][j];
                if (card.getNum() != 0) {
                    if (card.moveLeft(cards, b)) {
                        res=true;
                    }
                }
            }
        }
        return res;
    }

    private boolean moveCardBotton(boolean b) {
        CARD card;
        boolean res=false;
        for (int i = 2; i >= 0; i--) {
            for (int j = 0; j < 4; j++) {
                card = cards[i][j];
                if (card.getNum() != 0) {
                    if(card.moveDown(cards,b)) {
                        res = true;
                    }
                }
            }
        }
        return res;
    }

    private boolean moveCardRight(boolean b) {
        CARD card;
        boolean res=false;
        for (int i = 0;i < 4; i++){
            for (int j = 2;j >= 0;j--){
                card = cards[i][j];
                if(card.getNum()!=0){
                    if(card.moveRight(cards,b)) {
                        res = true;
                    }
                }
            }
        }
        return res;
    }

    private boolean moveCardTop(boolean b) {
        CARD card;
        boolean res=false;
        for (int i = 1;i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                card = cards[i][j];
                if (card.getNum() != 0) {
                    if(card.moveTop(cards, b)) {
                        res = true;
                    }
                }
            }
        }
        return res;
    }

    private void gameWin() {
        //记录卡片的最大值，并记录
        gameFlag = 1;

        //弹出来结果显示
        UIManager.put("OptionPane.buttonFont", new FontUIResource(new Font("微软雅黑", Font.PLAIN, 18)));
        String[] options = {"再来一局", "退出"};
        int choice = JOptionPane.showOptionDialog(this,
                "你成功了，太棒了",
                "游戏结束",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.INFORMATION_MESSAGE,
                null,
                options,
                options[0]);
        if (choice == 0) {
            this.dispose();
            new GameFrame();
        } else if (choice == 1) {
            System.exit(0);
        }
    }

    private boolean isWin(){
        CARD card;
        for(int i=0;i<rows;i++){
            for(int j=0;j<cols;j++){
                card=cards[i][j];
                if(card.getNum()==2048){
                    return true;
                }
            }
        }
        return false;
    }
    private void gameOver(){
        gameFlag=1;
        //弹出来结果显示
        String[] options = {"再来一局", "退出"};
        int choice = JOptionPane.showOptionDialog(this,
                "你失败了，请再接再励",
                "游戏结束",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.INFORMATION_MESSAGE,
                null,
                options,
                options[0]);
        if (choice == 0) {
            this.dispose();
            new GameFrame();
        } else if (choice == 1) {
            System.exit(0);
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}

