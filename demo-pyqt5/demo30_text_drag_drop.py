#!/usr/local/bin python
# _*_ coding: utf-8 _*_
# Create by puppet on 2022/2/22
"""
一个简单的拖放功能：把一个文本从编辑框里拖到按钮上，更新按钮上的标签（文字）。
"""
import sys

from PyQt5 import QtWidgets
from PyQt5.QtCore import Qt, QDate
from PyQt5 import QtGui


class Button(QtWidgets.QPushButton):
    """自定义按钮"""

    def __init__(self, title, parent):
        super(Button, self).__init__(title, parent)
        # 开启拖放功能
        self.setAcceptDrops(True)

    def dragEnterEvent(self, a0: QtGui.QDragEnterEvent) -> None:
        """重写拖动进入时的事件处理器"""
        if a0.mimeData().hasFormat('text/plain'):
            # 如果是文本类型，接受并处理该事件
            a0.accept()
        else:
            # 如果不是文本类型，则拒绝处理
            a0.ignore()

    def dropEvent(self, a0: QtGui.QDropEvent) -> None:
        """重写放置时的事件处理器"""
        self.setText(a0.mimeData().text())


class Example(QtWidgets.QWidget):
    def __init__(self):
        super(Example, self).__init__()
        self.init_ui()

    def init_ui(self):
        edit = QtWidgets.QLineEdit(self)
        # 将 edit 设置为允许拖动
        edit.setDragEnabled(True)
        edit.move(30, 65)

        button = Button('Button', self)
        button.move(190, 65)

        self.setGeometry(460, 250, 600, 400)
        self.setWindowTitle('Simple drag and drop')
        self.show()


if __name__ == '__main__':
    app = QtWidgets.QApplication(sys.argv)
    example = Example()
    sys.exit(app.exec_())
