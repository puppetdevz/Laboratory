#!/usr/local/bin python
# _*_ coding: utf-8 _*_
# Create by puppet on 2022/2/22
"""
组件拖放功能：拖放一个按钮组件
"""
import sys

from PyQt5 import QtWidgets
from PyQt5.QtCore import Qt, QDate, QMimeData
from PyQt5 import QtGui


class Button(QtWidgets.QPushButton):
    """自定义按钮"""

    def __init__(self, title, parent):
        super(Button, self).__init__(title, parent)

    def mouseMoveEvent(self, a0: QtGui.QMouseEvent) -> None:
        """重写鼠标移动时的事件处理器"""
        if a0.buttons() != Qt.RightButton:
            # 如果不是鼠标右键，则直接返回
            return

        mime_data = QMimeData()

        drag = QtGui.QDrag(self)
        drag.setMimeData(mime_data)
        drag.setHotSpot(a0.pos() - self.rect().topLeft())
        drag.exec_(Qt.MoveAction)

    def mousePressEvent(self, a0: QtGui.QMouseEvent) -> None:
        """重写鼠标按下时的事件处理器"""
        super().mousePressEvent(a0)
        if a0.button() == Qt.LeftButton:
            # 如果按下的是鼠标左键
            print('press')


class Example(QtWidgets.QWidget):
    def __init__(self):
        super(Example, self).__init__()
        self.init_ui()

    def init_ui(self):
        # 设置 QWidght 组件内的小组件为“允许拖放”
        self.setAcceptDrops(True)

        self.button = Button('Button', self)
        self.button.move(100, 65)

        self.setGeometry(460, 250, 600, 400)
        self.setWindowTitle('Component drag and drop')
        self.show()

    def dragEnterEvent(self, a0: QtGui.QDragEnterEvent) -> None:
        a0.accept()

    def dropEvent(self, a0: QtGui.QDropEvent) -> None:
        position = a0.pos()
        self.button.move(position)

        a0.setDropAction(Qt.MoveAction)
        a0.accept()


if __name__ == '__main__':
    app = QtWidgets.QApplication(sys.argv)
    example = Example()
    sys.exit(app.exec_())
