/*
	Author : Aniket Verma
	Project Name : Snake Game
	Project Code : SG001
*/

#include<iostream.h>
#include<dos.h>
#include<conio.h>
#include<process.h>
#include<stdlib.h>

int i=0,j=0;
int x=9,y=9;
int fx=-1,fy=-1;//food coordinates
int score = 0;
int size=0;
int speed=400;
int speedCounter=10;
int gameOver=0;
int tx[100],ty[100];
int appendTail(int,int);
void draw()
{
	clrscr();
	for(i=1;i<=23;i++)
	{
		cout<<char(2)<<" ";
	}
	cout<<endl;
	for(i=1;i<=20;i++)
	{       cout<<char(2)<<" ";
		for(j=0;j<=20;j++)
		{
			if(appendTail(i,j))cout<<char(219)<<" ";
			else if(i==x && j==y)
			{
				cout<<char(219)<<" ";
			}
			else if(fx == i && fy == j)cout<<"o ";
			else cout<<"  ";

		}
		cout<<char(2)<<endl;
	}

	for(i=1;i<=23;i++)
	{
		cout<<char(2)<<" ";
	}
	cout<<endl;

}
void isFoodEaten()
{
	if(fx==x&&fy==y)
	{
		size+=1;
		score+=5;
		if(score>=speedCounter)
		{
			speed-=5;
			speedCounter+=rand()%30;
		}
		if(speed<=150)speed=150;
		fx=rand()%20;
		fy=rand()%19;
	}
}
void checkCollision()
{
	for(i=0;i<size;i++)
	{
		if(x==tx[i]&&y==ty[i])
		{
			gameOver=-1;
			break;
		}
	}
}
int appendTail(int i,int j)
{
	int flag=0;
	for(int i2=0;i2<size;i2++)
	{
		if(i==tx[i2]&&j==ty[i2])
		{
			flag+=1;
			break;
		}
	}
	return flag;
}

int main()
{
	clrscr();
	char dir='w';
	fx=rand()%20;
	fy=rand()%19;
	while(gameOver!=-1)
	{
		for(i=size-1;i>=1;i--)
		{
			tx[i]=tx[i-1];
			ty[i]=ty[i-1];
		}
		tx[0]=x;
		ty[0]=y;

		if(kbhit())
		dir=getch();

		if(dir=='w')x--;
		if(dir=='s')x++;
		if(dir=='a')y--;
		if(dir=='d')y++;

		isFoodEaten();

		if(x==0)x=20;
		if(x==21)x=1;
		if(y==-1)y=20;
		if(y==21)y=0;

		if(dir==27)gameOver=-1;
		delay(speed);
		draw();
		checkCollision();
		cout<<"Score : "<<score<<endl;
	}
	clrscr();
	cout<<"\t\t\tGAME OVER\t\t\t"<<endl;
	cout<<"\t\t\tScore : "<<score<<endl;
	getch();
	return 0;
}