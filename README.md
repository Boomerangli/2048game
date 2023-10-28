# 2048game
实现了2048游戏的部分基础操作
1. 游戏界面实现
用二维数组模拟游戏盘面，实现显示游戏盘面的方法。
实现显示得分和游戏状态的方法。
实现键盘输入的监听器，处理用户输入的方向键。
实现游戏开始、暂停和结束等状态的管理方法。
2. 游戏逻辑实现
实现数字方块向上、向下、向左、向右移动的方法。
实现相邻相同数字方块合并的方法。
实现得分计算的方法。
3. 随机数生成实现
实现在空白位置随机生成 2 或 4 的数字方块的方法。
4. 游戏结束检查实现
在每次操作后检查是否还有空白位置，是否还有相邻的相
同数字方块可以合并，如果没有则游戏结束。