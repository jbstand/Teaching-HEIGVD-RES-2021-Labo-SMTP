# Teaching-HEIGVD-RES-2021-Labo-SMTP

## <u>Description</u>

The initial idea of the project is to teach the student the behavior of the SMTP protocol and how to use it.

Thanks to this application, we can now prank our friends.

Because our application is a mail prank generator. To avoid spamming our friends, we have set up a mock server that will take care of retrieving the mails before they leave.

#### How does it work ? 

The application will randomly take the emails you propose and randomly take a message you propose, then it will randomly choose between the chosen emails to determine the victim that will send the email and the rest of the emails will be the victims receiving the email. 

## <u>Instructions to set up mock SMTP server</u>



## <u>Instructions to configure our tool</u>

First, in order to use our application, you will have to clone our repo.

To be able to use our application, you just have to modify three different files to adapt what you want :

- You can change the application's configuration here : `SMTPrank\src\main\resources\config.properties`
- You can change the prank messages here : `SMTPrank\src\main\resources\messages.utf8`
- You can change the list of victim's emails here : `SMTPrank\src\main\resources\victims.utf8`

And then to run the app, you'll need to run the class `SMTP/src/main/java/ch/heigvd/MelMot/Main.java`


## <u>Implementation</u>


 ![](https://github.com/jbstand/Teaching-HEIGVD-RES-2021-Labo-SMTP/blob/main/figures/uml.jpg)


When you start the connection with the server, the following messages are supposed to appear in your console (The client sends the EHLO request to the server) :

<p align="center">
  <img src="https://github.com/jbstand/Teaching-HEIGVD-RES-2021-Labo-SMTP/blob/main/figures/ehlo.PNG" width="50%;"/>
</p>

1. The first line represents the device from which I connect to the server and on which SMTP server we are connected
2. The second line represents the device I use to connect to the server
3. The third line tells that the content is  a MIME. encoded content
4. The fourth line tells us that the server is ready to accept conversation.

When you send a mail to the server, the following messages are supposed to appear in your console :

<p align="center">
  <img src="https://github.com/jbstand/Teaching-HEIGVD-RES-2021-Labo-SMTP/blob/main/figures/message.PNG" width="50%;"/>
</p>

1. The first "send" message represent the client sending the request "MAIL FROM : <john.doe@exemple.com>", which tells to server who's the sender
2. The second and third "send" message represent the client sending the request "RCPT TO : <john.doe@exemple.com>", which tells the server who's the recipient
3. The fourth "send" message represent the client sending the request "DATA", which tells the server the following messages are the body of the mail. It needs to end with "<CR><LF>.<CR><LF>"

4. Every messages send to the server are followed with the response "250 Ok" which means the server accept what the client sends

#### Server side

<p align="center">
  <img src="https://github.com/jbstand/Teaching-HEIGVD-RES-2021-Labo-SMTP/blob/main/figures/server.PNG" width="80%;"/>
</p>

We can see that the mock server intercept all the mails we send.
