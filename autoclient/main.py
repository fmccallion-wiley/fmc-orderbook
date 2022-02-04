#!/usr/bin/env python

import requests
import os
import sys

HOSTNAME = os.environ["appname"]
page=sys.argv[1]

url = 'http://'+HOSTNAME+'/'+page
print(url)
x = requests.get(url)
print("Response: "+str(x))
