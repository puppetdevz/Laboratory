#!/usr/local/bin python
# _*_ coding: utf-8 _*_
# Create by puppet on 2022/2/15
import sys
from PyQt5.QtCore import Qt
from PyQt5.QtWidgets import QApplication, QWidget, QLCDNumber, QSlider, QVBoxLayout


class Example(QWidget):
    def __init__(self):
        super(Example, self).__init__()
        self.init_ui()

    def init_ui(self):
        # 分别定义一个数字和一个滑块小部件
        lcd = QLCDNumber(self)
        sld = QSlider(Qt.Horizontal, self)

        # 定义垂直盒布局
        vbox = QVBoxLayout()
        # 将数字和滑块小部件放到盒布局中
        vbox.addWidget(lcd)
        vbox.addWidget(sld)
        self.setLayout(vbox)
        sld.valueChanged.connect(lcd.display)

        self.setGeometry(460, 250, 600, 300)
        self.setWindowTitle('Signals And Slots')
        self.show()


if __name__ == '__main__':
    app = QApplication(sys.argv)
    ex = Example()
    sys.exit(app.exec_())
