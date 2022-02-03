#!/usr/bin/env python

from random import randrange
import time
import requests
import os

with open('symbols.list') as symbol_list:
    symbols = [v.rstrip("\n") for v in symbol_list.readlines()]
symbol_list.close()

with open('types.list') as types_list:
    types = [v.rstrip("\n") for v in types_list.readlines()]
types_list.close()

with open('users.list') as users_lists:
    users = [v.rstrip("\n") for v in users_lists.readlines()]
users_lists.close()

MAX_QTY = int(os.environ["MAX_QTY"])
MAX_RECORDS = int(os.environ["MAX_RECORDS"])
MAX_PRICE = int(os.environ["MAX_PRICE"])
HOSTNAME = os.environ["appname"]

url_buy = 'http://'+HOSTNAME+'/buy'
url_sell = 'http://'+HOSTNAME+'/sell'

for i in range(0,randrange(MAX_RECORDS)):
    idx_users = randrange(len(users))
    idx_symbols = randrange(len(symbols))
    secondsSinceEpoch = str(time.time())
    idx_types =  randrange(len(types))
    secondsSinceEpoch = secondsSinceEpoch.replace('.', '')
    row = {'orderid':str(users[idx_users])+str(secondsSinceEpoch),
           'symbol':str(symbols[idx_symbols]),
           'userid':str(users[idx_users]),
          'type':str(types[idx_types]),
        'qty':randrange(MAX_QTY),
        'price':randrange(200)
    }
    if str(types[idx_types])=='buy':
        url = url_buy
    if str(types[idx_types])=='sell':
        url = url_sell
    print(row)
    print(url)
    x = requests.post(url, data=row)
