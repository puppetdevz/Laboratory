#!/usr/bin/env python
# _*_ coding: utf-8 _*_
# Create by puppet on 2022/2/8
"""
主窗口即为状态栏、菜单栏和工具栏的总和
该例子将这三种栏写在同一个窗口中
"""
import sys

from PyQt5.QtWidgets import QMainWindow, QApplication, QAction, qApp, QMenu, QTextEdit
from PyQt5.QtGui import QIcon


class Example(QMainWindow):
    def __init__(self):
        super(Example, self).__init__()
        self.init_ui()

    def init_ui(self):
        # 创建一个编辑区
        text_edit = QTextEdit()
        # 将编辑区铺满整个主窗口
        self.setCentralWidget(text_edit)

        # 创建状态栏，并定义基本信息
        self.statusBar().showMessage('Ready')

        # 创建“退出操作”
        exit_act = QAction(QIcon('exit.png'), 'Exit', self)
        exit_act.setShortcut('Ctrl+Q')
        # 既可以用 qApp.quit 也可以用 self.close
        # exit_act.triggered.connect(qApp.quit)
        exit_act.triggered.connect(self.close)

        # 创建菜单栏
        menu_bar = self.menuBar()
        # 给菜单栏添加菜单
        file_menu = QMenu('File', self)
        # 将“退出操作”添加到“文件菜单”中
        file_menu.addAction(exit_act)
        # 将“文件菜单”添加到“菜单栏”中
        menu_bar.addMenu(file_menu)

        # 添加工具栏
        tool_bar = self.addToolBar('Exit')
        tool_bar.addAction(exit_act)

        # 初始化界面
        self.setGeometry(460, 250, 600, 300)
        self.setWindowTitle('Main Window')
        self.show()


if __name__ == '__main__':
    app = QApplication(sys.argv)
    ex = Example()
    sys.exit(app.exec_())
