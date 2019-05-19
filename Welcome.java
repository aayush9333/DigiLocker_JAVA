import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
class Welcome
{
JFrame jf;
Dimension dim;
int w,h;
JLabel h1,u,p,stat;
JTextField user,pass;
JButton sub,fp;
EmailDB db;
UD ud;
Mailing ml;
Sound snd;
String x,y,temp;
Thread th;
int otpval,func;
int z;
boolean otp;
Welcome()
{
db=new EmailDB();
ud=new UD();
ml=new Mailing();
snd=new Sound();
func=1;
z=5;
th=new Thread()
{
public void run()
{
try
{
Thread.sleep(500);
}
catch(Exception e)
{
}
jf.remove(h1);
jf.remove(u);
jf.remove(p);
jf.remove(user);
jf.remove(pass);
jf.remove(sub);
jf.remove(fp);
stat.setText("Sorry, No more attempts left !!!");
jf.revalidate();
jf.repaint();
snd.PlaySound();
}
}
;
jf=new JFrame("LOGIN");
dim=(Toolkit.getDefaultToolkit()).getScreenSize();
w=(int)(dim.getWidth());
h=(int)(dim.getHeight());
jf.setBounds((w/2)-300,(h/2)-300,600,600);
jf.setResizable(false);
jf.setLayout(null);
jf.setDefaultCloseOperation(jf.EXIT_ON_CLOSE);
jf.setVisible(true);
configure();
}
void configure()
{
h1=new JLabel();
h1.setFont(new Font("Comic Sans MS",0,18));
//h1.setOpaque(true);
//h1.setBackground(Color.yellow);
h1.setText("              WELCOME TO DIGILOCKER !!!");
h1.setBounds(100,50,400,150);
jf.add(h1);

u=new JLabel("User :");
u.setFont(new Font("Comic Sans MS",0,14));
//u.setOpaque(true);
//u.setBackground(Color.orange);
u.setBounds(h1.getX(),h1.getY()+h1.getHeight()+50,150,40);
jf.add(u);

p=new JLabel("Password :");
p.setFont(new Font("Comic Sans MS",0,14));
//p.setOpaque(true);
//p.setBackground(Color.orange);
p.setBounds(u.getX(),u.getY()+u.getHeight()+20,150,40);
jf.add(p);

user=new JTextField();
user.setFont(new Font("Comic Sans MS",0,14));
user.setBounds(u.getX()+u.getWidth(),u.getY(),250,40);
jf.add(user);

pass=new JPasswordField();
pass.setFont(new Font("Comic Sans MS",0,14));
pass.setBounds(p.getX()+p.getWidth(),p.getY(),250,40);
jf.add(pass);

sub=new JButton("S U B M I T");
sub.setBounds(p.getX()+25,p.getY()+p.getHeight()+80,150,40);
jf.add(sub);

fp=new JButton("Forget Password");
fp.setBounds(sub.getX()+sub.getWidth()+50,sub.getY(),150,40);
jf.add(fp);

stat=new JLabel();
stat.setOpaque(true);
stat.setBackground(Color.blue);
stat.setForeground(Color.green);
stat.setBounds(p.getX()+100,p.getY()+p.getHeight()+20,200,30);
jf.add(stat);

set();

jf.revalidate();
jf.repaint();
}
void set()
{
sub.addActionListener(new ActionListener()
{
public void actionPerformed(java.awt.event.ActionEvent e)
{
x=user.getText().toString();
y=pass.getText().toString();
if(x!=""&&y!="")
{
if(db.searchUser(x))
{
if(y.equals(db.getPassword(x)))
{
startProgram();
}
else
{
z--;
stat.setText("incorrect password");
}
}
else
{
stat.setText("user not found");
}
}
else
{
stat.setText("submit");
}
if(z==0)
{
alertProgram();
}
}
}
);
fp.addActionListener(new ActionListener()
{
public void actionPerformed(java.awt.event.ActionEvent e)
{
if(func==1)
{
x=user.getText();
if(db.searchUser(x))
{
temp=x;
generate(x);
}
else
{
stat.setText("user not found");
}
}
else if(func==2)
{
y=pass.getText();
if((y.trim()).equals((otpval+"").trim()))
{
func=3;
u.setText("Enter new password");
p.setText("Enter new password");
stat.setText("OTP Matched !!!");
fp.setText("Change Password");
user.setText("");
pass.setText("");
}
else
{
reset();
stat.setText("WRONG OTP");
}
}
else if(func==3)
{
x=user.getText();
y=pass.getText();
if((x.trim()).equals(y.trim()))
{
if(db.setPassword(temp,x.trim()))
{
stat.setText("PASSWORD CHANGED SUCCESSFULLY");
}
else
{
stat.setText("PASSWORD UNCHANGED");
}
}
else
{
stat.setText("COULD NOT CHANGE PASSWORD");
}
reset();
}
}
}
);
}
void reset()
{
func=1;
u.setText("User :");
p.setText("Password :");
user.setText(temp);
pass.setText("");
fp.setText("Forget Password");
sub.setEnabled(true);
}
void generate(String email)
{
otpval=(int)(Math.random()*10000);
otpval=(otpval<1000)?(otpval+1000):otpval;
otp=ml.sendMail(email,otpval+"");
if(otp)
{
sub.setEnabled(false);
stat.setText("OTP SENT !!!");
p.setText("Enter OTP");
func=2;
fp.setText("Enter OTP");
}
}
boolean submitOTP()
{
return true;
}
void alertProgram()
{
th.start();
}
void startProgram()
{
jf.dispose();
ud.start();
}
}