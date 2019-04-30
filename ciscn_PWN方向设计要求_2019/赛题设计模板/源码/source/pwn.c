// gcc pwn.c -o pwn -m32 -fno-stack-protector -z execstack
#include "stdio.h"
#include "stdlib.h"
#include "string.h"
#include "unistd.h"

void shell()
{
	system("/bin/sh");
}

void init() {
	setvbuf(stdout, 0, 2, 0);
	setvbuf(stdin, 0, 1, 0);
	setvbuf(stderr, 0, 1, 0);
	alarm(0);
}

void pwn(){
	char buf[16];
	read(0,buf,0x100);
}

int main(int argc, char const *argv[],char const *env[])
{
	init();
	pwn();
	if(1){
		puts("NO, Please continue! ");	
	}
	return 0;
}

