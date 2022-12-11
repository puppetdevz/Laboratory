#!/usr/bin/env python
# _*_ coding: utf-8 _*_
# Create by Jack on 2021/04/21

from sanic import Sanic
from sanic.response import json

app = Sanic('first_app')

@app.route('/')
async def test(req):
    return json({'hello': 'world'})

if __name__ == '__main__':
    app.run(host='localhost', port=8000)