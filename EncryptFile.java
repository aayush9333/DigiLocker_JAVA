import java.io.*;
class EncryptFile
{
byte enc[];
boolean encrypt(String src,String dest,int b,int shft)
{
int rsiz;
byte bfr[];
FileInputStream r;
FileOutputStream w;
try
{
if((new File(dest)).exists())
{
return false;
}
r=new FileInputStream(src);
w=new FileOutputStream(dest);
bfr=new byte[b];
while((rsiz=r.read(bfr))!=-1)
{
encryptArray(bfr,shft);
w.write(enc,0,rsiz);
}
r.close();
w.close();
}
catch(Exception e)
{
return false;
}
return true;
}
void encryptArray(byte arr[],int a)
{
int i,l;
l=arr.length;
enc=new byte[l];
for(i=0;i<l;i++)
{
enc[i]=(byte)(arr[i]+a);
}
}
}