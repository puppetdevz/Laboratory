#!/usr/local/bin python
# _*_ coding: utf-8 _*_
# Create by puppet on 2022/2/22
"""
小滑块的组件，这个小滑块能拖着前后滑动，这个经常用于修改一些具有范围的数值
"""
import sys

from PyQt5 import QtWidgets
from PyQt5.QtCore import Qt
from PyQt5.QtGui import QColor, QPixmap


class Example(QtWidgets.QWidget):
    def __init__(self):
        super(Example, self).__init__()
        self.init_ui()

    def init_ui(self):
        slider = QtWidgets.QSlider(Qt.Horizontal, self)
        slider.setFocusPolicy(Qt.NoFocus)
        slider.setGeometry(30, 40, 100, 30)
        # 将“数值改变信号”与自定义处理函数 change_value 绑定，并将信号事件转换为 int 型
        slider.valueChanged[int].connect(self.change_value)

        self.label = QtWidgets.QLabel(self)
        self.label.setPixmap(QPixmap('mute.png'))
        self.label.setGeometry(160, 40, 80, 30)

        self.setGeometry(460, 250, 600, 400)
        self.setWindowTitle('Slider')
        self.show()

    def change_value(self, value):
        """改变数值"""
        if value == 0:
            self.label.setPixmap(QPixmap('mute.png'))
        elif 0 < value <= 30:
            self.label.setPixmap(QPixmap('min.png'))
        elif 30 < value < 80:
            self.label.setPixmap(QPixmap('med.png'))
        else:
            self.label.setPixmap(QPixmap('max.png'))


if __name__ == '__main__':
    app = QtWidgets.QApplication(sys.argv)
    example = Example()
    sys.exit(app.exec_())
