#!/usr/bin/env python
# _*_ coding: utf-8 _*_
# Create by puppet on 2022/2/3
import sys

from PyQt5.QtWidgets import QApplication, QMainWindow, QDesktopWidget, QAction, qApp, QMenu
from PyQt5.QtGui import QIcon


class Example(QMainWindow):
    def __init__(self):
        super(Example, self).__init__()
        self.init_ui()

    def init_ui(self):
        # 创建一个“退出”行为
        exit_act = QAction(QIcon('exit.png'), 'Exit', self)
        exit_act.setShortcut('Ctrl+Q')
        exit_act.setToolTip('Exit application')
        exit_act.triggered.connect(qApp.quit)

        # 新建一个状态栏，并给定一个文字信息作为状态
        self.statusBar().showMessage('Ready')

        # 新建菜单栏，并给菜单栏添加菜单，再给菜单添加行为
        menubar = self.menuBar()
        # menubar.setNativeMenuBar(False)
        file_menu = menubar.addMenu('File')
        file_menu.addAction(exit_act)

        # 在 file 菜单下新建一个“导入”子菜单和一个“新建”行为，同时“导入”子菜单下有一个导入行为
        # 创建“导入”子菜单及子菜单下的“导入”行为
        imp_menu = QMenu('Import', self)
        imp_act = QAction('Import mail', self)
        imp_menu.addAction(imp_act)
        # 创建“新建”行为
        new_act = QAction('New', self)
        # 将子菜单和行为绑定到 file 菜单中
        file_menu.addAction(new_act)
        file_menu.addMenu(imp_menu)

        # 设置主窗口的位置和大小
        # move() 与 resize() 的结合，前两个入参看作是 move() 的入参，后两个是 resize() 的入参
        # self.setGeometry(300, 300, 250, 150)

        # 注意：如果要窗口居中，则需要先调用 resize() 后再调 center() 否则 center() 没有效果
        self.resize(300, 200)
        self.center()
        # 设置主窗口的标题
        self.setWindowTitle('Simple Menu Bar')
        # 显示主窗口
        self.show()

    def center(self):
        qr = self.frameGeometry()
        center_poit = QDesktopWidget().availableGeometry().center()
        qr.moveCenter(center_poit)
        self.move(qr.topLeft())


if __name__ == '__main__':
    app = QApplication(sys.argv)
    ex = Example()
    sys.exit(app.exec_())
