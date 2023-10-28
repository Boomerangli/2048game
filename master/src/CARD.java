import java.awt.*;

public class CARD {
    private int x=70;//x坐标
    private int y=150;//y坐标
    private int w=105;//宽
    private int h=105;//高
    private int i=0;//下标i
    private int j=0;//下标j
    private int start=10;//偏移量
    private int num=0;//数字
    int [][] data=new int[4][4];
    private boolean merge=false;//是否已经合并，如果已经合并则不能再合并

    public boolean isMerge() {
        return merge;
    }

    public void setMerge(boolean merge) {
        this.merge = merge;
    }

    //创建卡片对象
    public CARD(int i, int j){
        this.i=i;
        this.j=j;
        cal();
    }

    //计算卡片的坐标
    public void cal(){
        this.x=70+j*w+(j+1)*start;
        this.y=150+i*h+(i+1)*start;
    }

    //在card之中定义一个卡片的绘制
    public void draw(Graphics g){
        //根据数字获取对应的颜色
        Color color=getColor();
        Color oldercolor=g.getColor();
        //设置新颜色
        g.setColor(color);
        g.fillRoundRect(x,y,w,h,4,4);
        //还原旧颜色
        g.setColor(oldercolor);

        //绘制卡片上的数字
        if(num!=0){
            //给字体添加颜色
            g.setColor(new Color(125,78,51));
            Font font=new Font("微雅软黑",Font.BOLD,40);
            g.setFont(font);

            //放置的位子和数字的大小有关
            String text=num+"";
            int tlen=getWordWidth(font,text,g);
            int tx=x+(w-tlen)/2;
            int ty=y+65;
            g.drawString(text,tx,ty);
        }
    }

    //获取字体的长度
    public static int getWordWidth(Font font,String content,Graphics g){
        //拿到字符的宽度
        FontMetrics metrics=g.getFontMetrics(font);
        int width=0;
        for(int i=0;i<content.length();i++){
            //将字符的宽度一个个累加起来
            width+=metrics.charWidth(content.charAt(i));
        }
        return width;
    }
    //根据数字获取颜色
    private Color getColor(){
        Color color=null;
        switch (num){
            case 2:
                color=new Color(238,244,234);
                break;
            case 4:
                color=new Color(222,236,200);
                break;
            case 8:
                color=new Color(174,213,130);
                break;
            case 16:
                color=new Color(142,201,75);
                break;
            case 32:
                color=new Color(111,148,48);
                break;
            case 64:
                color=new Color(76,174,124);
                break;
            case 128:
                color=new Color(60,180,144);
                break;
            case 256:
                color=new Color(45,130,120);
                break;
            case 512:
                color=new Color(9,97,26);
                break;
            case 1024:
                color=new Color(242,177,121);
                break;
            case 2048:
                color=new Color(223,185,0);
                break;
            //默认颜色
            default:
                color=new Color(92,151,117);
                break;
        }
        return color;
    }

    public void setNum(int num) {
        this.num=num;
    }

    public int getNum() {
        return this.num;
    }
    //向上移动的方法
    public boolean moveTop(CARD[][] cards, boolean b) {
        //设置递归的退出条件
        if(i==0){
            return false;
        }
        //找到上一个卡片
        CARD prev=cards[i-1][j];
        //把当前的卡片交换上去
        if(prev.getNum()==0){
            if(b){
            prev.num=this.num;
            this.num=0;
            prev.moveTop(cards,b);}
            return true;
        }//需要合并的数字
        else if(prev.getNum()==num && !prev.merge){
            if(b){
            prev.merge=true;
            prev.num=this.num*2;
            this.num=0;}
            return true;
        }
        else{
            return false;
        }
    }

    public boolean moveRight(CARD[][] cards, boolean b) {
        if (j == 3) {
            return false;
        }
        CARD prev = cards[i][j + 1];
        if (prev.getNum() == 0) {
            if(b){
            prev.num = this.num;
            this.num = 0;
            prev.moveRight(cards,b);}
            return true;
        } else if (prev.getNum() == num && !prev.merge) {
            if(b){
            prev.merge = true;
            prev.num = this.num*2;
            this.num = 0;}
            return true;
        }else{
            return false;
        }
    }

    public boolean moveDown(CARD[][] cards, boolean b) {
        if (i == 3) {
            return false;
        }
        CARD prev = cards[i + 1][j];
        if (prev.getNum() == 0) {
            if(b){
                prev.num = this.num;
                this.num = 0;
                prev.moveDown(cards,b);
            }
            return true;
        } else if (prev.getNum() == num && !prev.merge) {
            if(b){
            prev.merge = true;
            prev.num = this.num*2;
            this.num = 0;}
            return true;
        }else{
            return false;
        }
    }

    public boolean moveLeft(CARD[][] cards, boolean b) {
        if (j == 0) {
            return false;
        }
        CARD prev = cards[i][j - 1];
        if (prev.getNum() == 0) {
            if(b){
            prev.num = this.num;
            this.num = 0;
            prev.moveLeft(cards,b);}
            return true;
        } else if (prev.getNum() == num && !prev.merge) {
            if(b){
            prev.merge = true;
            prev.num = this.num*2;
            this.num = 0;}
            return true;
        }else{
            return false;
        }
    }
}
