#!/usr/local/bin python
# _*_ coding: utf-8 _*_
# Create by puppet on 2022/2/15
"""
文件选取对话框
"""

import sys
from PyQt5 import QtWidgets, QtGui


class Example(QtWidgets.QMainWindow):
    def __init__(self):
        super(Example, self).__init__()
        self.init_ui()

    def init_ui(self):
        # 定义一个文本编辑部件
        self.text_edit = QtWidgets.QTextEdit()
        self.setCentralWidget(self.text_edit)
        # 定义一个操作
        open_file = QtWidgets.QAction(QtGui.QIcon('open.png'), 'Open', self)
        open_file.setShortcut('Ctrl+O')
        open_file.setStatusTip('Open New File')
        open_file.triggered.connect(self.show_dialog)

        # 创建状态栏、菜单栏、子菜单栏(文件菜单栏)、并给文件菜单栏添加一个操作
        self.statusBar()
        menubar = self.menuBar()
        file_menu = menubar.addMenu('File')
        file_menu.addAction(open_file)

        self.setGeometry(460, 250, 600, 400)
        self.setWindowTitle('Pick File Dialog')
        self.show()

    def show_dialog(self):
        # 打开文件选择对话框，第二个参数为"对话框标题"，第三个参数为"文件选取"的“初始目录”
        file_name = QtWidgets.QFileDialog.getOpenFileName(self, 'Open File', '~/Downloads')
        print(file_name)
        # ('/Users/puppet/PycharmProjects/demo-pyqt5/demo20_pick_file_dialog.py', 'All Files (*)')
        if file_name[0]:
            with open(file_name[0], 'r', encoding='utf-8') as f:
                data = f.read()
                self.text_edit.setText(data)


if __name__ == '__main__':
    app = QtWidgets.QApplication(sys.argv)
    ex = Example()
    sys.exit(app.exec_())
