#!/usr/bin/env python
# _*_ coding: utf-8 _*_
# Create by puppet on 2022/2/3
import sys

from PyQt5.QtWidgets import QApplication, QMainWindow, QDesktopWidget, qApp, QAction


class Example(QMainWindow):
    def __init__(self):
        super(Example, self).__init__()
        self.init_ui()

    def init_ui(self):
        # 创建“退出”操作
        exit_act = QAction('Exit', self)
        exit_act.triggered.connect(qApp.quit)
        exit_act.setShortcut('Ctrl+Q')
        # 对于工具栏来说，添加即创建，直接添加一个“工具栏”，再给工具栏添加一个“操作“
        # 注意：工具栏可以选择放置在不同区域，添加“工具栏“时，可以指定添加到某个区域
        tool = self.addToolBar('Exit')
        tool.addAction(exit_act)

        # 注意：如果要窗口居中，则需要先调用 resize() 后再调 center() 否则 center() 没有效果
        self.resize(600, 400)
        self.center()
        # 设置主窗口的标题
        self.setWindowTitle('Tool Bar')
        # 显示主窗口
        self.show()

    def center(self):
        qr = self.frameGeometry()
        center_point = QDesktopWidget().availableGeometry().center()
        qr.moveCenter(center_point)
        self.move(qr.topLeft())


if __name__ == '__main__':
    app = QApplication(sys.argv)
    ex = Example()
    sys.exit(app.exec_())
