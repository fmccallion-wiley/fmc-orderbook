
from random import randrange
import time
import requests



users = ["TEAM SRE","TEAM_MTHREE"]
symbols = ["FB","TSLA","AAPL","C","ARK"]
types = ["buy","sell"]
MAX_QTY = 1000
MAX_RECORDS = 10000
MAX_PRICE = 1000


url_buy = 'http://sreexampledev.computerlab.online/buy'
url_sell = 'http://sreexampledev.computerlab.online/sell'

for i in range(0,MAX_RECORDS):
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
    #print(x.text)









